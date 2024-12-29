package com.senai.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.api.dtos.UserDto;
import com.senai.api.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
		return userService.register(userDto);
	}

	@GetMapping
	public ResponseEntity<?> getUsers() {
		return userService.list();
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getUser(@PathVariable Integer userId) {
		return userService.find(userId);
	}

	@PatchMapping("/{userId}")
	public ResponseEntity<?> putUser(@PathVariable Integer userId, @RequestBody UserDto userDto) {
		return userService.edit(userId, userDto);
	}

	@PatchMapping("/change-password/{userId}")
	public ResponseEntity<?> putUserPassword(@PathVariable Integer userId, @Valid @RequestBody String password) {
		return userService.changePassword(userId, password);
	}
}
