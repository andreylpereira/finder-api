package com.senai.api.dtos;

import java.time.LocalDateTime;

public class ContactFormDto {

	private Integer id;
	private String nameUser;
	private String contactUser;
	private String contactTitle;
	private String contactDescription;
	private LocalDateTime contactDate; 
	private Integer itemId;
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
	
	
}
