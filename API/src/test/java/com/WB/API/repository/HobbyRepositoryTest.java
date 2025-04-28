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

import com.WB.API.model.Hobby;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Test du repository des passions")
class HobbyRepositoryTest {

	@Autowired
	private HobbyRepository hobbyRepository;

	private List<Hobby> hobbies;

	@BeforeEach
	private void loadData() {
		hobbies = new ArrayList<>();

		Hobby hobby1 = new Hobby();
		hobby1.setName("Modélisme");
		hobbyRepository.save(hobby1);
		hobbies.add(hobby1);

		Hobby hobby2 = new Hobby();
		hobby2.setName("Collectionneur de BD");
		hobbyRepository.save(hobby2);
		hobbies.add(hobby2);

		Hobby hobby3 = new Hobby();
		hobby3.setName("Bricolage");
		hobbyRepository.save(hobby3);
		hobbies.add(hobby3);
	}

	@Test
	@DisplayName("Chargement d'une passion à partir de son ID")
	void findHobbyById_ReturnCorrectHobby() {
		Hobby searchHobby = hobbies.get(2);
		Optional<Hobby> optHobby = hobbyRepository.findById(searchHobby.getId());

		Assertions.assertTrue(optHobby.isPresent(), "La passion n'a pas été trouvée pour l'ID " + searchHobby.getId());
		Hobby Hobby = optHobby.get();

		Assertions.assertNotNull(Hobby);
		Assertions.assertEquals(searchHobby.getId(), Hobby.getId());
		Assertions.assertEquals(searchHobby.getName(), Hobby.getName());
	}

}
