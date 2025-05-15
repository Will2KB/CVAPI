package com.WB.API.assertions;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import com.WB.API.dto.NationalityDTO;
import com.WB.API.model.Nationality;

public class NationalityAssertions {

	/*
	 * Retourne une entité Nationality pour les tests
	 */
	public static Nationality getNationality() {
		return new Nationality(25, "Nationality");
	}

	/*
	 * Retourne un DTO Nationality pour les tests
	 */
	public static NationalityDTO getNationalityDTO() {
		return new NationalityDTO(25, "Nationality");
	}

	/*
	 * Retourne un objet de test avec une liste d'entité et une liste de DTO pour
	 * les tests
	 */
	public static TestDatas<Nationality, NationalityDTO> getNationalityTestDatas(int count) {
		List<Nationality> entities = new ArrayList<>();
		List<NationalityDTO> dtos = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			entities.add(new Nationality(i, "Nationality" + i));
			dtos.add(new NationalityDTO(i, "Nationality" + i));
		}

		return new TestDatas<>(entities, dtos);
	}

	/*
	 * Vérifie que l'objet DTO et ses propriétés ne sont pas null
	 */
	public static void assertNotNullDTO(NationalityDTO dto) {
		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto.getId(), "L'ID du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getName(), "Le nom du DTO ne doit pas être null");
	}

	/*
	 * Vérifie que l'objet entité et ses propriétés ne sont pas null
	 */
	public static void assertNotNullEntity(Nationality entity) {
		Assertions.assertNotNull(entity);
		Assertions.assertNotNull(entity.getId(), "L'ID de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getName(), "Le nom de l'entité ne doit pas être null");
	}

	/*
	 * Compare la valeur des propriétés de deux entités
	 */
	public static void assertEqualsProperties(Nationality nationality1, Nationality nationality2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		NationalityAssertions.assertNotNullEntity(nationality1);
		NationalityAssertions.assertNotNullEntity(nationality2);

		// On compare les propriétés
		Assertions.assertEquals(nationality1.getId(), nationality2.getId(),
				"L'ID n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(nationality1.getName(), nationality2.getName(),
				"Le nom n'est pas cohérent entre les deux entités");
	}

	/*
	 * Compare la valeur des propriétés de d'une entité et d'un DTO
	 */
	public static void assertEqualsProperties(Nationality nationality1, NationalityDTO nationality2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		NationalityAssertions.assertNotNullEntity(nationality1);
		NationalityAssertions.assertNotNullDTO(nationality2);

		// On compare les propriétés
		Assertions.assertEquals(nationality1.getId(), nationality2.getId(),
				"L'ID n'est pas cohérent entre l'entité et le DTO");
		Assertions.assertEquals(nationality1.getName(), nationality2.getName(),
				"Le nom n'est pas cohérent entre les l'entité et le DTO");
	}

	/*
	 * Compare la valeur des propriétés de deux DTO
	 */
	public static void assertEqualsProperties(NationalityDTO nationality1, NationalityDTO nationality2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		NationalityAssertions.assertNotNullDTO(nationality1);
		NationalityAssertions.assertNotNullDTO(nationality2);

		// On compare les propriétés
		Assertions.assertEquals(nationality1.getId(), nationality2.getId(),
				"L'ID n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(nationality1.getName(), nationality2.getName(),
				"Le nom n'est pas cohérent entre les deux DTO");
	}

	/*
	 * Compare les propriétés à partir de deux listes d'entités
	 */
	public static void assertListEntities(List<Nationality> entities1, List<Nationality> entities2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(entities2.size(), entities1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities1.size(); i++) {
			// On récupère les entités courantes
			Nationality actualEntity = entities1.get(i);
			Nationality expectedEntity = entities2.get(i);

			// On les compare
			NationalityAssertions.assertEqualsProperties(actualEntity, expectedEntity);
		}
	}

	/*
	 * Compare les propriétés à partir de d'une liste d'entités et d'une liste DTO
	 */
	public static void assertListEntitiesDTOs(List<Nationality> entities, List<NationalityDTO> dtos) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos.size(), entities.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities.size(); i++) {
			// On récupère les entités courantes
			Nationality actualEntity = entities.get(i);
			NationalityDTO expectedDTO = dtos.get(i);

			// On les compare
			NationalityAssertions.assertEqualsProperties(actualEntity, expectedDTO);
		}
	}

	/*
	 * Compare les propriétés à partir de deux listes DTO
	 */
	public static void assertListDTOs(List<NationalityDTO> dtos1, List<NationalityDTO> dtos2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos2.size(), dtos1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < dtos1.size(); i++) {
			// On récupère les entités courantes
			NationalityDTO actualDTO = dtos1.get(i);
			NationalityDTO expectedDTO = dtos2.get(i);

			// On les compare
			NationalityAssertions.assertEqualsProperties(actualDTO, expectedDTO);
		}
	}
}
