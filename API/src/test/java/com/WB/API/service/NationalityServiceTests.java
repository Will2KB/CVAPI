package com.WB.API.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
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
import com.WB.API.exceptions.RessourceNotFoundException;
import com.WB.API.model.Nationality;
import com.WB.API.repository.NationalityRepository;

/*
 * Tests du service des nationalités
 * Lors de ces test, nous souhaitons vérifier le fonctionnement du service 
 * c'est pourquoi le comportement du repository (auquel le service fait appel) sera mocké
 */
@ActiveProfiles("test")
@DisplayName("Test du service des nationalités")
@ExtendWith(MockitoExtension.class)
class NationalityServiceTests {

	@Mock
	private NationalityRepository nationalityRepository;

	@InjectMocks
	private NationalityService nationalityService;

	@Test
	@DisplayName("Recherche d'une nationalité à partir de l'ID")
	void findById_ShouldReturnCorrectNationality() {
		// Arrange
		NationalityDTO mockedNationalityDTO = NationalityAssertions.getNationalityDTO();
		Nationality mockedNationality = new Nationality(mockedNationalityDTO.getId(), mockedNationalityDTO.getName());
		Mockito.when(nationalityRepository.findById(mockedNationality.getId()))
				.thenReturn(Optional.of(mockedNationality));

		// Act
		NationalityDTO result = nationalityService.getNationalityById(mockedNationality.getId());

		// Assert
		NationalityAssertions.assertNotNullDTO(result);
		NationalityAssertions.assertEqualsProperties(mockedNationality, result);
	}

	@Test
	@DisplayName("Recherche d'une nationalité à partir d'un ID inexistant")
	void findById_ShouldReturnNullWhenNotFound() {
		// Arrange
		Integer id = 999;
		Mockito.when(nationalityRepository.findById(id)).thenReturn(Optional.empty());

		// Act + Assert
		RessourceNotFoundException exception = Assertions.assertThrows(RessourceNotFoundException.class,
				() -> nationalityService.getNationalityById(id));

		// Vérification du message de l'exception
		Assertions.assertEquals("Nationality not found with id: " + id, exception.getMessage());

	}

	@Test
	@DisplayName("Chargement de toutes les nationalités")
	void getNationalities_ShouldReturnListOfAllNationalitys() {
		// Arrange
		TestDatas<Nationality, NationalityDTO> datas = NationalityAssertions.getNationalityTestDatas(6);
		Mockito.when(nationalityRepository.findAll()).thenReturn(datas.entities);

		// Act
		List<NationalityDTO> loadedNationalities = nationalityService.getNationalities();

		// Assert
		NationalityAssertions.assertListEntitiesDTOs(datas.entities, loadedNationalities);
	}

	@Test
	@DisplayName("Chargement de toutes les nationalités quand la base de donnée est vide")
	void getNationalities_ShouldReturnEmptyListWhenBDDEmpty() {
		// Arrange
		Mockito.when(nationalityRepository.findAll()).thenReturn(List.of());

		// Act + Assert
		RessourceNotFoundException exception = Assertions.assertThrows(RessourceNotFoundException.class,
				() -> nationalityService.getNationalities());

		// Vérification du message de l'exception
		Assertions.assertEquals("List of nationalities is empty.", exception.getMessage());

	}
}