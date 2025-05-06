package com.WB.API.dto;

import jakarta.validation.constraints.NotBlank;

public class SkillDTO {

	private int id;

	@NotBlank(message = "Un nom de compétence est nécessaire")
	private String name;

	private int typeId;
	private String typeName;
	private boolean enable;

	public SkillDTO() {
	}

	public SkillDTO(int id, String name, boolean isEnable) {
		this();
		this.id = id;
		this.name = name;
		this.enable = isEnable;
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

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
