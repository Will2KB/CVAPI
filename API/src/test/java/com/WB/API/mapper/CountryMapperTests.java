package com.WB.API.mapper;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.CountryAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.CountryDTO;
import com.WB.API.model.Country;

@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du mapper des pays")
public class CountryMapperTests {

	@Test
	@DisplayName("Test de conversion d'une entité pays en objet de transfert")
	public void testEntityToDTO_ReturnCompleteDTO() {
		// Arrange
		Country entity = CountryAssertions.getCountry();

		// Act
		CountryDTO dto = CountryMapper.toDTO(entity);

		// Assert
		CountryAssertions.assertEqualsProperties(entity, dto);

	}

	@Test
	@DisplayName("Test de conversion d'une entité pays null en objet de transfert")
	public void testEntityToDTO_ReturnNullDTO() {
		// Arrange set to Null + Actr
		CountryDTO dto = CountryMapper.toDTO(null);

		// Assert
		Assertions.assertNull(dto);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités pays en liste d'objets de transfert")
	public void testEntityListToDTOList_ReturnCompletDTOList() {
		// Arrange
		TestDatas<Country, CountryDTO> datas = CountryAssertions.getCountryTestDatas(8);

		// Act
		List<CountryDTO> listDTO = CountryMapper.toDTOList(datas.entities);

		// Assert
		CountryAssertions.assertListEntitiesDTOs(datas.entities, listDTO);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités pays null en liste d'objets de transfert")
	public void testEntityListToDTOList_ReturnNullDTOList() {
		// Arrange set to Null + Act + Assert
		Assertions.assertNull(CountryMapper.toDTOList(null));
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert pays en entité")
	public void testDTOToEntity_ReturnCompleteEntity() {
		// Arrange
		CountryDTO dto = CountryAssertions.getCountryDTO();

		// Act
		Country entity = CountryMapper.toEntity(dto);

		// Assert
		CountryAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert pays null en entité")
	public void testDTOToEntity_ReturnNullEntity() {
		// Arrange set to Null + Act
		Country entity = CountryMapper.toEntity(null);

		// Assert
		Assertions.assertNull(entity);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'objets de transfert pays en liste d'entités")
	public void testDTOListToEntityList_ReturnCompleteEntityList() {
		// Arrange
		TestDatas<Country, CountryDTO> datas = CountryAssertions.getCountryTestDatas(8);

		// Act
		List<Country> listEntities = CountryMapper.toEntityList(datas.dtos);

		// Assert
		CountryAssertions.assertListEntitiesDTOs(listEntities, datas.dtos);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'objets de transfert pays null en liste d'entités")
	public void testDTOListToEntityList_ReturnNullEntityList() {
		// Arrange set to Null + Act + Assert
		Assertions.assertNull(CountryMapper.toEntityList(null));
	}

}
