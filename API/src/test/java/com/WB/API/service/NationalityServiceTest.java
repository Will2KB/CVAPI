package com.WB.API.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	private List<Nationality> mockedListNationalities;

	@BeforeEach
	private void LoadData() {
		mockedListNationalities = new ArrayList<Nationality>();
		mockedListNationalities.add(new Nationality(12, "Français"));
		mockedListNationalities.add(new Nationality(13, "Suisse"));
		mockedListNationalities.add(new Nationality(14, "Espagnol"));

	}

	@Test
	@DisplayName("Recherche d'une nationalité à partir de l'ID")
	void findById_ShouldReturnCorrectNationality() {
		Nationality mockedNationality = mockedListNationalities.get(2);
		Mockito.when(nationalityRepository.findById(mockedNationality.getId()))
				.thenReturn(Optional.of(mockedNationality));
		Nationality result = nationalityService.getNationalityById(mockedNationality.getId());

		Assertions.assertNotNull(result);
		Assertions.assertEquals(mockedNationality.getName(), result.getName());
	}

	@Test
	@DisplayName("Chargement de toutes les nationalités")
	void getNationalities_ShouldReturnListOfAllNationalitys() {
		Mockito.when(nationalityRepository.findAll()).thenReturn(mockedListNationalities);

		List<Nationality> loadedNationalities = nationalityService.getNationalities();

		for (Nationality expectedNationality : mockedListNationalities) {
			Nationality actualNationality = loadedNationalities.stream()
					.filter(c -> c.getName().equals(expectedNationality.getName())).findFirst().orElse(null);

			Assertions.assertNotNull(actualNationality);
			Assertions.assertEquals(expectedNationality.getId(), actualNationality.getId());
			Assertions.assertEquals(expectedNationality.getName(), actualNationality.getName());
		}
	}
}