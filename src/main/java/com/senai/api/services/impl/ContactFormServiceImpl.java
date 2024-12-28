package com.senai.api.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.senai.api.dtos.ContactFormDto;
import com.senai.api.entities.ContactForm;
import com.senai.api.entities.Item;
import com.senai.api.repositories.ContactFormRepository;
import com.senai.api.repositories.ItemRepository;
import com.senai.api.services.ContactFormService;

@Service
public class ContactFormServiceImpl implements ContactFormService {

	private ContactFormRepository contactFormRepository;
	private ItemRepository itemRepository;
	
	@Autowired
	public ContactFormServiceImpl(ContactFormRepository contactFormRepository, ItemRepository itemRepository) {
		this.contactFormRepository = contactFormRepository;
		this.itemRepository = itemRepository;
	}
	
	@Override
	public ResponseEntity<?> list() {
		try {
	        List<ContactForm> forms = contactFormRepository.findAll();
	        List<ContactFormDto> formsDto = new ArrayList<>();

	        for (ContactForm form : forms) {
	            ContactFormDto formDto = new ContactFormDto();
	           
	            BeanUtils.copyProperties(form, formDto);  
	            formDto.setItemId(form.getItem().getId());
	            formsDto.add(formDto);
	        }

	        return new ResponseEntity<>(formsDto, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Não foi possível recuperar os formulários.", HttpStatus.BAD_REQUEST);
	    }
	}

	@Override
	public ResponseEntity<?> find(Integer contactFormId) {
		try {
			ContactForm form = contactFormRepository.getReferenceById(contactFormId);
			ContactFormDto formDto = new ContactFormDto();
			BeanUtils.copyProperties(form, formDto);
			formDto.setItemId(form.getItem().getId());
			return new ResponseEntity<>(formDto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível recuperar os dados do formulário.", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> register(ContactFormDto contactFormDto) {
		Optional<ContactForm> formExists = contactFormRepository.findByNameUser(contactFormDto.getNameUser());
		if (!formExists.isEmpty()) {
			return new ResponseEntity<>("Formulário já cadastrado.", HttpStatus.BAD_REQUEST);
		}
		try {
			boolean isExists = itemRepository.existsById(contactFormDto.getItemId());
			
			if (isExists) {
				Item item = itemRepository.getReferenceById(contactFormDto.getItemId());
				ContactForm form = new ContactForm();
				BeanUtils.copyProperties(contactFormDto, form);
				form.setItem(item);
				contactFormRepository.save(form);
				return new ResponseEntity<>(contactFormDto, HttpStatus.CREATED);
			}
			return new ResponseEntity<>("Item não encontrado.", HttpStatus.BAD_REQUEST);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
