package com.WB.API.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public class HobbyDTO {

	private int id;

	@NotBlank(message = "Un nom de hobby est nécessaire")
	private String name;

	@Valid
	private List<HobbyDescriptionDTO> descriptions;

	public HobbyDTO() {
		this.descriptions = new ArrayList<>();
	}

	public HobbyDTO(int id, String name) {
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

	public List<HobbyDescriptionDTO> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<HobbyDescriptionDTO> descriptions) {
		this.descriptions = descriptions;
	}

}
