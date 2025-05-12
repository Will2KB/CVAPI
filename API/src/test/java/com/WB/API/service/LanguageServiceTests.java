package com.WB.API.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.LanguageAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.LanguageDTO;
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.model.Language;
import com.WB.API.repository.LanguageRepository;

/*
 * Tests du service des langues
 * Lors de ces test, nous souhaitons vérifier le fonctionnement du service 
 * c'est pourquoi le comportement du repository (auquel le service fait appel) sera mocké
 */
@ActiveProfiles("test")
@DisplayName("Test du service des langues")
@ExtendWith(MockitoExtension.class)
class LanguageServiceTests {

	@Mock
	private LanguageRepository LanguageRepository;

	@InjectMocks
	private LanguageService languageService;

	@Test
	@DisplayName("Chargement de toutes les langues")
	void findAll_ShouldReturnListOfAllLanguages() {
		// Arrange
		TestDatas<Language, LanguageDTO> datas = LanguageAssertions.getLanguageTestDatas(3);
		Mockito.when(LanguageRepository.findAll()).thenReturn(datas.entities);

		// Act
		List<LanguageDTO> loadedLanguages = languageService.getLanguages();

		// Assert
		LanguageAssertions.assertListEntitiesDTOs(datas.entities, loadedLanguages);
	}

	@Test
	@DisplayName("Chargement de toutes les langues quand la base de données est vide")
	void findAll_ShouldReturnEmptyListWhenBDDEmpty() {
		// Arrange
		Mockito.when(LanguageRepository.findAll()).thenReturn(List.of());
		// Act + Assert
		RessourceNotFoundException exception = Assertions.assertThrows(RessourceNotFoundException.class,
				() -> languageService.getLanguages());

		// Vérification du message de l'exception
		Assertions.assertEquals("List of languages is empty.", exception.getMessage());
	}
}