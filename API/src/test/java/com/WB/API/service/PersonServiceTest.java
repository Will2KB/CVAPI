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

import com.WB.API.assertions.PersonAssertions;
import com.WB.API.assertions.TestSummaryDatas;
import com.WB.API.dto.PersonDTO;
import com.WB.API.dto.PersonSummaryDTO;
import com.WB.API.model.Person;
import com.WB.API.repository.PersonRepository;

@ActiveProfiles("test")
@DisplayName("Test du service des personnes")
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;

	@InjectMocks
	private PersonService personService;

	@Test
	@DisplayName("Sauvegarde d'une personne")
	void savePerson_ReturnsSavedPerson() {

		PersonDTO mockedPerson = PersonAssertions.getPersonDTO();
		Person input = new Person(mockedPerson.getId(), mockedPerson.getName(), mockedPerson.getFirstName(),
				mockedPerson.getMail(), mockedPerson.getPhone(), mockedPerson.getTitle(), mockedPerson.getSubtitle(),
				mockedPerson.getBirthDate(), mockedPerson.getPersonalValues());

		Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(input);

		PersonSummaryDTO result = personService.savePerson(mockedPerson);

		PersonAssertions.assertNotNullDTO(result);
		PersonAssertions.assertEqualsProperties(input, result);
	}

	@Test
	@DisplayName("Recherche d'une personne à partir de l'ID")
	void findById_ShouldReturnCorrectPerson() {
		PersonDTO mockedPerson = PersonAssertions.getPersonDTO();
		Person output = new Person(mockedPerson.getId(), mockedPerson.getName(), mockedPerson.getFirstName(),
				mockedPerson.getMail(), mockedPerson.getPhone(), mockedPerson.getTitle(), mockedPerson.getSubtitle(),
				mockedPerson.getBirthDate(), mockedPerson.getPersonalValues());
		Mockito.when(personRepository.findById(mockedPerson.getId())).thenReturn(Optional.of(output));
		PersonDTO result = personService.getPersonById(mockedPerson.getId());

		PersonAssertions.assertNotNullDTO(result);
		PersonAssertions.assertEqualsProperties(mockedPerson, result);
	}

	@Test
	@DisplayName("Chargement de toutes les personnes")
	void findAll_ShouldReturnListOfAllPersons() {
		TestSummaryDatas<Person, PersonDTO, PersonSummaryDTO> datas = PersonAssertions.getSkillTestDatas(4);
		Mockito.when(personRepository.findAll()).thenReturn(datas.entities);

		List<PersonSummaryDTO> loadedPersons = personService.getPersons();

		PersonAssertions.assertListEntitiesSummaries(datas.entities, loadedPersons);
	}
}