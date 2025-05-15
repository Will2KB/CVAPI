package com.WB.API.mapper;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.assertions.NationalityAssertions;
import com.WB.API.assertions.TestDatas;
import com.WB.API.dto.NationalityDTO;
import com.WB.API.model.Nationality;

@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du mapper des nationalités")
public class NationalityMapperTests {

	@Test
	@DisplayName("Test de conversion d'une entité nationalité en objet de transfert")
	public void testEntityToDTO_ReturnCompleteDTO() {
		// Arrange
		Nationality entity = NationalityAssertions.getNationality();

		// Act
		NationalityDTO dto = NationalityMapper.toDTO(entity);

		// Assert
		NationalityAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'une entité nationalité null en objet de transfert")
	public void testEntityToDTO_ReturnNullDTO() {
		// Arrange set to Null + Act
		NationalityDTO dto = NationalityMapper.toDTO(null);

		// Assert
		Assertions.assertNull(dto);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités nationalité en liste d'objets de transfert")
	public void testEntityListToDTOList_ReturnCompleteDTOList() {
		// Arrange
		TestDatas<Nationality, NationalityDTO> datas = NationalityAssertions.getNationalityTestDatas(6);

		// Act
		List<NationalityDTO> dtoMapped = NationalityMapper.toDTOList(datas.entities);

		// Assert
		NationalityAssertions.assertListEntitiesDTOs(datas.entities, dtoMapped);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'entités nationalité null en liste d'objets de transfert")
	public void testEntityListToDTOList_ReturnNullDTOList() {
		// Arrange set to Null + Act
		List<NationalityDTO> dtoMapped = NationalityMapper.toDTOList(null);

		// Assert
		Assertions.assertNull(dtoMapped);
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert nationalité en entité")
	public void testDTOToEntity_ReturnCompleteEntity() {
		// Arrange
		NationalityDTO dto = NationalityAssertions.getNationalityDTO();

		// Act
		Nationality entity = NationalityMapper.toEntity(dto);

		// Assert
		NationalityAssertions.assertEqualsProperties(entity, dto);
	}

	@Test
	@DisplayName("Test de conversion d'un objet de transfert nationalité null en entité")
	public void testDTOToEntity_ReturnNullEntity() {
		// Arrange set to Null + Act
		Nationality entity = NationalityMapper.toEntity(null);

		// Assert
		Assertions.assertNull(entity);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'objets de transfert nationalité en liste d'entités")
	public void testDTOListToEntityList_ReturnCompleteEntityList() {
		// Arrange
		TestDatas<Nationality, NationalityDTO> datas = NationalityAssertions.getNationalityTestDatas(6);

		// Act
		List<Nationality> entitiesMapped = NationalityMapper.toEntityList(datas.dtos);

		// Assert
		NationalityAssertions.assertListEntitiesDTOs(entitiesMapped, datas.dtos);
	}

	@Test
	@DisplayName("Test de conversion d'une liste d'objets de transfert nationalité null en liste d'entités")
	public void testDTOListToEntityList_ReturnNullEntityList() {
		// Arrange set to Null + Act
		List<Nationality> entitiesMapped = NationalityMapper.toEntityList(null);

		// Assert
		Assertions.assertNull(entitiesMapped);
	}

}
