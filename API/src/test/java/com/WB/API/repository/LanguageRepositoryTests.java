package com.WB.API.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.LanguageAssertions;
import com.WB.API.model.Language;

/*
 * Tests du repository des langues
 * Lors de ces test, nous souhaitons vérifier le fonctionnement du repository 
 * Ces tests seront effectué sur une base de test H2
 */
@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Test du repository des langues")
class LanguageRepositoryTests {

	@Autowired
	private LanguageRepository LanguageRepository;

	private List<Language> languages;

	/**
	 * Chargement des données de tests spécifique au repository
	 */
	@BeforeEach
	private void loadData() {
		languages = new ArrayList<>();

		Language language1 = new Language();
		language1.setName("Français");
		LanguageRepository.save(language1);
		languages.add(language1);

		Language language2 = new Language();
		language2.setName("Anglais");
		LanguageRepository.save(language2);
		languages.add(language2);

		Language language3 = new Language();
		language3.setName("Espagnol");
		LanguageRepository.save(language3);
		languages.add(language3);
	}

	@Test
	@DisplayName("Chargement d'une langue à partir de son ID")
	void findLanguageById_ReturnCorrectLanguage() {
		// Arrange
		Language searchLanguage = languages.get(2);

		// Act
		Optional<Language> optLanguage = LanguageRepository.findById(searchLanguage.getId());

		// Assert
		Assertions.assertTrue(optLanguage.isPresent(),
				"La langue n'a pas été trouvée pour l'ID " + searchLanguage.getId());
		Language foundLanguage = optLanguage.get();
		LanguageAssertions.assertEqualsProperties(searchLanguage, foundLanguage);
	}

	@Test
	@DisplayName("Chargement d'une langue à partir d'un ID inexistant")
	void findLanguageById_ReturnNullWhenNotFound() {
		// Arrange
		Integer id = 999;

		// Act
		Optional<Language> optLanguage = LanguageRepository.findById(id);

		// Assert
		Assertions.assertFalse(optLanguage.isPresent(), "La langue a été trouvée pour l'ID " + id);
	}
}
