package com.WB.API.dto;

import java.util.ArrayList;
import java.util.List;

public class HobbyDTO {

	private int id;
	private String name;
	private List<HobbyDescriptionDTO> descriptions;

	public HobbyDTO() {
		this.descriptions = new ArrayList<>();
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
