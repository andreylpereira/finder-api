package com.senai.api.dtos;

import java.time.LocalDateTime;
import java.util.Date;

public class ItemDto {

	private Integer id;
	private String title;
	private String description;
	private String localFound;
	private Date dateFound;
	private String contentType;
	private String base64Image;
	private String status;
	private Boolean ownerFound;
	private String observation;
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
	public Date getDateFound() {
		return dateFound;
	}
	public void setDateFound(Date dateFound) {
		this.dateFound = dateFound;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
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
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
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
