package com.WB.API.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.NationalityAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.NationalityDTO;
import com.WB.API.model.Nationality;
import com.WB.API.repository.NationalityRepository;

@ActiveProfiles("test")
@DisplayName("Test du service des nationalités")
@ExtendWith(MockitoExtension.class)
class NationalityServiceTest {

	@Mock
	private NationalityRepository nationalityRepository;

	@InjectMocks
	private NationalityService nationalityService;

	@Test
	@DisplayName("Recherche d'une nationalité à partir de l'ID")
	void findById_ShouldReturnCorrectNationality() {
		NationalityDTO mockedNationalityDTO = NationalityAssertions.getNationalityDTO();
		Nationality mockedNationality = new Nationality(mockedNationalityDTO.getId(), mockedNationalityDTO.getName());
		Mockito.when(nationalityRepository.findById(mockedNationality.getId()))
				.thenReturn(Optional.of(mockedNationality));

		NationalityDTO result = nationalityService.getNationalityById(mockedNationality.getId());

		NationalityAssertions.assertNotNullDTO(result);
		NationalityAssertions.assertEqualsProperties(mockedNationality, result);
	}

	@Test
	@DisplayName("Chargement de toutes les nationalités")
	void getNationalities_ShouldReturnListOfAllNationalitys() {
		TestDatas<Nationality, NationalityDTO> datas = NationalityAssertions.getSkillTestDatas(6);
		Mockito.when(nationalityRepository.findAll()).thenReturn(datas.entities);

		List<NationalityDTO> loadedNationalities = nationalityService.getNationalities();

		NationalityAssertions.assertListEntitiesDTOs(datas.entities, loadedNationalities);
	}
}