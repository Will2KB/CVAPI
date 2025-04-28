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

import com.WB.API.model.Language;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Test du repository des langues")
class LanguageRepositoryTest {

	@Autowired
	private LanguageRepository LanguageRepository;

	private List<Language> languages;

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
		Language searchLanguage = languages.get(2);
		Optional<Language> optLanguage = LanguageRepository.findById(searchLanguage.getId());

		Assertions.assertTrue(optLanguage.isPresent(),
				"La langue n'a pas été trouvée pour l'ID " + searchLanguage.getId());
		Language Language = optLanguage.get();

		Assertions.assertNotNull(Language);
		Assertions.assertEquals(searchLanguage.getId(), Language.getId());
		Assertions.assertEquals(searchLanguage.getName(), Language.getName());
	}

}
