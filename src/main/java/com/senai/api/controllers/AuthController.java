package com.senai.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.api.dtos.AuthDto;
import com.senai.api.dtos.AuthResponseDto;
import com.senai.api.services.AuthService;


@RestController
@RequestMapping("api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;	

	@PostMapping("/login")
	public ResponseEntity<AuthResponseDto> auth(@RequestBody AuthDto authDto) {
		return authService.login(authDto);
	}

}
