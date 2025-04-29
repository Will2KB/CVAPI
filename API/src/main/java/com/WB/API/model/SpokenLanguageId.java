package com.WB.API.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SpokenLanguageId {

	@Column(name = "person_id", insertable = false, updatable = false)
	private int personId;

	@Column(name = "language_id", insertable = false, updatable = false)
	private int languageId;

	public SpokenLanguageId() {

	}

	public SpokenLanguageId(int personId, int langageId) {
		this();
		this.languageId = langageId;
		this.personId = personId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		SpokenLanguageId that = (SpokenLanguageId) obj;
		return Objects.equals(personId, that.personId) && Objects.equals(languageId, that.languageId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(personId, languageId);
	}

}
