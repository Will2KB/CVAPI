package com.WB.API.dto;

import jakarta.validation.constraints.NotBlank;

public class HobbyDescriptionDTO {

	private int id;

	@NotBlank(message = "Une description de hobby est nécessaire")
	private String description;

	public HobbyDescriptionDTO() {

	}

	public HobbyDescriptionDTO(int id, String description) {
		this();
		this.setId(id);
		this.setDescription(description);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
