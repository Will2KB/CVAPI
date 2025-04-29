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
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "language")
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotBlank(message = "un nom de langue est nécessaire")
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "language", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SpokenLanguage> languagesSpoken;

	public Language() {
		this.languagesSpoken = new ArrayList<>();
	}

	public Language(int id) {
		this.id = id;
	}

	public Language(int id, String name) {
		this();
		this.id = id;
		this.name = name;
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

	public List<SpokenLanguage> getLanguageSpoken() {
		return languagesSpoken;
	}

	public void setLanguageSpoken(List<SpokenLanguage> languageSpoken) {
		this.languagesSpoken = languageSpoken;
	}

	public void addLanguageSpoken(SpokenLanguage languageSpoken) {
		languagesSpoken.add(languageSpoken);
		languageSpoken.setLanguage(this);
	}

	public void removeLanguageSpoken(SpokenLanguage languageSpoken) {
		languagesSpoken.remove(languageSpoken);
		languageSpoken.setLanguage(null);
	}
}
