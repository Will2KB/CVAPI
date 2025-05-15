package com.WB.API.assertions;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import com.WB.API.dto.LanguageDTO;
import com.WB.API.model.Language;

public class LanguageAssertions {

	/*
	 * Retourne une entité Language pour les tests
	 */
	public static Language getLanguage() {
		return new Language(25, "Language");
	}

	/*
	 * Retourne un DTO Language pour les tests
	 */
	public static LanguageDTO getLanguageDTO() {
		return new LanguageDTO(25, "Language");
	}

	/*
	 * Retourne un objet de test avec une liste d'entité et une liste de DTO pour
	 * les tests
	 */
	public static TestDatas<Language, LanguageDTO> getLanguageTestDatas(int count) {
		List<Language> entities = new ArrayList<>();
		List<LanguageDTO> dtos = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			entities.add(new Language(i, "Language" + i));
			dtos.add(new LanguageDTO(i, "Language" + i));
		}

		return new TestDatas<>(entities, dtos);
	}

	/*
	 * Vérifie que l'objet DTO et ses propriétés ne sont pas null
	 */
	public static void assertNotNullDTO(LanguageDTO dto) {
		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto.getId(), "L'ID du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getName(), "Le nom du DTO ne doit pas être null");
	}

	/*
	 * Vérifie que l'objet entité et ses propriétés ne sont pas null
	 */
	public static void assertNotNullEntity(Language entity) {
		Assertions.assertNotNull(entity);
		Assertions.assertNotNull(entity.getId(), "L'ID de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getName(), "Le nom de l'entité ne doit pas être null");
	}

	/*
	 * Compare la valeur des propriétés de deux entités
	 */
	public static void assertEqualsProperties(Language language1, Language language2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		LanguageAssertions.assertNotNullEntity(language1);
		LanguageAssertions.assertNotNullEntity(language2);

		// On compare les propriétés
		Assertions.assertEquals(language1.getId(), language2.getId(), "L'ID n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(language1.getName(), language2.getName(),
				"Le nom n'est pas cohérent entre les deux entités");
	}

	/*
	 * Compare la valeur des propriétés de d'une entité et d'un DTO
	 */
	public static void assertEqualsProperties(Language language1, LanguageDTO language2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		LanguageAssertions.assertNotNullEntity(language1);
		LanguageAssertions.assertNotNullDTO(language2);

		// On compare les propriétés
		Assertions.assertEquals(language1.getId(), language2.getId(),
				"L'ID n'est pas cohérent entre l'entité et le DTO");
		Assertions.assertEquals(language1.getName(), language2.getName(),
				"Le nom n'est pas cohérent entre les l'entité et le DTO");
	}

	/*
	 * Compare la valeur des propriétés de deux DTO
	 */
	public static void assertEqualsProperties(LanguageDTO language1, LanguageDTO language2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		LanguageAssertions.assertNotNullDTO(language1);
		LanguageAssertions.assertNotNullDTO(language2);

		// On compare les propriétés
		Assertions.assertEquals(language1.getId(), language2.getId(), "L'ID n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(language1.getName(), language2.getName(),
				"Le nom n'est pas cohérent entre les deux DTO");
	}

	/*
	 * Compare les propriétés à partir de deux listes d'entités
	 */
	public static void assertListEntities(List<Language> entities1, List<Language> entities2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(entities2.size(), entities1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities1.size(); i++) {
			// On récupère les entités courantes
			Language actualEntity = entities1.get(i);
			Language expectedEntity = entities2.get(i);

			// On les compare
			LanguageAssertions.assertEqualsProperties(actualEntity, expectedEntity);
		}
	}

	/*
	 * Compare les propriétés à partir de d'une liste d'entités et d'une liste DTO
	 */
	public static void assertListEntitiesDTOs(List<Language> entities, List<LanguageDTO> dtos) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos.size(), entities.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities.size(); i++) {
			// On récupère les entités courantes
			Language actualEntity = entities.get(i);
			LanguageDTO expectedDTO = dtos.get(i);

			// On les compare
			LanguageAssertions.assertEqualsProperties(actualEntity, expectedDTO);
		}
	}

	/*
	 * Compare les propriétés à partir de deux listes DTO
	 */
	public static void assertListDTOs(List<LanguageDTO> dtos1, List<LanguageDTO> dtos2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos2.size(), dtos1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < dtos1.size(); i++) {
			// On récupère les entités courantes
			LanguageDTO actualDTO = dtos1.get(i);
			LanguageDTO expectedDTO = dtos2.get(i);

			// On les compare
			LanguageAssertions.assertEqualsProperties(actualDTO, expectedDTO);
		}
	}
}
