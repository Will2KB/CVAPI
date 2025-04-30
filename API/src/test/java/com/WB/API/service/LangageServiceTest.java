package com.WB.API.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.dto.LanguageDTO;
import com.WB.API.mapper.LanguageMapper;
import com.WB.API.model.Language;
import com.WB.API.repository.LanguageRepository;

@ActiveProfiles("test")
@DisplayName("Test du service des langues")
@ExtendWith(MockitoExtension.class)
class LangageServiceTest {

	@Mock
	private LanguageRepository LanguageRepository;

	@InjectMocks
	private LanguageService languageService;

	private List<LanguageDTO> mockedListLanguages;

	@BeforeEach
	private void LoadData() {
		mockedListLanguages = new ArrayList<>();
		mockedListLanguages.add(new LanguageDTO(12, "Français"));
		mockedListLanguages.add(new LanguageDTO(13, "Anglais"));
		mockedListLanguages.add(new LanguageDTO(14, "Espagnol"));

	}

	private List<Language> getMockedListEntity() {
		List<Language> languages = new ArrayList<>();

		for (LanguageDTO languageDTO : mockedListLanguages) {
			languages.add(LanguageMapper.toEntity(languageDTO));
		}

		return languages;
	}

	@Test
	@DisplayName("Chargement de toutes les langues")
	void findAll_ShouldReturnListOfAllLanguages() {
		Mockito.when(LanguageRepository.findAll()).thenReturn(this.getMockedListEntity());

		List<LanguageDTO> loadedLanguages = languageService.getLanguages();

		for (LanguageDTO expectedLanguage : mockedListLanguages) {
			LanguageDTO actualLanguage = loadedLanguages.stream()
					.filter(c -> c.getName().equals(expectedLanguage.getName())).findFirst().orElse(null);

			Assertions.assertNotNull(actualLanguage);
			Assertions.assertEquals(expectedLanguage.getId(), actualLanguage.getId());
			Assertions.assertEquals(expectedLanguage.getName(), actualLanguage.getName());
		}
	}
}