package com.WB.API.assertions;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;

import com.WB.API.dto.CityDTO;
import com.WB.API.model.City;

public class CityAssertions {

	/*
	 * Retourne une entité City sans pays pour les tests
	 */
	public static City getCity() {
		return new City(25, "City", 74130);
	}

	/*
	 * Retourne une entité City avec un pays pour les tests
	 */
	public static City getCityWithCountry() {
		City entity = CityAssertions.getCity();
		entity.setCountry(CountryAssertions.getCountry());
		return entity;
	}

	/*
	 * Retourne un DTO City pour les tests
	 */
	public static CityDTO getCityDTO() {
		return new CityDTO(25, "City", 74130);
	}

	/*
	 * Retourne un objet de test avec une liste d'entité et une liste de DTO pour
	 * les tests
	 */
	public static TestDatas<City, CityDTO> getCityTestDatas(int count) {
		List<City> entities = new ArrayList<>();
		List<CityDTO> dtos = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			entities.add(new City(i, "City" + i, 74000 + i));
			dtos.add(new CityDTO(i, "City" + i, 74000 + i));
		}

		return new TestDatas<>(entities, dtos);
	}

	/*
	 * Vérifie que l'objet DTO et ses propriétés ne sont pas null
	 */
	public static void assertNotNullDTO(CityDTO dto) {
		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto.getId(), "L'ID du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getName(), "Le nom du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getZipCode(), "Le code postale du DTO ne doit pas être null");
	}

	/*
	 * Vérifie que l'objet entité et ses propriétés ne sont pas null
	 */
	public static void assertNotNullEntity(City entity) {
		Assertions.assertNotNull(entity);
		Assertions.assertNotNull(entity.getId(), "L'ID de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getName(), "Le nom de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getZipCode(), "Le code postale de l'entité ne doit pas être null");
	}

	/*
	 * Compare la valeur des propriétés de deux entités
	 */
	public static void assertEqualsProperties(City city1, City city2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		CityAssertions.assertNotNullEntity(city1);
		CityAssertions.assertNotNullEntity(city2);

		// On compare les propriétés
		Assertions.assertEquals(city1.getId(), city2.getId(), "L'ID n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(city1.getName(), city2.getName(), "Le nom n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(city1.getZipCode(), city2.getZipCode(),
				"Le code postal n'est pas cohérent entre les deux entités");
	}

	/*
	 * Compare la valeur des propriétés de d'une entité et d'un DTO
	 */
	public static void assertEqualsProperties(City city1, CityDTO city2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		CityAssertions.assertNotNullEntity(city1);
		CityAssertions.assertNotNullDTO(city2);

		// On compare les propriétés
		Assertions.assertEquals(city1.getId(), city2.getId(), "L'ID n'est pas cohérent entre l'entité et le DTO");
		Assertions.assertEquals(city1.getName(), city2.getName(),
				"Le nom n'est pas cohérent entre les l'entité et le DTO");
		Assertions.assertEquals(city1.getZipCode(), city2.getZipCode(),
				"Le code postal n'est pas cohérent entre les l'entité et le DTO");
	}

	/*
	 * Compare la valeur des propriétés de deux DTO
	 */
	public static void assertEqualsProperties(CityDTO city1, CityDTO city2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		CityAssertions.assertNotNullDTO(city1);
		CityAssertions.assertNotNullDTO(city2);

		// On compare les propriétés
		Assertions.assertEquals(city1.getId(), city2.getId(), "L'ID n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(city1.getName(), city2.getName(), "Le nom n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(city1.getZipCode(), city2.getZipCode(),
				"Le code postal n'est pas cohérent entre entre les deux DTO");
	}

	/*
	 * Compare les propriétés à partir de deux listes d'entités
	 */
	public static void assertListEntities(List<City> entities1, List<City> entities2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(entities2.size(), entities1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities1.size(); i++) {
			// On récupère les entités courantes
			City actualEntity = entities1.get(i);
			City expectedEntity = entities2.get(i);

			// On les compare
			CityAssertions.assertEqualsProperties(actualEntity, expectedEntity);
		}
	}

	/*
	 * Compare les propriétés à partir de d'une liste d'entités et d'une liste DTO
	 */
	public static void assertListEntitiesDTOs(List<City> entities, List<CityDTO> dtos) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos.size(), entities.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities.size(); i++) {
			// On récupère les entités courantes
			City actualEntity = entities.get(i);
			CityDTO expectedDTO = dtos.get(i);

			// On les compare
			CityAssertions.assertEqualsProperties(actualEntity, expectedDTO);
		}
	}

	/*
	 * Compare les propriétés à partir de deux listes DTO
	 */
	public static void assertListDTOs(List<CityDTO> dtos1, List<CityDTO> dtos2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos2.size(), dtos1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < dtos1.size(); i++) {
			// On récupère les entités courantes
			CityDTO actualDTO = dtos1.get(i);
			CityDTO expectedDTO = dtos2.get(i);

			// On les compare
			CityAssertions.assertEqualsProperties(actualDTO, expectedDTO);
		}
	}
}
