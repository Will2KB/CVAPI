package com.WB.API.dto;

import jakarta.validation.constraints.NotBlank;

public class NationalityDTO {

	private int id;

	@NotBlank(message = "Un nom de nationalité est nécessaire")
	private String name;

	public NationalityDTO() {

	}

	public NationalityDTO(int id, String name) {
		this();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
