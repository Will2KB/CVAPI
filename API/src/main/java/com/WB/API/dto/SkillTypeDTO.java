package com.WB.API.dto;

import jakarta.validation.constraints.NotBlank;

public class SkillTypeDTO {

	private int id;

	@NotBlank(message = "Un nom de type de compétence est nécessaire")
	private String name;

	public SkillTypeDTO() {

	}

	public SkillTypeDTO(int id, String name) {
		this();
		this.setId(id);
		this.setName(name);
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
