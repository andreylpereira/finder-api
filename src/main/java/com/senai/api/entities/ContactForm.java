package com.senai.api.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
	@NotBlank(message = "Campo e-mail não pode ser nulo ou em branco.")
	private String contactUser;
	@NotBlank(message = "Campo contato não pode ser nulo ou em branco.")
	private String contactTitle;
	@Lob
	@Column(columnDefinition = "TEXT")
	@NotBlank(message = "Campo contato não pode ser nulo ou em branco.")
	private String contactDescription;
	@ManyToOne
	@JoinColumn(name = "item_id")
	@JsonBackReference
	private Item item;
	private LocalDateTime contactDate = LocalDateTime.now();

	public ContactForm() {
	}

	public ContactForm(Integer id, @NotBlank(message = "Campo nome não pode ser nulo ou em branco.") String nameUser,
			@Email(message = "E-mail inválido.") @NotBlank(message = "Campo e-mail não pode ser nulo ou em branco.") String contactUser,
			@NotBlank(message = "Campo contato não pode ser nulo ou em branco.") String contactTitle,
			@NotBlank(message = "Campo contato não pode ser nulo ou em branco.") String contactDescription, Item item,
			LocalDateTime contactDate) {
		this.id = id;
		this.nameUser = nameUser;
		this.contactUser = contactUser;
		this.contactTitle = contactTitle;
		this.contactDescription = contactDescription;
		this.item = item;
		this.contactDate = contactDate;
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

	public String getContactUser() {
		return contactUser;
	}

	public void setContactUser(String contactUser) {
		this.contactUser = contactUser;
	}

	public String getContactTitle() {
		return contactTitle;
	}

	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
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

	

}