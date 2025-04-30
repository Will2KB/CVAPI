package com.WB.API.dto;

import jakarta.validation.constraints.NotBlank;

public class SpokenLanguageDTO {

	private int personId;
	private int languageId;
	private String name;
	@NotBlank(message = "Un niveau est nécessaire")
	private String level;

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
