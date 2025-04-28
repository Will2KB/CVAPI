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

import com.WB.API.model.Nationality;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Test du repository des nationalités")
class NationalityRepositoryTest {

	@Autowired
	private NationalityRepository nationalityRepository;

	private List<Nationality> nationalyties;

	@BeforeEach
	private void loadData() {
		nationalyties = new ArrayList<>();

		Nationality nationality1 = new Nationality();
		nationality1.setName("Française");
		nationalityRepository.save(nationality1);
		nationalyties.add(nationality1);

		Nationality nationality2 = new Nationality();
		nationality2.setName("Suisse");
		nationalityRepository.save(nationality2);
		nationalyties.add(nationality2);

		Nationality nationality3 = new Nationality();
		nationality3.setName("Espagnol");
		nationalityRepository.save(nationality3);
		nationalyties.add(nationality3);
	}

	@Test
	@DisplayName("Chargement de toutes les nationalités")
	void findAllCities_ReturnCorrectList() {
		List<Nationality> findCities = nationalityRepository.findAll();

		for (Nationality expectedNationality : nationalyties) {
			Nationality actualNationality = findCities.stream()
					.filter(c -> c.getName().equals(expectedNationality.getName())).findFirst().orElse(null);

			Assertions.assertNotNull(actualNationality);
			Assertions.assertEquals(expectedNationality.getId(), actualNationality.getId());
			Assertions.assertEquals(expectedNationality.getName(), actualNationality.getName());
		}
	}

	@Test
	@DisplayName("Chargement d'une nationalité à partir de son ID")
	void findNationalityById_ReturnCorrectNationality() {
		Nationality searchNationality = nationalyties.get(2);
		Optional<Nationality> optNationality = nationalityRepository.findById(searchNationality.getId());

		Assertions.assertTrue(optNationality.isPresent(),
				"La nationalité n'a pas été trouvée pour l'ID " + searchNationality.getId());
		Nationality Nationality = optNationality.get();

		Assertions.assertNotNull(Nationality);
		Assertions.assertEquals(searchNationality.getId(), Nationality.getId());
		Assertions.assertEquals(searchNationality.getName(), Nationality.getName());
	}

}
