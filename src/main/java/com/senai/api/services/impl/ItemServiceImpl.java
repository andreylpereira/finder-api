package com.senai.api.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.senai.api.dtos.ContactFormDto;
import com.senai.api.dtos.ItemDto;
import com.senai.api.entities.ContactForm;
import com.senai.api.entities.Item;
import com.senai.api.entities.User;
import com.senai.api.repositories.ContactFormRepository;
import com.senai.api.repositories.ItemRepository;
import com.senai.api.repositories.UserRepository;
import com.senai.api.services.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	private ItemRepository itemRepository;
	private UserRepository userRepository;
	private ContactFormRepository contactFormRepository;

	@Autowired
	public ItemServiceImpl(ItemRepository itemRepository, UserRepository userRepository,
			ContactFormRepository contactFormRepository) {
		this.itemRepository = itemRepository;
		this.userRepository = userRepository;
		this.contactFormRepository = contactFormRepository;
	}

	@Override
	public ResponseEntity<?> list() {
		try {
			List<Item> items = itemRepository.findAll();
//			List<ItemDto> itemsDto = new ArrayList<>();
//
//			for (Item item : items) {
//				ItemDto itemDto = new ItemDto();
//
//				BeanUtils.copyProperties(item, itemDto);
//				itemDto.setUserId(item.getUserRegistration().getId());
//				itemsDto.add(itemDto);
//			}

			return new ResponseEntity<>(items, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível recuperar a lista de itens.", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> find(Integer itemId) {
		try {
			Item item = itemRepository.getReferenceById(itemId);
			ItemDto itemDto = new ItemDto();
			BeanUtils.copyProperties(item, itemDto);
			itemDto.setUserId(item.getUserRegistration().getId());
			return new ResponseEntity<>(itemDto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível recuperar os dados do item.", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> register(ItemDto itemDto) {
		Optional<Item> itemExists = itemRepository.findByTitle(itemDto.getTitle());
		if (!itemExists.isEmpty()) {
			return new ResponseEntity<>("Item já cadastrado.", HttpStatus.BAD_REQUEST);
		}

		try {
			boolean isExists = userRepository.existsById(itemDto.getUserId());

			if (isExists) {
				User user = userRepository.getReferenceById(itemDto.getUserId());
				Item item = new Item();
				BeanUtils.copyProperties(itemDto, item);
				item.setUserRegistration(user);
				item.setRegistrationDate(LocalDateTime.now());
				itemRepository.save(item);
				return new ResponseEntity<>(itemDto, HttpStatus.CREATED);
			}
			return new ResponseEntity<>("Usuário não encontrado.", HttpStatus.BAD_REQUEST);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> edit(Integer itemId, ItemDto itemDto) {
		boolean isExists = itemRepository.existsById(itemId);

		if (isExists) {
			Item item = itemRepository.getReferenceById(itemId);
			BeanUtils.copyProperties(itemDto, item);
			itemRepository.saveAndFlush(item);
			return new ResponseEntity<>("Item atualizado com sucesso.", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("Não foi possível atualizar os dados do item.", HttpStatus.NOT_FOUND);
	}

	@Override // deletar?
	public ResponseEntity<?> findForms(Integer itemId) {
		try {
			boolean isExists = itemRepository.existsById(itemId);
			if (isExists) {
				List<ContactForm> forms = contactFormRepository.findByItemId(itemId);
				List<ContactFormDto> formsDto = new ArrayList<>();
				for (ContactForm form : forms) {
					ContactFormDto formDto = new ContactFormDto();

					BeanUtils.copyProperties(form, formDto);
					formDto.setItemId(form.getItem().getId());
					formsDto.add(formDto);
				}
				return new ResponseEntity<>(formsDto, HttpStatus.OK);
			}
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return new ResponseEntity<>("Não foi possível recuperar os formulários.", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> listPublicItems() {
		try {
			List<Item> items = itemRepository.findAll();

			for (Item item : items) {
				item.setForms(null);
				item.setObservation(null);
			}
			List<Item> filterList = items.stream().filter(i -> i.getOwnerFound() == false).collect(Collectors.toList());
			return new ResponseEntity<>(filterList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível recuperar a lista de itens.", HttpStatus.BAD_REQUEST);
		}
	}
}
