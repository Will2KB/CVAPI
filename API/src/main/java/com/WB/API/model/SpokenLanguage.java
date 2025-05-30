package com.WB.API.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "person_speaks_language")
public class SpokenLanguage {

	@EmbeddedId
	private SpokenLanguageId id;

	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	@ManyToOne
	@JoinColumn(name = "language_id")
	private Language language;

	@Column(name = "level")
	private String level;

	public SpokenLanguage() {

	}

	public SpokenLanguage(int personId, int languageId) {
		this();
		this.id = new SpokenLanguageId(personId, languageId);
		this.setLanguage(new Language(languageId));
		this.setPerson(new Person(personId));
	}

	public SpokenLanguage(int personId, int languageId, String name, String level) {
		this(personId, languageId);
		this.setLanguage(new Language(languageId, name));
		this.setLevel(level);
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
