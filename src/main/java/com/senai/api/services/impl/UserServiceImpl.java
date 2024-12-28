package com.senai.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.senai.api.dtos.UserDto;
import com.senai.api.entities.User;
import com.senai.api.repositories.UserRepository;
import com.senai.api.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public ResponseEntity<?> list() {
		try {
			List<User> users = userRepository.findAll();
			List<UserDto> usersDto = new ArrayList<UserDto>();

			for (User user : users) {
				UserDto userDto = new UserDto();
				BeanUtils.copyProperties(user, userDto);
				userDto.setPassword(null);
				usersDto.add(userDto);
			}

			return new ResponseEntity<>(usersDto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível recuperar a lista de usuários.", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> find(Integer userId) {
		try {
			User user = userRepository.getReferenceById(userId);
			UserDto userDto = new UserDto();
			user.setPassword(null);
			BeanUtils.copyProperties(user, userDto);
			return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível recuperar os dados do usuário.", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> register(UserDto userDto) {

		Optional<User> userExists = userRepository.findByEmail(userDto.getEmail());
		if (!userExists.isEmpty()) {
			return new ResponseEntity<>("Usuário já cadastrado.", HttpStatus.BAD_REQUEST);
		}

		try {
			User user = new User();
			String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
			BeanUtils.copyProperties(userDto, user);
			user.setPassword(encryptedPassword);
			userRepository.save(user);
			userDto.setPassword(null);
			return new ResponseEntity<>(userDto, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> edit(Integer userId, UserDto userDto) {
		boolean isExists = userRepository.existsById(userId);

		if (isExists) {
			User user = userRepository.getReferenceById(userId);

			if (userDto.getName() != null) {
				user.setName(userDto.getName());
			}
			if (userDto.getEmail() != null) {
				user.setEmail(userDto.getEmail());
			}
			if (userDto.getPassword() != null) {
				user.setPassword(passwordEncoder.encode(userDto.getPassword()));
			}
			if (userDto.getRole() != null) {
				user.setRole(userDto.getRole());
			}
			if (userDto.getPosition() != null) {
				user.setPosition(userDto.getPosition());
			}
			if (userDto.getEnable() != null) {
				user.setEnable(userDto.getEnable());
			}

			userRepository.saveAndFlush(user);
			return new ResponseEntity<>("Usuário atualizado com sucesso.", HttpStatus.ACCEPTED);
		}

		return new ResponseEntity<>("Não foi possível atualizar os dados do usuário.", HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<?> changePassword(Integer userId, String newPassword) {
		Boolean isEmpty = newPassword.trim().length() == 0;
		if (isEmpty) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("A senha fornecida é inválida.");
		}

		User user = userRepository.getReferenceById(userId);
		if (user.getId() == userId) {
			String encryptedPassword = new BCryptPasswordEncoder().encode(newPassword);
			user.setPassword(encryptedPassword);
			userRepository.saveAndFlush(user);
			return new ResponseEntity<>("Senha atualizada com sucesso.", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("Não foi possível atualizar a senha.", HttpStatus.NOT_FOUND);
	}

}
