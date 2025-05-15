package com.WB.API.assertions;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import com.WB.API.dto.HobbyDescriptionDTO;
import com.WB.API.model.HobbyDescription;

public class HobbyDescriptionAssertions {

	/*
	 * Retourne une entité HobbyDescription pour les tests
	 */
	public static HobbyDescription getHobby() {
		return new HobbyDescription(25, "Hobby");
	}

	/*
	 * Retourne un DTO HobbyDescription pour les tests
	 */
	public static HobbyDescriptionDTO getHobbyDescriptionDTO() {
		return new HobbyDescriptionDTO(25, "Hobby");
	}

	/*
	 * Retourne un objet de test avec une liste d'entité et une liste de DTO pour
	 * les tests
	 */
	public static TestDatas<HobbyDescription, HobbyDescriptionDTO> getHobbyDescriptionTestDatas(int count) {
		List<HobbyDescription> entities = new ArrayList<>();
		List<HobbyDescriptionDTO> dtos = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			entities.add(new HobbyDescription(i, "Entity" + i));
			dtos.add(new HobbyDescriptionDTO(i, "Entity" + i));
		}

		return new TestDatas<>(entities, dtos);
	}

	/*
	 * Vérifie que l'objet DTO et ses propriétés ne sont pas null
	 */
	public static void assertNotNullDTO(HobbyDescriptionDTO dto) {
		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto.getId(), "L'ID du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getDescription(), "La description du DTO ne doit pas être null");
	}

	/*
	 * Vérifie que l'objet entité et ses propriétés ne sont pas null
	 */
	public static void assertNotNullEntity(HobbyDescription entity) {
		Assertions.assertNotNull(entity);
		Assertions.assertNotNull(entity.getId(), "L'ID de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getDescription(), "La description de l'entité ne doit pas être null");
	}

	/*
	 * Compare la valeur des propriétés de deux entités
	 */
	public static void assertEqualsProperties(HobbyDescription hobby1, HobbyDescription hobby2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		HobbyDescriptionAssertions.assertNotNullEntity(hobby1);
		HobbyDescriptionAssertions.assertNotNullEntity(hobby2);

		// On compare les propriétés
		Assertions.assertEquals(hobby1.getId(), hobby2.getId(), "L'ID n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(hobby1.getDescription(), hobby2.getDescription(),
				"La description n'est pas cohérente entre les deux entités");
	}

	/*
	 * Compare la valeur des propriétés de d'une entité et d'un DTO
	 */
	public static void assertEqualsProperties(HobbyDescription hobby1, HobbyDescriptionDTO hobby2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		HobbyDescriptionAssertions.assertNotNullEntity(hobby1);
		HobbyDescriptionAssertions.assertNotNullDTO(hobby2);

		// On compare les propriétés
		Assertions.assertEquals(hobby1.getId(), hobby2.getId(), "L'ID n'est pas cohérent entre l'entité et le DTO");
		Assertions.assertEquals(hobby1.getDescription(), hobby2.getDescription(),
				"La description n'est pas cohérente entre les l'entité et le DTO");
	}

	/*
	 * Compare la valeur des propriétés de deux DTO
	 */
	public static void assertEqualsProperties(HobbyDescriptionDTO hobby1, HobbyDescriptionDTO hobby2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		HobbyDescriptionAssertions.assertNotNullDTO(hobby1);
		HobbyDescriptionAssertions.assertNotNullDTO(hobby2);

		// On compare les propriétés
		Assertions.assertEquals(hobby1.getId(), hobby2.getId(), "L'ID n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(hobby1.getDescription(), hobby2.getDescription(),
				"La description n'est pas cohérente entre les deux DTO");
	}

	/*
	 * Compare les propriétés à partir de deux listes d'entités
	 */
	public static void assertListEntities(List<HobbyDescription> entities1, List<HobbyDescription> entities2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(entities2.size(), entities1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities1.size(); i++) {
			// On récupère les entités courantes
			HobbyDescription actualEntity = entities1.get(i);
			HobbyDescription expectedEntity = entities2.get(i);

			// On les compare
			HobbyDescriptionAssertions.assertEqualsProperties(actualEntity, expectedEntity);
		}
	}

	/*
	 * Compare les propriétés à partir de d'une liste d'entités et d'une liste DTO
	 */
	public static void assertListEntitiesDTOs(List<HobbyDescription> entities, List<HobbyDescriptionDTO> dtos) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos.size(), entities.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities.size(); i++) {
			// On récupère les entités courantes
			HobbyDescription actualEntity = entities.get(i);
			HobbyDescriptionDTO expectedDTO = dtos.get(i);

			// On les compare
			HobbyDescriptionAssertions.assertEqualsProperties(actualEntity, expectedDTO);
		}
	}

	/*
	 * Compare les propriétés à partir de deux listes DTO
	 */
	public static void assertListDTOs(List<HobbyDescriptionDTO> dtos1, List<HobbyDescriptionDTO> dtos2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos2.size(), dtos1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < dtos1.size(); i++) {
			// On récupère les entités courantes
			HobbyDescriptionDTO actualDTO = dtos1.get(i);
			HobbyDescriptionDTO expectedDTO = dtos2.get(i);

			// On les compare
			HobbyDescriptionAssertions.assertEqualsProperties(actualDTO, expectedDTO);
		}
	}
}
