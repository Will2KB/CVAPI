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

import com.WB.API.model.Establishement;

@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du repository des établissements")
class EstablishementRepositoryTest {

	@Autowired
	EstablishementRepository establishementRepository;

	private List<Establishement> establishements;

	@BeforeEach
	private void loadData() {
		establishements = new ArrayList<>();

		Establishement establishement1 = new Establishement();
		establishement1.setName("Université");
		establishementRepository.save(establishement1);
		establishements.add(establishement1);

		Establishement establishement2 = new Establishement();
		establishement2.setName("Company");
		establishementRepository.save(establishement2);
		establishements.add(establishement2);

	}

	@Test
	@DisplayName("Chargement d'un établissement à partir de son ID")
	void findEstablishementById_ReturnCorrectEstablishement() {
		Establishement searchEstablishement = establishements.get(1);
		Optional<Establishement> optEstablishement = establishementRepository.findById(searchEstablishement.getId());

		Assertions.assertTrue(optEstablishement.isPresent(),
				"L'établissement n'a pas été trouvée pour l'ID " + searchEstablishement.getId());
		Establishement establishement = optEstablishement.get();

		Assertions.assertNotNull(establishement);
		Assertions.assertEquals(searchEstablishement.getId(), establishement.getId());
		Assertions.assertEquals(searchEstablishement.getName(), establishement.getName());
	}
}
