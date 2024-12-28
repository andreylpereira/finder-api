package com.senai.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.api.dtos.ItemDto;
import com.senai.api.services.ItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/items")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@PostMapping
	public ResponseEntity<?> createItem(@Valid @RequestBody ItemDto itemDto) {
		return itemService.register(itemDto);
	}

	@GetMapping
	public ResponseEntity<?> getItems() {
		return itemService.list();
	}

	@GetMapping("/{itemId}")
	public ResponseEntity<?> getItem(@PathVariable Integer itemId) {
		return itemService.find(itemId);
	}
	
	@GetMapping("/{itemId}/forms")
	public ResponseEntity<?> getFormsItem(@PathVariable Integer itemId) {
		return itemService.findForms(itemId);
	}

	@PutMapping("/{itemId}")
	public ResponseEntity<?> putItem(@PathVariable Integer itemId, @Valid @RequestBody ItemDto itemDto) {
		return itemService.edit(itemId, itemDto);
	}

}
