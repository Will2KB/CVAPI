package com.WB.API.assertions;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import com.WB.API.dto.SkillTypeDTO;
import com.WB.API.model.SkillType;

public class SkillTypeAssertions {

	/*
	 * Retourne une entité SkillType pour les tests
	 */
	public static SkillType getSkillType() {
		SkillType newSkillType = new SkillType(21);
		newSkillType.setName("SkillType");
		return newSkillType;
	}

	/*
	 * Retourne un DTO SkillType pour les tests
	 */
	public static SkillTypeDTO getSkillTypeDTO() {
		return new SkillTypeDTO(21, "SkillType");
	}

	/*
	 * Retourne un objet de test avec une liste d'entité et une liste de DTO pour
	 * les tests
	 */
	public static TestDatas<SkillType, SkillTypeDTO> getSkillTypeTestDatas(int count) {
		List<SkillType> entities = new ArrayList<>();
		List<SkillTypeDTO> dtos = new ArrayList<>();

		boolean isEnable = true;

		for (int i = 0; i < count; i++) {
			isEnable = !isEnable;
			SkillType skillTypeEntity = new SkillType(i);
			skillTypeEntity.setName("Technique" + i);
			entities.add(skillTypeEntity);
			dtos.add(new SkillTypeDTO(i, "SkillType" + i));
		}

		return new TestDatas<>(entities, dtos);
	}

	/*
	 * Vérifie que l'objet DTO et ses propriétés ne sont pas null
	 */
	public static void assertNotNullDTO(SkillTypeDTO dto) {
		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto.getId(), "L'ID du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getName(), "Le nom du DTO ne doit pas être null");
	}

	/*
	 * Vérifie que l'objet entité et ses propriétés ne sont pas null
	 */
	public static void assertNotNullEntity(SkillType entity) {
		Assertions.assertNotNull(entity, "L'entité mappée est null");
		Assertions.assertNotNull(entity.getId(), "L'ID de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getName(), "Le nom de l'entité ne doit pas être null");
	}

	/*
	 * Compare la valeur des propriétés de deux entités
	 */
	public static void assertEqualsProperties(SkillType skillType1, SkillType skillType2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		SkillTypeAssertions.assertNotNullEntity(skillType1);
		SkillTypeAssertions.assertNotNullEntity(skillType2);

		// On compare les propriétés
		Assertions.assertEquals(skillType1.getId(), skillType2.getId(),
				"L'ID mappé n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(skillType1.getName(), skillType2.getName(),
				"Le nom mappé n'est pas cohérent entre les deux entités");
	}

	/*
	 * Compare la valeur des propriétés de d'une entité et d'un DTO
	 */
	public static void assertEqualsProperties(SkillType entity, SkillTypeDTO dto) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		SkillTypeAssertions.assertNotNullEntity(entity);
		SkillTypeAssertions.assertNotNullDTO(dto);

		// On compare les propriétés
		Assertions.assertEquals(entity.getId(), dto.getId(), "L'ID mappé n'est pas cohérent entre l'entité et le DTO");
		Assertions.assertEquals(entity.getName(), dto.getName(),
				"Le nom mappé n'est pas cohérent entre l'entité et le DTO");
	}

	/*
	 * Compare la valeur des propriétés de deux DTO
	 */
	public static void assertEqualsProperties(SkillTypeDTO dto1, SkillTypeDTO dto2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		SkillTypeAssertions.assertNotNullDTO(dto1);
		SkillTypeAssertions.assertNotNullDTO(dto2);

		// On compare les propriétés
		Assertions.assertEquals(dto1.getId(), dto2.getId(), "L'ID mappé n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(dto1.getName(), dto2.getName(), "Le nom mappé n'est pas cohérent entre les deux DTO");
	}

	/*
	 * Compare les propriétés à partir de deux listes d'entités
	 */
	public static void assertListEntites(List<SkillType> entities1, List<SkillType> entities2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(entities2.size(), entities1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities1.size(); i++) {
			// On récupère les entités courantes
			SkillType actualEntity = entities1.get(i);
			SkillType expectedEntity = entities2.get(i);

			// On les compare
			SkillTypeAssertions.assertEqualsProperties(actualEntity, expectedEntity);
		}
	}

	/*
	 * Compare les propriétés à partir de d'une liste d'entités et d'une liste DTO
	 */
	public static void assertListEntitiesDTOs(List<SkillType> entities, List<SkillTypeDTO> dtos) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos.size(), entities.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities.size(); i++) {
			// On récupère les entités courantes
			SkillType actualEntity = entities.get(i);
			SkillTypeDTO expectedDTO = dtos.get(i);

			// On les compare
			SkillTypeAssertions.assertEqualsProperties(actualEntity, expectedDTO);
		}
	}

	/*
	 * Compare les propriétés à partir de deux listes DTO
	 */
	public static void assertListDTOs(List<SkillTypeDTO> dtos1, List<SkillTypeDTO> dtos2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos2.size(), dtos1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < dtos1.size(); i++) {
			// On récupère les entités courantes
			SkillTypeDTO actualDTO = dtos1.get(i);
			SkillTypeDTO expectedDTO = dtos2.get(i);

			// On les compare
			SkillTypeAssertions.assertEqualsProperties(actualDTO, expectedDTO);
		}
	}
}
