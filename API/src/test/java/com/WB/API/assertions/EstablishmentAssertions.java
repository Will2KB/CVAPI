package com.WB.API.assertions;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import com.WB.API.dto.EstablishmentDTO;
import com.WB.API.model.Establishment;

public class EstablishmentAssertions {

	/*
	 * Retourne une entité Establishment sans sous objet défini pour les tests
	 */
	public static Establishment getEstablishment() {
		return new Establishment(25, "Establishment", 30);
	}

	/*
	 * Retourne une entité Establishment avec une adresse définie pour les tests
	 */
	public static Establishment getEstablishmentWithAddress() {
		Establishment entity = EstablishmentAssertions.getEstablishment();
		entity.setAddress(AddressAssertions.getAddress());
		return entity;
	}

	/*
	 * Retourne une entité Establishment avec une ville définie mais sans pays pour
	 * les tests
	 */
	public static Establishment getEstablishmentWithCity() {
		Establishment entity = EstablishmentAssertions.getEstablishmentWithAddress();
		entity.setAddress(AddressAssertions.getAddressWithCity());
		return entity;
	}

	/*
	 * Retourne une entité Establishment avec une ville et un pays définis pour les
	 * tests
	 */
	public static Establishment getEstablishmentWithCountry() {
		Establishment entity = EstablishmentAssertions.getEstablishmentWithCity();
		entity.setAddress(AddressAssertions.getAddressWithCountry());
		return entity;
	}

	/*
	 * Retourne un DTO Establishment pour les tests
	 */
	public static EstablishmentDTO getEstablishmentDTO() {
		return new EstablishmentDTO(25, "Establishment", 30);
	}

	/*
	 * Retourne un objet de test avec une liste d'entité et une liste de DTO pour
	 * les tests
	 */
	public static TestDatas<Establishment, EstablishmentDTO> getEstablishmentTestDatas(int count) {
		List<Establishment> entities = new ArrayList<>();
		List<EstablishmentDTO> dtos = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			entities.add(new Establishment(i, "Establishment" + i, 74000 + i));
			dtos.add(new EstablishmentDTO(i, "Establishment" + i, 74000 + i));
		}

		return new TestDatas<>(entities, dtos);
	}

	/*
	 * Vérifie que l'objet DTO et ses propriétés ne sont pas null
	 */
	public static void assertNotNullDTO(EstablishmentDTO dto) {
		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto.getId(), "L'ID du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getName(), "Le nom du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getAddressId(), "L'ID de l'adresse du DTO ne doit pas être null");
	}

	/*
	 * Vérifie que l'objet entité et ses propriétés ne sont pas null
	 */
	public static void assertNotNullEntity(Establishment entity) {
		Assertions.assertNotNull(entity);
		Assertions.assertNotNull(entity.getAddress());
		Assertions.assertNotNull(entity.getId(), "L'ID de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getName(), "Le nom de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getAddress().getId(), "L'ID de l'adresse de l'entité ne doit pas être null");
	}

	/*
	 * Compare la valeur des propriétés de deux entités
	 */
	public static void assertEqualsProperties(Establishment establishment1, Establishment establishment2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		EstablishmentAssertions.assertNotNullEntity(establishment1);
		EstablishmentAssertions.assertNotNullEntity(establishment2);

		// On compare les propriétés
		Assertions.assertEquals(establishment1.getId(), establishment2.getId(),
				"L'ID n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(establishment1.getName(), establishment2.getName(),
				"Le nom n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(establishment1.getAddress().getId(), establishment2.getAddress().getId(),
				"L'ID de l'adresse n'est pas cohérent entre les deux entités");
	}

	/*
	 * Compare la valeur des propriétés de d'une entité et d'un DTO
	 */
	public static void assertEqualsProperties(Establishment establishment1, EstablishmentDTO establishment2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		EstablishmentAssertions.assertNotNullEntity(establishment1);
		EstablishmentAssertions.assertNotNullDTO(establishment2);

		// On compare les propriétés
		Assertions.assertEquals(establishment1.getId(), establishment2.getId(),
				"L'ID n'est pas cohérent entre l'entité et le DTO");
		Assertions.assertEquals(establishment1.getName(), establishment2.getName(),
				"Le nom n'est pas cohérent entre les l'entité et le DTO");
		Assertions.assertEquals(establishment1.getAddress().getId(), establishment2.getAddressId(),
				"L'ID de l'adresse n'est pas cohérent entre les l'entité et le DTO");
	}

	/*
	 * Compare la valeur des propriétés de deux DTO
	 */
	public static void assertEqualsProperties(EstablishmentDTO establishment1, EstablishmentDTO establishment2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		EstablishmentAssertions.assertNotNullDTO(establishment1);
		EstablishmentAssertions.assertNotNullDTO(establishment2);

		// On compare les propriétés
		Assertions.assertEquals(establishment1.getId(), establishment2.getId(),
				"L'ID n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(establishment1.getName(), establishment2.getName(),
				"Le nom n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(establishment1.getAddressId(), establishment2.getAddressId(),
				"L'ID de l'adresse n'est pas cohérent entre entre les deux DTO");
	}

	/*
	 * Compare les propriétés à partir de deux listes d'entités
	 */
	public static void assertListEntities(List<Establishment> entities1, List<Establishment> entities2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(entities2.size(), entities1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities1.size(); i++) {
			// On récupère les entités courantes
			Establishment actualEntity = entities1.get(i);
			Establishment expectedEntity = entities2.get(i);

			// On les compare
			EstablishmentAssertions.assertEqualsProperties(actualEntity, expectedEntity);
		}
	}

	/*
	 * Compare les propriétés à partir de d'une liste d'entités et d'une liste DTO
	 */
	public static void assertListEntitiesDTOs(List<Establishment> entities, List<EstablishmentDTO> dtos) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos.size(), entities.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities.size(); i++) {
			// On récupère les entités courantes
			Establishment actualEntity = entities.get(i);
			EstablishmentDTO expectedDTO = dtos.get(i);

			// On les compare
			EstablishmentAssertions.assertEqualsProperties(actualEntity, expectedDTO);
		}
	}

	/*
	 * Compare les propriétés à partir de deux listes DTO
	 */
	public static void assertListDTOs(List<EstablishmentDTO> dtos1, List<EstablishmentDTO> dtos2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos2.size(), dtos1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < dtos1.size(); i++) {
			// On récupère les entités courantes
			EstablishmentDTO actualDTO = dtos1.get(i);
			EstablishmentDTO expectedDTO = dtos2.get(i);

			// On les compare
			EstablishmentAssertions.assertEqualsProperties(actualDTO, expectedDTO);
		}
	}
}
