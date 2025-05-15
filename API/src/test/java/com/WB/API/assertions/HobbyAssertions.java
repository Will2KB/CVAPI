package com.WB.API.assertions;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import com.WB.API.dto.HobbyDTO;
import com.WB.API.model.Hobby;

public class HobbyAssertions {

	/*
	 * Retourne une entité Hobby pour les tests
	 */
	public static Hobby getHobby() {
		Hobby newHobby = new Hobby(25);
		newHobby.setName("Hobby");
		return newHobby;
	}

	/*
	 * Retourne un DTO Hobby pour les tests
	 */
	public static HobbyDTO getHobbyDTO() {
		return new HobbyDTO(25, "Hobby");
	}

	/*
	 * Retourne un objet de test avec une liste d'entité et une liste de DTO pour
	 * les tests
	 */
	public static TestDatas<Hobby, HobbyDTO> getHobbyTestDatas(int count) {
		List<Hobby> entities = new ArrayList<>();
		List<HobbyDTO> dtos = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			Hobby newEntity = new Hobby(i);
			newEntity.setName("Entity" + i);
			entities.add(newEntity);
			dtos.add(new HobbyDTO(i, "Entity" + i));
		}

		return new TestDatas<>(entities, dtos);
	}

	/*
	 * Vérifie que l'objet DTO et ses propriétés ne sont pas null
	 */
	public static void assertNotNullDTO(HobbyDTO dto) {
		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto.getId(), "L'ID du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getName(), "Le nom du DTO ne doit pas être null");
	}

	/*
	 * Vérifie que l'objet entité et ses propriétés ne sont pas null
	 */
	public static void assertNotNullEntity(Hobby entity) {
		Assertions.assertNotNull(entity);
		Assertions.assertNotNull(entity.getId(), "L'ID de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getName(), "Le nom de l'entité ne doit pas être null");
	}

	/*
	 * Compare la valeur des propriétés de deux entités
	 */
	public static void assertEqualsProperties(Hobby hobby1, Hobby hobby2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		HobbyAssertions.assertNotNullEntity(hobby1);
		HobbyAssertions.assertNotNullEntity(hobby2);

		// On compare les propriétés
		Assertions.assertEquals(hobby1.getId(), hobby2.getId(), "L'ID n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(hobby1.getName(), hobby2.getName(), "Le nom n'est pas cohérent entre les deux entités");
	}

	/*
	 * Compare la valeur des propriétés de d'une entité et d'un DTO
	 */
	public static void assertEqualsProperties(Hobby hobby1, HobbyDTO hobby2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		HobbyAssertions.assertNotNullEntity(hobby1);
		HobbyAssertions.assertNotNullDTO(hobby2);

		// On compare les propriétés
		Assertions.assertEquals(hobby1.getId(), hobby2.getId(), "L'ID n'est pas cohérent entre l'entité et le DTO");
		Assertions.assertEquals(hobby1.getName(), hobby2.getName(),
				"Le nom n'est pas cohérent entre les l'entité et le DTO");
	}

	/*
	 * Compare la valeur des propriétés de deux DTO
	 */
	public static void assertEqualsProperties(HobbyDTO hobby1, HobbyDTO hobby2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		HobbyAssertions.assertNotNullDTO(hobby1);
		HobbyAssertions.assertNotNullDTO(hobby2);

		// On compare les propriétés
		Assertions.assertEquals(hobby1.getId(), hobby2.getId(), "L'ID n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(hobby1.getName(), hobby2.getName(), "Le nom n'est pas cohérent entre les deux DTO");
	}

	/*
	 * Compare les propriétés à partir de deux listes d'entités
	 */
	public static void assertListEntities(List<Hobby> entities1, List<Hobby> entities2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(entities2.size(), entities1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities1.size(); i++) {
			// On récupère les entités courantes
			Hobby actualEntity = entities1.get(i);
			Hobby expectedEntity = entities2.get(i);

			// On les compare
			HobbyAssertions.assertEqualsProperties(actualEntity, expectedEntity);
		}
	}

	/*
	 * Compare les propriétés à partir de d'une liste d'entités et d'une liste DTO
	 */
	public static void assertListEntitiesDTOs(List<Hobby> entities, List<HobbyDTO> dtos) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos.size(), entities.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities.size(); i++) {
			// On récupère les entités courantes
			Hobby actualEntity = entities.get(i);
			HobbyDTO expectedDTO = dtos.get(i);

			// On les compare
			HobbyAssertions.assertEqualsProperties(actualEntity, expectedDTO);
		}
	}

	/*
	 * Compare les propriétés à partir de deux listes DTO
	 */
	public static void assertListDTOs(List<HobbyDTO> dtos1, List<HobbyDTO> dtos2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos2.size(), dtos1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < dtos1.size(); i++) {
			// On récupère les entités courantes
			HobbyDTO actualDTO = dtos1.get(i);
			HobbyDTO expectedDTO = dtos2.get(i);

			// On les compare
			HobbyAssertions.assertEqualsProperties(actualDTO, expectedDTO);
		}
	}
}
