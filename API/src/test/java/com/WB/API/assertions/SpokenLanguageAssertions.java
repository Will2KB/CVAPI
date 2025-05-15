package com.WB.API.assertions;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import com.WB.API.dto.SpokenLanguageDTO;
import com.WB.API.model.SpokenLanguage;

public class SpokenLanguageAssertions {

	/*
	 * Retourne une entité SpokenLanguage pour les tests
	 */
	public static SpokenLanguage getSpokenLanguage() {
		return new SpokenLanguage(25, 12, "Langugage", "B1");
	}

	/*
	 * Retourne un DTO SpokenLangage pour les tests
	 */
	public static SpokenLanguageDTO getSpokenLanguageDTO() {
		return new SpokenLanguageDTO(25, 12, "Language", "B1");
	}

	/*
	 * Retourne un objet de test avec une liste d'entité et une liste de DTO pour
	 * les tests
	 */
	public static TestDatas<SpokenLanguage, SpokenLanguageDTO> getSpokenLanguageTestDatas(int count) {
		List<SpokenLanguage> entities = new ArrayList<>();
		List<SpokenLanguageDTO> dtos = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			entities.add(new SpokenLanguage(12 + i, 21 + i, "Language" + i, "B" + i));
			dtos.add(new SpokenLanguageDTO(12 + i, 21 + i, "Language" + i, "B" + i));
		}

		return new TestDatas<>(entities, dtos);
	}

	/*
	 * Vérifie que l'objet DTO et ses propriétés ne sont pas null
	 */
	public static void assertNotNullDTO(SpokenLanguageDTO dto) {
		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto.getPersonId(), "L'ID de la personne du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getLanguageId(), "L'ID de la langue du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getName(), "Le nom du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getLevel(), "Le niveau du DTO ne doit pas être null");
	}

	/*
	 * Vérifie que l'objet entité et ses propriétés ne sont pas null
	 */
	public static void assertNotNullEntity(SpokenLanguage entity) {
		Assertions.assertNotNull(entity);
		Assertions.assertNotNull(entity.getLanguage());
		Assertions.assertNotNull(entity.getPerson());
		Assertions.assertNotNull(entity.getPerson().getId(), "L'ID de la personne de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getLanguage().getId(), "L'ID de la langue de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getLevel(), "Le niveau de l'entité ne doit pas être null");
	}

	/*
	 * Compare la valeur des propriétés de deux entités
	 */
	public static void assertEqualsProperties(SpokenLanguage language1, SpokenLanguage language2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		SpokenLanguageAssertions.assertNotNullEntity(language1);
		SpokenLanguageAssertions.assertNotNullEntity(language2);

		// On compare les propriétés
		Assertions.assertEquals(language1.getLanguage().getId(), language2.getLanguage().getId(),
				"L'ID de la langue n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(language1.getPerson().getId(), language2.getPerson().getId(),
				"L'ID de la personne n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(language1.getLevel(), language2.getLevel(),
				"Le niveau n'est pas cohérent entre les deux entités");
	}

	/*
	 * Compare la valeur des propriétés de d'une entité et d'un DTO
	 */
	public static void assertEqualsProperties(SpokenLanguage language1, SpokenLanguageDTO language2) {

		// On vérifie la nullité des objets avant d'accéder aux propriétés
		SpokenLanguageAssertions.assertNotNullEntity(language1);
		SpokenLanguageAssertions.assertNotNullDTO(language2);

		// On compare les propriétés
		Assertions.assertEquals(language1.getLanguage().getId(), language2.getLanguageId(),
				"L'ID de la langue n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(language1.getPerson().getId(), language2.getPersonId(),
				"L'ID de la personne n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(language1.getLevel(), language2.getLevel(),
				"Le niveau n'est pas cohérent entre les deux entités");
	}

	/*
	 * Compare la valeur des propriétés de deux DTO
	 */
	public static void assertEqualsProperties(SpokenLanguageDTO language1, SpokenLanguageDTO language2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		SpokenLanguageAssertions.assertNotNullDTO(language1);
		SpokenLanguageAssertions.assertNotNullDTO(language2);

		// On compare les propriétés
		Assertions.assertEquals(language1.getLanguageId(), language2.getLanguageId(),
				"L'ID de la langue n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(language1.getPersonId(), language2.getPersonId(),
				"L'ID de la personne n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(language1.getName(), language2.getName(),
				"Le nom n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(language1.getLevel(), language2.getLevel(),
				"Le niveau n'est pas cohérent entre les deux entités");
	}

	/*
	 * Compare les propriétés à partir de deux listes d'entités
	 */
	public static void assertListEntities(List<SpokenLanguage> entities1, List<SpokenLanguage> entities2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(entities2.size(), entities1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities1.size(); i++) {

			// On récupère les entités courantes
			SpokenLanguage actualEntity = entities1.get(i);
			SpokenLanguage expectedEntity = entities2.get(i);

			// On les compare
			SpokenLanguageAssertions.assertEqualsProperties(actualEntity, expectedEntity);
		}
	}

	/*
	 * Compare les propriétés à partir de d'une liste d'entités et d'une liste DTO
	 */
	public static void assertListEntitiesDTOs(List<SpokenLanguage> entities, List<SpokenLanguageDTO> dtos) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos.size(), entities.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities.size(); i++) {

			// On récupère les entités courantes
			SpokenLanguage actualEntity = entities.get(i);
			SpokenLanguageDTO expectedDTO = dtos.get(i);

			// On les compare
			SpokenLanguageAssertions.assertEqualsProperties(actualEntity, expectedDTO);
		}
	}

	/*
	 * Compare les propriétés à partir de deux listes DTO
	 */
	public static void assertListDTOs(List<SpokenLanguageDTO> dtos1, List<SpokenLanguageDTO> dtos2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos2.size(), dtos1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < dtos1.size(); i++) {

			// On récupère les entités courantes
			SpokenLanguageDTO actualDTO = dtos1.get(i);
			SpokenLanguageDTO expectedDTO = dtos2.get(i);

			// On les compare
			SpokenLanguageAssertions.assertEqualsProperties(actualDTO, expectedDTO);
		}
	}
}
