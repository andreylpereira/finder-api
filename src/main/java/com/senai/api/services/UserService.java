package com.senai.api.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.senai.api.dtos.UserDto;

@Service
public interface UserService {

	ResponseEntity<?> list();
	ResponseEntity<?> find(Integer userId);
	ResponseEntity<?> register(UserDto userDto);
	ResponseEntity<?> edit(Integer userId, UserDto userDto);
	ResponseEntity<?> changePassword(Integer userId, String newPassword);
}
