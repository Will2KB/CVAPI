package com.WB.API.assertions;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import com.WB.API.dto.AddressDTO;
import com.WB.API.model.Address;

public class AddressAssertions {

	/*
	 * Retourne une entité Address sans sous objet pour les tests
	 */
	public static Address getAddress() {
		return new Address(25, 730, "Address", "Complement");
	}

	/*
	 * Retourne une entité Address avec une ville mais sans pays pour les tests
	 */
	public static Address getAddressWithCity() {
		Address newAddress = new Address(25, 730, "Address", "Complement");
		newAddress.setCity(CityAssertions.getCity());
		return newAddress;
	}

	/*
	 * Retourne une entité Address avec une ville et un payspour les tests
	 */
	public static Address getAddressWithCountry() {
		Address newAddress = new Address(25, 730, "Address", "Complement");
		newAddress.setCity(CityAssertions.getCityWithCountry());
		return newAddress;
	}

	/*
	 * Retourne un DTO Address pour les tests
	 */
	public static AddressDTO getAddressDTO() {
		return new AddressDTO(25, 730, "Address", "Complement");
	}

	/*
	 * Retourne un objet de test avec une liste d'entité et une liste de DTO pour
	 * les tests
	 */
	public static TestDatas<Address, AddressDTO> getAddressTestDatas(int count) {
		List<Address> entities = new ArrayList<>();
		List<AddressDTO> dtos = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			entities.add(new Address(i, 730 + i, "Address" + i, "7400" + i));
			dtos.add(new AddressDTO(i, 730 + i, "Address" + i, "7400" + i));
		}

		return new TestDatas<>(entities, dtos);
	}

	/*
	 * Vérifie que l'objet DTO et ses propriétés ne sont pas null
	 */
	public static void assertNotNullDTO(AddressDTO dto) {
		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto.getId(), "L'ID du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getStreetNumber(), "Le numéro de rue du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getStreet(), "Le nom de rue du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getComplement(), "Le complément d'adresse du DTO ne doit pas être null");
	}

	/*
	 * Vérifie que l'objet entité et ses propriétés ne sont pas null
	 */
	public static void assertNotNullEntity(Address entity) {
		Assertions.assertNotNull(entity);
		Assertions.assertNotNull(entity.getId(), "L'ID de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getStreetNumber(), "Le numéro de rue de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getStreet(), "Le nom de rue de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getComplement(), "Le complément d'adresse de l'entité ne doit pas être null");
	}

	/*
	 * Compare la valeur des propriétés de deux entités
	 */
	public static void assertEqualsProperties(Address address1, Address address2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		AddressAssertions.assertNotNullEntity(address1);
		AddressAssertions.assertNotNullEntity(address2);

		// On compare les propriétés
		Assertions.assertEquals(address1.getId(), address2.getId(), "L'ID n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(address1.getStreetNumber(), address2.getStreetNumber(),
				"Le numéro de rue n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(address1.getStreet(), address2.getStreet(),
				"Le nom de rue n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(address1.getComplement(), address2.getComplement(),
				"Le complément d'adresse n'est pas cohérent entre les deux entités");
	}

	/*
	 * Compare la valeur des propriétés de d'une entité et d'un DTO
	 */
	public static void assertEqualsProperties(Address address1, AddressDTO address2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		AddressAssertions.assertNotNullEntity(address1);
		AddressAssertions.assertNotNullDTO(address2);

		// On compare les propriétés
		Assertions.assertEquals(address1.getId(), address2.getId(), "L'ID n'est pas cohérent entre l'entité et le DTO");
		Assertions.assertEquals(address1.getStreetNumber(), address2.getStreetNumber(),
				"Le numéro de rue n'est pas cohérent entre les l'entité et le DTO");
		Assertions.assertEquals(address1.getStreet(), address2.getStreet(),
				"Le nom de rue n'est pas cohérent entre les l'entité et le DTO");
		Assertions.assertEquals(address1.getComplement(), address2.getComplement(),
				"Le complément d'adresse n'est pas cohérent entre les l'entité et le DTO");
	}

	/*
	 * Compare la valeur des propriétés de deux DTO
	 */
	public static void assertEqualsProperties(AddressDTO address1, AddressDTO address2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		AddressAssertions.assertNotNullDTO(address1);
		AddressAssertions.assertNotNullDTO(address2);

		// On compare les propriétés
		Assertions.assertEquals(address1.getId(), address2.getId(), "L'ID n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(address1.getStreetNumber(), address2.getStreetNumber(),
				"Le numéro de rue n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(address1.getStreet(), address2.getStreet(),
				"Le nom de rue n'est pas cohérent entre entre les deux DTO");
		Assertions.assertEquals(address1.getComplement(), address2.getComplement(),
				"Le complément d'adresse n'est pas cohérent entre entre les deux DTO");
	}

	/*
	 * Compare les propriétés à partir de deux listes d'entités
	 */
	public static void assertListEntities(List<Address> entities1, List<Address> entities2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(entities2.size(), entities1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities1.size(); i++) {
			// On récupère les entités courantes
			Address actualEntity = entities1.get(i);
			Address expectedEntity = entities2.get(i);

			// On les compare
			AddressAssertions.assertEqualsProperties(actualEntity, expectedEntity);
		}
	}

	/*
	 * Compare les propriétés à partir de d'une liste d'entités et d'une liste DTO
	 */
	public static void assertListEntitiesDTOs(List<Address> entities, List<AddressDTO> dtos) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos.size(), entities.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities.size(); i++) {
			// On récupère les entités courantes
			Address actualEntity = entities.get(i);
			AddressDTO expectedDTO = dtos.get(i);

			// On les compare
			AddressAssertions.assertEqualsProperties(actualEntity, expectedDTO);
		}
	}

	/*
	 * Compare les propriétés à partir de deux listes DTO
	 */
	public static void assertListDTOs(List<AddressDTO> dtos1, List<AddressDTO> dtos2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos2.size(), dtos1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < dtos1.size(); i++) {
			// On récupère les entités courantes
			AddressDTO actualDTO = dtos1.get(i);
			AddressDTO expectedDTO = dtos2.get(i);

			// On les compare
			AddressAssertions.assertEqualsProperties(actualDTO, expectedDTO);
		}
	}
}
