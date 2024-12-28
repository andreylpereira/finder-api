package com.senai.api.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.senai.api.entities.User;
import com.senai.api.repositories.UserRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

	@Value("${admin.email}")
	private String email;
	@Value("${admin.password}")
	private String password;

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public AdminInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<User> userExists = userRepository.findByEmail(email);
		if (userExists.isEmpty()) {
			User admin = new User();
			admin.setEmail(email);
			admin.setName("Admin");
			admin.setPassword(passwordEncoder.encode(password));
			admin.setRole("ADMINISTRADOR");
			admin.setPosition("Admin");
			userRepository.save(admin);
		}
	}

}