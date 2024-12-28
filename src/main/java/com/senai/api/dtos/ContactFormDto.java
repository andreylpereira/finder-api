package com.senai.api.dtos;

import java.time.LocalDateTime;

public class ContactFormDto {

	private Integer id;
	private String nameUser;
	private String emailUser;
	private String contactDescription;
	private LocalDateTime contactDate;
	private Integer itemId;
	private Boolean analized;

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
	public LocalDateTime getContactDate() {
		return contactDate;
	}
	public void setContactDate(LocalDateTime contactDate) {
		this.contactDate = contactDate;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Boolean getAnalized() {
		return analized;
	}
	public void setAnalized(Boolean analized) {
		this.analized = analized;
	}
	
}
