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

import com.WB.API.model.Establishment;

@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du repository des établissements")
class EstablishementRepositoryTest {

	@Autowired
	EstablishementRepository establishementRepository;

	private List<Establishment> establishements;

	@BeforeEach
	private void loadData() {
		establishements = new ArrayList<>();

		Establishment establishement1 = new Establishment();
		establishement1.setName("Université");
		establishementRepository.save(establishement1);
		establishements.add(establishement1);

		Establishment establishement2 = new Establishment();
		establishement2.setName("Company");
		establishementRepository.save(establishement2);
		establishements.add(establishement2);

	}

	@Test
	@DisplayName("Chargement d'un établissement à partir de son ID")
	void findEstablishementById_ReturnCorrectEstablishement() {
		Establishment searchEstablishement = establishements.get(1);
		Optional<Establishment> optEstablishement = establishementRepository.findById(searchEstablishement.getId());

		Assertions.assertTrue(optEstablishement.isPresent(),
				"L'établissement n'a pas été trouvée pour l'ID " + searchEstablishement.getId());
		Establishment establishement = optEstablishement.get();

		Assertions.assertNotNull(establishement);
		Assertions.assertEquals(searchEstablishement.getId(), establishement.getId());
		Assertions.assertEquals(searchEstablishement.getName(), establishement.getName());
	}
}
