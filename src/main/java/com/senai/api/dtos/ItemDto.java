package com.senai.api.dtos;

import java.time.LocalDateTime;

public class ItemDto {

	private Integer id;
	private String title;
	private String description;
	private String localFound;
	private LocalDateTime dateFound;
	private String photo;
	private String status;
	private Boolean ownerFound;
	private LocalDateTime registrationDate;
	private Integer userId;

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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
