package com.senai.api.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "contact_Forms")
public class ContactForm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message = "Campo nome não pode ser nulo ou em branco.")
	private String nameUser;
	@Email(message = "E-mail inválido.")
	@NotBlank(message = "Campo e-mail não pode ser nulo ou em branco.")
	private String emailUser;
	@NotBlank(message = "Campo contato não pode ser nulo ou em branco.")
	private String contactDescription;
	@ManyToOne
	@JoinColumn(name = "item_id")
	@JsonBackReference
	private Item item;
	private LocalDateTime contactDate = LocalDateTime.now();
	private Boolean analized = false;

	public ContactForm() {
	}

	public ContactForm(Integer id, @NotBlank(message = "Campo nome não pode ser nulo ou em branco.") String nameUser,
			@Email(message = "E-mail inválido.") @NotBlank(message = "Campo e-mail não pode ser nulo ou em branco.") String emailUser,
			@NotBlank(message = "Campo contato não pode ser nulo ou em branco.") String contactDescription, Item item,
			LocalDateTime contactDate, Boolean analized) {
		this.id = id;
		this.nameUser = nameUser;
		this.emailUser = emailUser;
		this.contactDescription = contactDescription;
		this.item = item;
		this.contactDate = contactDate;
		this.analized = analized;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getContactDescription() {
		return contactDescription;
	}

	public void setContactDescription(String contactDescription) {
		this.contactDescription = contactDescription;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public LocalDateTime getContactDate() {
		return contactDate;
	}

	public void setContactDate(LocalDateTime contactDate) {
		this.contactDate = contactDate;
	}

	public Boolean getAnalized() {
		return analized;
	}

	public void setAnalized(Boolean analized) {
		this.analized = analized;
	}

	
}