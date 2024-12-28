package com.senai.api.entities;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "items")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message = "Campo título não pode ser nulo ou em branco.")
	private String title;
	@NotBlank(message = "Campo descrição não pode ser nulo ou em branco.")
	private String description;
	@NotBlank(message = "Campo local encontrado não pode ser nulo ou em branco.")
	private String localFound;
	private LocalDateTime dateFound;
	private String photo;
	@NotBlank(message = "Campo status não pode ser nulo ou em branco.")
	private String status;
	private Boolean ownerFound = false;
	private LocalDateTime registrationDate = LocalDateTime.now();

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User userRegistration;

	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
	private Set<ContactForm> forms;

	public Item() {
	}

	public Item(Integer id, String title, String description, String localFound, LocalDateTime dateFound, String photo,
			String status, Boolean ownerFound, LocalDateTime registrationDate, User userRegistration,
			Set<ContactForm> forms) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.localFound = localFound;
		this.dateFound = dateFound;
		this.photo = photo;
		this.status = status;
		this.ownerFound = ownerFound;
		this.registrationDate = registrationDate;
		this.userRegistration = userRegistration;
		this.forms = forms;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocalFound() {
		return localFound;
	}

	public void setLocalFound(String localFound) {
		this.localFound = localFound;
	}

	public LocalDateTime getDateFound() {
		return dateFound;
	}

	public void setDateFound(LocalDateTime dateFound) {
		this.dateFound = dateFound;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getOwnerFound() {
		return ownerFound;
	}

	public void setOwnerFound(Boolean ownerFound) {
		this.ownerFound = ownerFound;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public User getUserRegistration() {
		return userRegistration;
	}

	public void setUserRegistration(User userRegistration) {
		this.userRegistration = userRegistration;
	}

	public Set<ContactForm> getForms() {
		return forms;
	}

	public void setForms(Set<ContactForm> forms) {
		this.forms = forms;
	}

}