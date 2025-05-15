package com.WB.API.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Classe permettant de configurer l'ID de la table Spoken language. Cette id
 * est composé de deux clefs étrangères
 */
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
}
