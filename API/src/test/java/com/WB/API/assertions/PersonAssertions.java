package com.WB.API.assertions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Assertions;

import com.WB.API.dto.PersonDTO;
import com.WB.API.dto.PersonSummaryDTO;
import com.WB.API.model.Person;

public class PersonAssertions {

	/*
	 * Retourne une entité Person pour les tests
	 */
	public static Person getPerson() {
		return new Person(25, "Name", "FirstName", "b.s@gmail.com", "0609548735", "title", "subTitle",
				LocalDate.of(1986, 07, 04), "Values", "LinkedIn", "Github");
	}

	/*
	 * Retourne un DTO Person pour les tests
	 */
	public static PersonDTO getPersonDTO() {
		return new PersonDTO(25, "Name", "FirstName", "b.s@gmail.com", "0609548735", "title", "subTitle",
				LocalDate.of(1986, 07, 04), "Values", List.of("KeySkillsA", "KeySkillsB", "KeySkillsC"), "LinkedIn",
				"Github");
	}

	/*
	 * Retourne un objet de test avec une liste d'entité et une liste de DTO pour
	 * les tests
	 */
	public static TestSummaryDatas<Person, PersonDTO, PersonSummaryDTO> getPersonTestDatas(int count) {
		List<Person> entities = new ArrayList<>();
		List<PersonDTO> dtos = new ArrayList<>();
		List<PersonSummaryDTO> dtoSummaries = new ArrayList<>();

		boolean isFormation = true;

		for (int i = 0; i < count; i++) {
			isFormation = !isFormation;
			int year = 1980 + i;
			int month = ThreadLocalRandom.current().nextInt(1, 13);
			int day = ThreadLocalRandom.current().nextInt(1, 29);
			entities.add(new Person(i, "Name" + i, "FirstName" + i, "email" + i + "@gmail.com", "0609" + i, "title" + i,
					"subTitle" + i, LocalDate.of(year, month, day), "Values" + i, "LinkedIn" + i, "GitHub" + i));
			PersonDTO newDTO = new PersonDTO(i, "Name" + i, "FirstName" + i, "email" + i + "@gmail.com", "0609" + i,
					"title" + i, "subTitle" + i, LocalDate.of(year, month, day), "Values" + i,
					List.of("KeySkillsA" + i, "KeySkillsB" + i, "KeySkillsC" + i), "LinkedIn" + i, "GitHub" + i);
			dtos.add(newDTO);
			dtoSummaries.add(newDTO.getSummary());
		}

		return new TestSummaryDatas<>(entities, dtos, dtoSummaries);
	}

	/*
	 * Vérifie que l'objet DTO et ses propriétés ne sont pas null
	 */
	public static void assertNotNullDTO(PersonDTO dto) {
		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto.getId(), "L'ID du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getName(), "Le nom du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getBirthDate(), "La date d'anniversaire du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getFirstName(), "Le prénom du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getMail(), "Le mail du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getPhone(), "Le téléphone du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getTitle(), "Le titre du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getSubtitle(), "Le sous titre du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getPersonalValues(), "Les valeurs personnelles du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getKeySkills(), "Les compétences du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getLinkedInLink(), "Le lien LinkedIn du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getGitHubLink(), "Le lien GitHub du DTO ne doit pas être null");
	}

	/*
	 * Vérifie que l'objet SummaryDTO et ses propriétés ne sont pas null
	 */
	public static void assertNotNullDTO(PersonSummaryDTO dto) {
		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto.getId(), "L'ID du résumé du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getName(), "Le nom du résumé du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getFirstName(), "Le prénom du résumé du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getMail(), "Le mail du résumé du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getPhone(), "Le téléphone du résumé du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getTitle(), "Le titre du résumé du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getLinkedInLink(), "Le lien LinkedIn du résumé du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getGitHubLink(), "Le lien GitHub du résumé du DTO ne doit pas être null");
	}

	/*
	 * Vérifie que l'objet entité et ses propriétés ne sont pas null
	 */
	public static void assertNotNullEntity(Person entity) {
		Assertions.assertNotNull(entity);
		Assertions.assertNotNull(entity.getId(), "L'ID de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getName(), "Le nom de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getBirthdate(), "La date d'anniversaire de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getFirstName(), "Le prénom de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getMail(), "Le mail de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getPhone(), "Le téléphone de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getTitle(), "Le titre de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getSubtitle(), "Le sous titre de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getPersonalValues(),
				"Les valeurs personnelles de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getLinkedInLink(), "Le lien LinkedIn de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getGitHubLink(), "Le lien GitHub de l'entité ne doit pas être null");
	}

	/*
	 * Compare la valeur des propriétés de deux entités
	 */
	public static void assertEqualsProperties(Person person1, Person person2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		PersonAssertions.assertNotNullEntity(person1);
		PersonAssertions.assertNotNullEntity(person2);

		// On compare les propriétés
		Assertions.assertEquals(person1.getId(), person2.getId(), "L'ID n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(person1.getName(), person2.getName(),
				"Le nom n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(person1.getFirstName(), person2.getFirstName(),
				"Le prénom n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(person1.getMail(), person2.getMail(),
				"Le mail n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(person1.getPhone(), person2.getPhone(),
				"Le téléphone n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(person1.getTitle(), person2.getTitle(),
				"Le titre n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(person1.getSubtitle(), person2.getSubtitle(),
				"Le sous titre n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(person1.getPersonalValues(), person2.getPersonalValues(),
				"Les valeurs personnelles ne sont pas cohérentes entre les deux entités");
		Assertions.assertEquals(person1.getBirthdate(), person2.getBirthdate(),
				"La date d'anniversaire n'est pas cohérente entre les deux entités");
		Assertions.assertEquals(person1.getLinkedInLink(), person2.getLinkedInLink(),
				"Les liens LinkedIn ne sont pas cohérents entre les deux entités");
		Assertions.assertEquals(person1.getGitHubLink(), person2.getGitHubLink(),
				"Les liens GitHub ne sont pas cohérents entre les deux entités");
	}

	/*
	 * Compare la valeur des propriétés de d'une entité et d'un DTO
	 */
	public static void assertEqualsProperties(Person person1, PersonDTO person2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		PersonAssertions.assertNotNullEntity(person1);
		PersonAssertions.assertNotNullDTO(person2);

		// On compare les propriétés
		Assertions.assertEquals(person1.getId(), person2.getId(), "L'ID n'est pas cohérent entre l'entité et le DTO");
		Assertions.assertEquals(person1.getName(), person2.getName(),
				"Le nom n'est pas cohérent entre les l'entité et le DTO");
		Assertions.assertEquals(person1.getMail(), person2.getMail(),
				"Le mail n'est pas cohérent entre l'entité et le DTO");
		Assertions.assertEquals(person1.getPhone(), person2.getPhone(),
				"Le téléphone n'est pas cohérent entre l'entité et le DTO");
		Assertions.assertEquals(person1.getTitle(), person2.getTitle(),
				"Le titre n'est pas cohérent entre l'entité et le DTO");
		Assertions.assertEquals(person1.getSubtitle(), person2.getSubtitle(),
				"Le sous titre n'est pas cohérent entre l'entité et le DTO");
		Assertions.assertEquals(person1.getPersonalValues(), person2.getPersonalValues(),
				"Les valeurs personnelles ne sont pas cohérentes entre l'entité et le DTO");
		Assertions.assertEquals(person1.getBirthdate(), person2.getBirthDate(),
				"La date d'anniversaire n'est pas cohérente entre l'entité et le DTO");
		Assertions.assertEquals(person1.getLinkedInLink(), person2.getLinkedInLink(),
				"Les liens LinkedIn ne sont pas cohérents entre l'entité et le DTO");
		Assertions.assertEquals(person1.getGitHubLink(), person2.getGitHubLink(),
				"Les liens GitHub ne sont pas cohérents entre l'entité et le DTO");
	}

	/*
	 * Compare la valeur des propriétés de d'une entité et d'un DTO Summary
	 */
	public static void assertEqualsProperties(Person person1, PersonSummaryDTO summary) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		PersonAssertions.assertNotNullEntity(person1);
		PersonAssertions.assertNotNullDTO(summary);

		// On compare les propriétés
		Assertions.assertEquals(person1.getId(), summary.getId(),
				"L'ID n'est pas cohérent entre l'entité et le résumé du DTO");
		Assertions.assertEquals(person1.getName(), summary.getName(),
				"Le nom n'est pas cohérent entre les l'entité et le résumé du DTO");
		Assertions.assertEquals(person1.getFirstName(), summary.getFirstName(),
				"Le prénom n'est pas cohérent entre l'entité et le résumé du DTO");
		Assertions.assertEquals(person1.getMail(), summary.getMail(),
				"Le mail n'est pas cohérent entre l'entité et le résumé du DTO");
		Assertions.assertEquals(person1.getPhone(), summary.getPhone(),
				"Le téléphone n'est pas cohérent entre l'entité et le résumé du DTO");
		Assertions.assertEquals(person1.getTitle(), summary.getTitle(),
				"Le titre n'est pas cohérent entre l'entité et le résumé du DTO");
		Assertions.assertEquals(person1.getLinkedInLink(), summary.getLinkedInLink(),
				"Le lien LinkedIn n'est pas cohérent entre l'entité et le résumé du DTO");
		Assertions.assertEquals(person1.getGitHubLink(), summary.getGitHubLink(),
				"Le lien GitHub n'est pas cohérent entre l'entité et le résumé du DTO");
	}

	/*
	 * Compare la valeur des propriétés de deux DTO
	 */
	public static void assertEqualsProperties(PersonDTO person1, PersonDTO person2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		PersonAssertions.assertNotNullDTO(person1);
		PersonAssertions.assertNotNullDTO(person2);

		// On compare les propriétés
		Assertions.assertEquals(person1.getId(), person2.getId(), "L'ID n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(person1.getName(), person2.getName(), "Le nom n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(person1.getFirstName(), person2.getFirstName(),
				"Le prénom n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(person1.getMail(), person2.getMail(), "Le mail n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(person1.getPhone(), person2.getPhone(),
				"Le téléphone n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(person1.getTitle(), person2.getTitle(),
				"Le titre n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(person1.getSubtitle(), person2.getSubtitle(),
				"Le sous titre n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(person1.getPersonalValues(), person2.getPersonalValues(),
				"Les valeurs personnelles ne sont pas cohérentes entre les deux DTO");
		Assertions.assertEquals(person1.getBirthDate(), person2.getBirthDate(),
				"La date d'anniversaire n'est pas cohérente entre les deux DTO");
		Assertions.assertEquals(person1.getLinkedInLink(), person2.getLinkedInLink(),
				"Les liens LinkedIn ne sont pas cohérents entre les deux DTO");
		Assertions.assertEquals(person1.getGitHubLink(), person2.getGitHubLink(),
				"Les liens GitHub ne sont pas cohérents entre les deux DTO");
	}

	/*
	 * Compare la valeur des propriétés d'un DTOSummary et d'un DTO
	 */
	public static void assertEqualsProperties(PersonSummaryDTO personSummary, PersonDTO person2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		PersonAssertions.assertNotNullDTO(personSummary);
		PersonAssertions.assertNotNullDTO(person2);

		// On compare les propriétés
		Assertions.assertEquals(personSummary.getId(), person2.getId(),
				"L'ID n'est pas cohérent entre le résumé et le DTO");
		Assertions.assertEquals(personSummary.getName(), person2.getName(),
				"Le nom n'est pas cohérent entre entre le résumé et le DTO");
		Assertions.assertEquals(personSummary.getFirstName(), person2.getFirstName(),
				"Le prénom n'est pas cohérent entre le résumé et le DTO");
		Assertions.assertEquals(personSummary.getMail(), person2.getMail(),
				"Le mail n'est pas cohérent entre le résumé et le DTO");
		Assertions.assertEquals(personSummary.getPhone(), person2.getPhone(),
				"Le téléphone n'est pas cohérent entre le résumé et le DTO");
		Assertions.assertEquals(personSummary.getTitle(), person2.getTitle(),
				"Le titre n'est pas cohérent entre le résumé et le DTO");
		Assertions.assertEquals(personSummary.getLinkedInLink(), person2.getLinkedInLink(),
				"Le lien LinkedIn n'est pas cohérent entre le résumé et le DTO");
		Assertions.assertEquals(personSummary.getGitHubLink(), person2.getGitHubLink(),
				"Le lien GitHub n'est pas cohérent entre le résumé et le DTO");
	}

	/*
	 * Compare la valeur des propriétés de deux DTOSummary
	 */
	public static void assertEqualsProperties(PersonSummaryDTO person1, PersonSummaryDTO person2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		PersonAssertions.assertNotNullDTO(person1);
		PersonAssertions.assertNotNullDTO(person2);

		// On compare les propriétés
		Assertions.assertEquals(person1.getId(), person2.getId(),
				"L'ID n'est pas cohérent entre les deux résumés du DTO");
		Assertions.assertEquals(person1.getName(), person2.getName(),
				"Le nom n'est pas cohérent entre les deux résumés du DTO");
		Assertions.assertEquals(person1.getFirstName(), person2.getFirstName(),
				"Le prénom n'est pas cohérent entre les deux résumés du DTO");
		Assertions.assertEquals(person1.getMail(), person2.getMail(),
				"Le mail n'est pas cohérent entre les deux résumés du DTO");
		Assertions.assertEquals(person1.getPhone(), person2.getPhone(),
				"Le téléphone n'est pas cohérent entre les deux résumés du DTO");
		Assertions.assertEquals(person1.getTitle(), person2.getTitle(),
				"Le titre n'est pas cohérent entre les deux résumés du DTO");
		Assertions.assertEquals(person1.getLinkedInLink(), person2.getLinkedInLink(),
				"Le lien LinkedIn n'est pas cohérent entre les deux résumés du DTO");
		Assertions.assertEquals(person1.getGitHubLink(), person2.getGitHubLink(),
				"Le lien GitHub n'est pas cohérent entre les deux résumés du DTO");
	}

	/*
	 * Compare les propriétés à partir de deux listes d'entités
	 */
	public static void assertListEntities(List<Person> entities1, List<Person> entities2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(entities2.size(), entities1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities1.size(); i++) {
			// On récupère les entités courantes
			Person actualEntity = entities1.get(i);
			Person expectedEntity = entities2.get(i);

			// On les compare
			PersonAssertions.assertEqualsProperties(actualEntity, expectedEntity);
		}
	}

	/*
	 * Compare les propriétés à partir de d'une liste d'entités et d'une liste DTO
	 */
	public static void assertListEntitiesDTOs(List<Person> entities, List<PersonDTO> dtos) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos.size(), entities.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities.size(); i++) {
			// On récupère les entités courantes
			Person actualEntity = entities.get(i);
			PersonDTO expectedDTO = dtos.get(i);

			// On les compare
			PersonAssertions.assertEqualsProperties(actualEntity, expectedDTO);
		}
	}

	/*
	 * Compare les propriétés à partir de d'une liste d'entités et d'une liste
	 * DTOSummary
	 */
	public static void assertListEntitiesSummaries(List<Person> entities, List<PersonSummaryDTO> summaries) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(summaries.size(), entities.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities.size(); i++) {
			// On récupère les entités courantes
			Person actualEntity = entities.get(i);
			PersonSummaryDTO expectedDTO = summaries.get(i);

			// On les compare
			PersonAssertions.assertEqualsProperties(actualEntity, expectedDTO);
		}
	}

	/*
	 * Compare les propriétés à partir de deux listes DTO
	 */
	public static void assertListDTOs(List<PersonDTO> dtos1, List<PersonDTO> dtos2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos2.size(), dtos1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < dtos1.size(); i++) {
			// On récupère les entités courantes
			PersonDTO actualDTO = dtos1.get(i);
			PersonDTO expectedDTO = dtos2.get(i);

			// On les compare
			PersonAssertions.assertEqualsProperties(actualDTO, expectedDTO);
		}
	}

	/*
	 * Compare les propriétés à partir de d'une liste DTOSummary et d'une liste DTO
	 */
	public static void assertListSumariesDTOs(List<PersonSummaryDTO> summaries, List<PersonDTO> dtos) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos.size(), summaries.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < summaries.size(); i++) {
			// On récupère les entités courantes
			PersonSummaryDTO actualDTO = summaries.get(i);
			PersonDTO expectedDTO = dtos.get(i);

			// On les compare
			PersonAssertions.assertEqualsProperties(actualDTO, expectedDTO);
		}
	}

	/*
	 * Compare les propriétés à partir de deux listes DTOSummary
	 */
	public static void assertListSummaries(List<PersonSummaryDTO> summaries1, List<PersonSummaryDTO> summaries2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(summaries2.size(), summaries1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < summaries1.size(); i++) {
			// On récupère les entités courantes
			PersonSummaryDTO actualDTO = summaries1.get(i);
			PersonSummaryDTO expectedDTO = summaries2.get(i);

			// On les compare
			PersonAssertions.assertEqualsProperties(actualDTO, expectedDTO);
		}
	}
}
