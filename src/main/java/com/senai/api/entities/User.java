package com.senai.api.entities;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Email(message = "E-mail inválido.")
	@NotBlank(message = "Campo e-mail não pode ser nulo ou em branco.")
	private String email;
	@NotBlank(message = "Campo senha não pode ser nulo ou em branco.")
	private String password;
	@NotBlank(message = "Campo nome não pode ser nulo ou em branco.")
	private String name;
	@NotBlank(message = "Campo acesso não pode ser nulo ou em branco.")
	private String role;
	@NotBlank(message = "Campo cargo não pode ser nulo ou em branco.")
	private String position;
	private Boolean enable = true;
	private LocalDateTime creationDate = LocalDateTime.now();
	
	@OneToMany(mappedBy = "userRegistration", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Item> items;

	public User() {
	}

	public User(Integer id, String email, String password, String name, String role, String position, Boolean enable,
			LocalDateTime creationDate, Set<Item> items) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.role = role;
		this.position = position;
		this.enable = enable;
		this.creationDate = creationDate;
		this.items = items;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

}
