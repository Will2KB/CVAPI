package com.WB.API.assertions;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import com.WB.API.dto.CountryDTO;
import com.WB.API.model.Country;

public class CountryAssertions {

	/*
	 * Retourne une entité Country pour les tests
	 */
	public static Country getCountry() {
		return new Country(25, "Country");
	}

	/*
	 * Retourne un DTO Country pour les tests
	 */
	public static CountryDTO getCountryDTO() {
		return new CountryDTO(25, "Country");
	}

	/*
	 * Retourne un objet de test avec une liste d'entité et une liste de DTO pour
	 * les tests
	 */
	public static TestDatas<Country, CountryDTO> getCountryTestDatas(int count) {
		List<Country> entities = new ArrayList<>();
		List<CountryDTO> dtos = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			entities.add(new Country(i, "Country" + i));
			dtos.add(new CountryDTO(i, "Country" + i));
		}

		return new TestDatas<>(entities, dtos);
	}

	/*
	 * Vérifie que l'objet DTO et ses propriétés ne sont pas null
	 */
	public static void assertNotNullDTO(CountryDTO dto) {
		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto.getId(), "L'ID du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getName(), "Le nom du DTO ne doit pas être null");
	}

	/*
	 * Vérifie que l'objet entité et ses propriétés ne sont pas null
	 */
	public static void assertNotNullEntity(Country entity) {
		Assertions.assertNotNull(entity);
		Assertions.assertNotNull(entity.getId(), "L'ID de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getName(), "Le nom de l'entité ne doit pas être null");
	}

	/*
	 * Compare la valeur des propriétés de deux entités
	 */
	public static void assertEqualsProperties(Country country1, Country country2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		CountryAssertions.assertNotNullEntity(country1);
		CountryAssertions.assertNotNullEntity(country2);

		// On compare les propriétés
		Assertions.assertEquals(country1.getId(), country2.getId(), "L'ID n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(country1.getName(), country2.getName(),
				"Le nom n'est pas cohérent entre les deux entités");
	}

	/*
	 * Compare la valeur des propriétés de d'une entité et d'un DTO
	 */
	public static void assertEqualsProperties(Country country1, CountryDTO country2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		CountryAssertions.assertNotNullEntity(country1);
		CountryAssertions.assertNotNullDTO(country2);

		// On compare les propriétés
		Assertions.assertEquals(country1.getId(), country2.getId(), "L'ID n'est pas cohérent entre l'entité et le DTO");
		Assertions.assertEquals(country1.getName(), country2.getName(),
				"Le nom n'est pas cohérent entre les l'entité et le DTO");
	}

	/*
	 * Compare la valeur des propriétés de deux DTO
	 */
	public static void assertEqualsProperties(CountryDTO country1, CountryDTO country2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		CountryAssertions.assertNotNullDTO(country1);
		CountryAssertions.assertNotNullDTO(country2);

		// On compare les propriétés
		Assertions.assertEquals(country1.getId(), country2.getId(), "L'ID n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(country1.getName(), country2.getName(), "Le nom n'est pas cohérent entre les deux DTO");
	}

	/*
	 * Compare les propriétés à partir de deux listes d'entités
	 */
	public static void assertListEntities(List<Country> entities1, List<Country> entities2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(entities2.size(), entities1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities1.size(); i++) {
			// On récupère les entités courantes
			Country actualEntity = entities1.get(i);
			Country expectedEntity = entities2.get(i);

			// On les compare
			CountryAssertions.assertEqualsProperties(actualEntity, expectedEntity);
		}
	}

	/*
	 * Compare les propriétés à partir de d'une liste d'entités et d'une liste DTO
	 */
	public static void assertListEntitiesDTOs(List<Country> entities, List<CountryDTO> dtos) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos.size(), entities.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities.size(); i++) {
			// On récupère les entités courantes
			Country actualEntity = entities.get(i);
			CountryDTO expectedDTO = dtos.get(i);

			// On les compare
			CountryAssertions.assertEqualsProperties(actualEntity, expectedDTO);
		}
	}

	/*
	 * Compare les propriétés à partir de deux listes DTO
	 */
	public static void assertListDTOs(List<CountryDTO> entities, List<CountryDTO> dtos) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos.size(), entities.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities.size(); i++) {
			// On récupère les entités courantes
			CountryDTO actualDTO = entities.get(i);
			CountryDTO expectedDTO = dtos.get(i);

			// On les compare
			CountryAssertions.assertEqualsProperties(actualDTO, expectedDTO);
		}
	}
}
