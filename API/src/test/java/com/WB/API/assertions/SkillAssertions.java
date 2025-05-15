package com.WB.API.assertions;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import com.WB.API.dto.SkillDTO;
import com.WB.API.model.Skill;

public class SkillAssertions {

	/*
	 * Retourne une entité Skill sans type de dompétence pour les tests
	 */
	public static Skill getSkill() {
		return new Skill(21, "Skill", true);
	}

	/*
	 * Retourne une entité Skill avec un type de compétence défini pour les tests
	 */
	public static Skill getSkillWithType() {
		Skill entity = SkillAssertions.getSkill();
		entity.setType(SkillTypeAssertions.getSkillType());
		return entity;
	}

	/*
	 * Retourne un DTO Skill pour les tests
	 */
	public static SkillDTO getSkillDTO() {
		return new SkillDTO(21, "Skill", true);
	}

	/*
	 * Retourne un objet de test avec une liste d'entité et une liste de DTO pour
	 * les tests
	 */
	public static TestDatas<Skill, SkillDTO> getSkillTestDatas(int count) {
		List<Skill> entities = new ArrayList<>();
		List<SkillDTO> dtos = new ArrayList<>();

		boolean isEnable = true;

		for (int i = 0; i < count; i++) {
			isEnable = !isEnable;
			entities.add(new Skill(i, "skill" + i, isEnable));
			dtos.add(new SkillDTO(i, "skill" + i, isEnable));
		}

		return new TestDatas<>(entities, dtos);
	}

	/*
	 * Vérifie que l'objet DTO et ses propriétés ne sont pas null
	 */
	public static void assertNotNullDTO(SkillDTO dto) {
		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto.getId(), "L'ID du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getName(), "Le nom du DTO ne doit pas être null");
	}

	/*
	 * Vérifie que l'objet entité et ses propriétés ne sont pas null
	 */
	public static void assertNotNullEntity(Skill entity) {
		Assertions.assertNotNull(entity, "L'entité mappée est null");
		Assertions.assertNotNull(entity.getId(), "L'ID de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getName(), "Le nom de l'entité ne doit pas être null");
	}

	/*
	 * Compare la valeur des propriétés de deux entités
	 */
	public static void assertEqualsProperties(Skill skill1, Skill skill2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		SkillAssertions.assertNotNullEntity(skill1);
		SkillAssertions.assertNotNullEntity(skill2);

		// On compare les propriétés
		Assertions.assertEquals(skill1.getId(), skill2.getId(), "L'ID mappé n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(skill1.getName(), skill2.getName(),
				"Le nom mappé n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(skill1.isEnable(), skill2.isEnable(),
				"La valeur de visibilité mappé n'est pas cohérent entre les deux entités");
	}

	/*
	 * Compare la valeur des propriétés de d'une entité et d'un DTO
	 */
	public static void assertEqualsProperties(Skill entity, SkillDTO dto) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		SkillAssertions.assertNotNullEntity(entity);
		SkillAssertions.assertNotNullDTO(dto);

		// On compare les propriétés
		Assertions.assertEquals(entity.getId(), dto.getId(), "L'ID mappé n'est pas cohérent entre l'entité et le DTO");
		Assertions.assertEquals(entity.getName(), dto.getName(),
				"Le nom mappé n'est pas cohérent entre l'entité et le DTO");
		Assertions.assertEquals(entity.isEnable(), dto.isEnable(),
				"La valeur de visibilité mappé n'est pas cohérent entre l'entité et le DTO");
	}

	/*
	 * Compare la valeur des propriétés de deux DTO
	 */
	public static void assertEqualsProperties(SkillDTO dto1, SkillDTO dto2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		SkillAssertions.assertNotNullDTO(dto1);
		SkillAssertions.assertNotNullDTO(dto2);

		// On compare les propriétés
		Assertions.assertEquals(dto1.getId(), dto2.getId(), "L'ID mappé n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(dto1.getName(), dto2.getName(), "Le nom mappé n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(dto1.isEnable(), dto2.isEnable(),
				"La valeur de visibilité mappé n'est pas cohérent entre les deux DTO");
	}

	/*
	 * Compare les propriétés à partir de deux listes d'entités
	 */
	public static void assertListEntites(List<Skill> entities1, List<Skill> entities2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(entities2.size(), entities1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities1.size(); i++) {
			// On récupère les entités courantes
			Skill actualEntity = entities1.get(i);
			Skill expectedEntity = entities2.get(i);

			// On les compare
			SkillAssertions.assertEqualsProperties(actualEntity, expectedEntity);
		}
	}

	/*
	 * Compare les propriétés à partir de d'une liste d'entités et d'une liste DTO
	 */
	public static void assertListEntitiesDTOs(List<Skill> entities, List<SkillDTO> dtos) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos.size(), entities.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities.size(); i++) {
			// On récupère les entités courantes
			Skill actualEntity = entities.get(i);
			SkillDTO expectedDTO = dtos.get(i);

			// On les compare
			SkillAssertions.assertEqualsProperties(actualEntity, expectedDTO);
		}
	}

	/*
	 * Compare les propriétés à partir de deux listes DTO
	 */
	public static void assertListDTOs(List<SkillDTO> dtos1, List<SkillDTO> dtos2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos2.size(), dtos1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < dtos1.size(); i++) {
			// On récupère les entités courantes
			SkillDTO actualDTO = dtos1.get(i);
			SkillDTO expectedDTO = dtos2.get(i);

			// On les compare
			SkillAssertions.assertEqualsProperties(actualDTO, expectedDTO);
		}
	}
}
