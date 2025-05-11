package com.WB.API.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "skill")
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private SkillType type;

	@Column(name = "enable")
	private boolean enable;

	public Skill() {
		this.enable = true;
	}

	public Skill(int id, int typeId) {
		this();
		this.id = id;
		this.setType(new SkillType(typeId));
	}

	public Skill(int id, String name, boolean isEnable) {
		this();
		this.id = id;
		this.setName(name);
		this.setEnable(isEnable);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SkillType getType() {
		return type;
	}

	public void setType(SkillType type) {
		this.type = type;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public int getId() {
		return id;
	}
}
