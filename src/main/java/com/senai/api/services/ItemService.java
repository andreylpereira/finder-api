package com.senai.api.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.senai.api.dtos.ItemDto;

@Service
public interface ItemService {
	
	ResponseEntity<?> list();
	ResponseEntity<?> find(Integer itemId);
	ResponseEntity<?> register(ItemDto itemDto);
	ResponseEntity<?> edit(Integer itemId, ItemDto itemDto);
	ResponseEntity<?> findForms(Integer itemId);
	ResponseEntity<?> listPublicItems();

}
