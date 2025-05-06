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
		this.language = new Language(languageId);
		this.person = new Person(personId);
	}

	public SpokenLanguage(int personId, int languageId, String level) {
		this(personId, languageId);
		this.level = level;
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
