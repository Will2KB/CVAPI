package com.WB.API.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "skill_type")
public class SkillType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Skill> skills = new ArrayList<>();

	public SkillType() {
		this.skills = new ArrayList<>();
	}

	public SkillType(int typeId) {
		this();
		this.id = typeId;
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

	public void addSkill(Skill skill) {
		skills.add(skill);
		skill.setType(this);
	}

	public void removeSkill(Skill skill) {
		skills.remove(skill);
		skill.setType(null);
	}
}
