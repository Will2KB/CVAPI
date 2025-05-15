package com.WB.API.mapper;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.PersonAssertions;
import com.WB.API.assertions.TestSummaryDatas;
import com.WB.API.dto.PersonDTO;
import com.WB.API.dto.PersonSummaryDTO;
import com.WB.API.model.Person;

@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du mapper des personnes")
public class PersonMapperTests {

	@Test
	@DisplayName("Test de conversion d'une entité personne en objet de transfert résumé")
	public void testEntityToSummaryDTO_ReturnCompleteDTO() {
		// Arrange
		Person entity = PersonAssertions.getPerson();

		// Act
		PersonSummaryDTO summary = PersonMapper.toSummaryDTO(entity);

		// Assert
		PersonAssertions.assertEqualsProperties(entity, summary);
	}

	@Test
	@DisplayName("Test de conversion d'une entité personne null en objet de transfert résumé")
	public void testEntityToSummaryDTO_ReturnNullDTO() {
		// Arrange set to Null + Act
		PersonSummaryDTO summary = PersonMapper.toSummaryDTO(null);

		// Assert
		Assertions.assertNull(summary);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités personne en liste d'objets de transfert résumé")
	public void testEntityListToDTOListSummary_ReturnDTOListSummary() {
		// Arrange
		TestSummaryDatas<Person, PersonDTO, PersonSummaryDTO> datas = PersonAssertions.getPersonTestDatas(4);

		// Act
		List<PersonSummaryDTO> listDTOSummary = PersonMapper.toDTOListSummary(datas.entities);

		// Assert
		PersonAssertions.assertListEntitiesSummaries(datas.entities, listDTOSummary);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités personne null en liste d'objets de transfert résumé")
	public void testEntityListToDTOListSummary_ReturnNullDTOListSummary() {
		// Arrange set to Null + Act + Assert
		Assertions.assertNull(PersonMapper.toDTOListSummary(null));
	}

	@Test
	@DisplayName("Test de conversion d'une entité personne en objet de transfert")
	public void testEntityToDTO_ReturnCompleteDTO() {
		// Arrange
		Person entity = PersonAssertions.getPerson();

		// Act
		PersonDTO dto = PersonMapper.toDTO(entity);

		// Assert
		PersonAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'une entité personne null en objet de transfert")
	public void testEntityToDTO_ReturnNullDTO() {
		// Arrange set to Null + Act
		PersonDTO dto = PersonMapper.toDTO(null);

		// Assert
		Assertions.assertNull(dto);
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert personne en entité")
	public void testDTOToEntity_ReturnCompleteEntity() {
		// Arrange
		PersonDTO dto = PersonAssertions.getPersonDTO();

		// Act
		Person entity = PersonMapper.toEntity(dto);

		// Assert
		PersonAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert personne null en entité")
	public void testDTOToEntity_ReturnNullEntity() {
		// Arrange set to Null + Act
		Person entity = PersonMapper.toEntity(null);

		// Assert
		Assertions.assertNull(entity);
	}
}
