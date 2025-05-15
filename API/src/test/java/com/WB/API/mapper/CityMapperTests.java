package com.WB.API.mapper;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.CityAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.CityDTO;
import com.WB.API.model.City;

@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du mapper des villes")
public class CityMapperTests {

	@Test
	@DisplayName("Test de conversion d'une entité ville sans pays en objet de transfert")
	public void testEntityToDTO_ReturnEntityWithoutCountry() {
		// Arrange
		City entity = CityAssertions.getCity();

		// Act
		CityDTO dto = CityMapper.toDTO(entity);

		// Assert
		CityAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'une entité ville en objet de transfert")
	public void testEntityToDTO_ReturnCompleteEntity() {
		// Arrange
		City entity = CityAssertions.getCityWithCountry();

		// Act
		CityDTO dto = CityMapper.toDTO(entity);

		// Assert
		CityAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'une entité ville null en objet de transfert")
	public void testEntityToDTO_ReturnNullEntity() {
		// Arrange set to Null + Act
		CityDTO dto = CityMapper.toDTO(null);

		// Assert
		Assertions.assertNull(dto);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités ville en liste d'objets de transfert")
	public void testEntityLIstToDTOList_ReturnCompleteDTOList() {
		// Arrange
		TestDatas<City, CityDTO> datas = CityAssertions.getCityTestDatas(6);

		// Act
		List<CityDTO> citiesDTOMapped = CityMapper.toDTOList(datas.entities);

		// Assert
		CityAssertions.assertListEntitiesDTOs(datas.entities, citiesDTOMapped);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités ville null en liste d'objets de transfert")
	public void testEntityLIstToDTOList_ReturnNullDTOList() {
		// Arrange set to Null + Act
		List<CityDTO> citiesDTOMapped = CityMapper.toDTOList(null);

		// Assert
		Assertions.assertNull(citiesDTOMapped);
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert ville en entité")
	public void testDTOToEntity_ReturnCompleteEntity() {
		// Arrange
		CityDTO dto = CityAssertions.getCityDTO();

		// Act
		City entity = CityMapper.toEntity(dto);

		// Assert
		CityAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert ville null en entité")
	public void testDTOToEntity_ReturnNullEntity() {
		// Arrange set to Null + Act
		City entity = CityMapper.toEntity(null);

		// Assert
		Assertions.assertNull(entity);
	}

}
