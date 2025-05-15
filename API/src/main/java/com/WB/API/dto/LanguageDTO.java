package com.WB.API.dto;

import jakarta.validation.constraints.NotBlank;

public class LanguageDTO {

	private int id;

	@NotBlank(message = "un nom de langue est nécessaire")
	private String name;

	public LanguageDTO() {
	}

	public LanguageDTO(int id) {
		this();
		this.setId(id);
	}

	public LanguageDTO(int id, String name) {
		this(id);
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
