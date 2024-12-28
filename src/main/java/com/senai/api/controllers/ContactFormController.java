package com.senai.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.api.dtos.ContactFormDto;
import com.senai.api.services.ContactFormService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/forms")
public class ContactFormController {
	
	@Autowired
	private ContactFormService contactFormService;
	
	@PostMapping
	public ResponseEntity<?> createContactForm(@Valid @RequestBody ContactFormDto contactFormDto) {
		return contactFormService.register(contactFormDto);
	}
	
	@GetMapping
	public ResponseEntity<?> getContactForms() {
		return contactFormService.list();
	}
	
	@GetMapping("/{contactFormId}")
	public ResponseEntity<?> getContactForm(@PathVariable Integer contactFormId) {
		return contactFormService.find(contactFormId);
	}

}
