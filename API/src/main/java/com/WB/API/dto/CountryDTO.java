package com.WB.API.dto;

import jakarta.validation.constraints.NotBlank;

public class CountryDTO {

	private int id;
	@NotBlank(message = "Un nom de pays est nécessaire")
	private String name;

	public CountryDTO() {

	}

	public CountryDTO(int id, String name) {
		this();
		this.setId(id);
		this.setName(name);
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
