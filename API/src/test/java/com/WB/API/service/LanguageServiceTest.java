package com.WB.API.service;

import java.util.List;

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
import com.WB.API.model.Language;
import com.WB.API.repository.LanguageRepository;

@ActiveProfiles("test")
@DisplayName("Test du service des langues")
@ExtendWith(MockitoExtension.class)
class LanguageServiceTest {

	@Mock
	private LanguageRepository LanguageRepository;

	@InjectMocks
	private LanguageService languageService;

	@Test
	@DisplayName("Chargement de toutes les langues")
	void findAll_ShouldReturnListOfAllLanguages() {
		TestDatas<Language, LanguageDTO> datas = LanguageAssertions.getSkillTestDatas(3);
		Mockito.when(LanguageRepository.findAll()).thenReturn(datas.entities);

		List<LanguageDTO> loadedLanguages = languageService.getLanguages();

		LanguageAssertions.assertListEntitiesDTOs(datas.entities, loadedLanguages);
	}
}