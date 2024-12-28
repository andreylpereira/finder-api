package com.senai.api.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.senai.api.dtos.ContactFormDto;

@Service
public interface ContactFormService {
	
	ResponseEntity<?> list();
	ResponseEntity<?> find(Integer contactFormId);
	ResponseEntity<?> register(ContactFormDto contactFormDto);

}
