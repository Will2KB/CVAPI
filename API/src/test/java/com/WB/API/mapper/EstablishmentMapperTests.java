package com.WB.API.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.EstablishmentAssertions;
import com.WB.API.dto.EstablishmentDTO;
import com.WB.API.model.Establishment;

@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du mapper des établissements")
public class EstablishmentMapperTests {

	@Test
	@DisplayName("Test de conversion d'une entité établissement sans adresse en objet de transfert")
	public void testEntityToDTO_ReturnDTOWithoutAddress() {
		// Arrange
		Establishment entity = EstablishmentAssertions.getEstablishment();

		// Act
		EstablishmentDTO dto = EstablishmentMapper.toDTO(entity);

		// Assert
		EstablishmentAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'une entité établissement sans ville en objet de transfert")
	public void testEntityToDTO_ReturnDTOWithoutCity() {
		// Arrange
		Establishment entity = EstablishmentAssertions.getEstablishmentWithAddress();

		// Act
		EstablishmentDTO dto = EstablishmentMapper.toDTO(entity);

		// Assert
		EstablishmentAssertions.assertNotNullDTO(dto);
		EstablishmentAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'une entité établissement sans pays en objet de transfert")
	public void testEntityToDTO_ReturnDTOWithoutCountry() {
		// Arrange
		Establishment entity = EstablishmentAssertions.getEstablishmentWithCity();

		// Act
		EstablishmentDTO dto = EstablishmentMapper.toDTO(entity);

		// Assert
		EstablishmentAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'une entité établissement en objet de transfert")
	public void testEntityToDTO_ReturnCompleteDTO() {
		// Arrange
		Establishment entity = EstablishmentAssertions.getEstablishmentWithCountry();

		// Act
		EstablishmentDTO dto = EstablishmentMapper.toDTO(entity);

		// Assert
		EstablishmentAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'une entité établissement null en objet de transfert")
	public void testEntityToDTO_ReturnNullDTO() {
		// Arrange set to Null + Act
		EstablishmentDTO dto = EstablishmentMapper.toDTO(null);

		// Assert
		Assertions.assertNull(dto);
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert établissement en entité")
	public void testDTOToEntity_ReturnCompleteEntity() {
		// Arrange
		EstablishmentDTO dto = EstablishmentAssertions.getEstablishmentDTO();

		// Act
		Establishment entity = EstablishmentMapper.toEntity(dto);

		// Assert
		EstablishmentAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert établissement null en entité")
	public void testDTOToEntity_ReturnNullEntity() {
		// Arrange set to Null + Act
		Establishment entity = EstablishmentMapper.toEntity(null);

		// Assert
		Assertions.assertNull(entity);
	}

}
