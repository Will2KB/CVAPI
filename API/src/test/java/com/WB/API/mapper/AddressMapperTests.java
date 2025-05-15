package com.WB.API.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.AddressAssertions;
import com.WB.API.dto.AddressDTO;
import com.WB.API.model.Address;

@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du mapper des adresses")
public class AddressMapperTests {

	@Test
	@DisplayName("Test de conversion d'une entité adresse en objet de transfert")
	public void testEntityToDTO_ReturnCompleteDTO() {
		// Arrange
		Address entity = AddressAssertions.getAddress();

		// Act
		AddressDTO dto = AddressMapper.toDTO(entity);

		// Assert
		AddressAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'une entité adresse null en objet de transfert")
	public void testEntityToDTO_ReturnNullDTO() {
		// Arrange set to Null + Act
		AddressDTO dto = AddressMapper.toDTO(null);

		// Assert
		Assertions.assertNull(dto);
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert adresse en entité")
	public void testDTOToEntity_ReturnCompleteEntity() {
		// Arrange
		AddressDTO dto = AddressAssertions.getAddressDTO();

		// Act
		Address entity = AddressMapper.toEntity(dto);

		// Assert
		AddressAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert adresse null en entité")
	public void testDTOToEntity_ReturnNullEntity() {
		// Arrange set to Null + Act
		Address entity = AddressMapper.toEntity(null);

		// Assert
		Assertions.assertNull(entity);
	}
}
