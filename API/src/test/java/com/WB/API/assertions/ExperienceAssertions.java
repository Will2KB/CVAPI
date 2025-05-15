package com.WB.API.assertions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Assertions;

import com.WB.API.dto.ExperienceDTO;
import com.WB.API.dto.ExperienceSummaryDTO;
import com.WB.API.model.Experience;

public class ExperienceAssertions {

	/*
	 * Retourne une entité Experience sans sous objet défini pour les tests
	 */
	public static Experience getExperience() {
		return new Experience(25, "Experience", LocalDate.of(2012, 02, 24), LocalDate.of(2016, 10, 04), true,
				"Mission");
	}

	/*
	 * Retourne une entité Experience avec un établissement défini sans sous objet
	 * pour les tests
	 */
	public static Experience getExperienceWithEstablishement() {
		Experience entity = ExperienceAssertions.getExperience();
		entity.setEstablishement(EstablishmentAssertions.getEstablishment());
		return entity;
	}

	/*
	 * Retourne une entité Experience avec un établissement entièrement défini pour
	 * les tests
	 */
	public static Experience getExperienceWithEstablishementAndCity() {
		Experience entity = ExperienceAssertions.getExperience();
		entity.setEstablishement(EstablishmentAssertions.getEstablishmentWithCountry());
		return entity;
	}

	/*
	 * Retourne une entité Experience avec une ville définie mais sans pays ni
	 * établissement pour les tests
	 */
	public static Experience getExperienceWithCity() {
		Experience entity = ExperienceAssertions.getExperience();
		entity.setCity(CityAssertions.getCity());
		return entity;
	}

	/*
	 * Retourne une entité Experience avec une ville et un pays définie mais sans
	 * établissement pour les tests
	 */
	public static Experience getExperienceWithCountry() {
		Experience entity = ExperienceAssertions.getExperience();
		entity.setCity(CityAssertions.getCityWithCountry());
		return entity;
	}

	/*
	 * Retourne un DTO Experience pour les tests
	 */
	public static ExperienceDTO getExperienceDTO() {
		return new ExperienceDTO(25, "Experience", LocalDate.of(2012, 02, 24), LocalDate.of(2016, 10, 04), true,
				"Mission");
	}

	/*
	 * Retourne un objet de test avec une liste d'entité et une liste de DTO pour
	 * les tests
	 */
	public static TestSummaryDatas<Experience, ExperienceDTO, ExperienceSummaryDTO> getExperienceTestDatas(int count) {
		List<Experience> entities = new ArrayList<>();
		List<ExperienceDTO> dtos = new ArrayList<>();
		List<ExperienceSummaryDTO> dtoSummaries = new ArrayList<>();

		boolean isFormation = true;

		for (int i = 0; i < count; i++) {
			isFormation = !isFormation;
			int yearStart = 2000 + i;
			int yearEng = 2005 + i;
			int month = ThreadLocalRandom.current().nextInt(1, 13);
			int day = ThreadLocalRandom.current().nextInt(1, 29);
			entities.add(new Experience(i, "Experience" + i, LocalDate.of(yearStart, month, day),
					LocalDate.of(yearEng, month, day), isFormation, "Mission" + i));
			dtos.add(new ExperienceDTO(i, "Experience" + i, LocalDate.of(yearStart, month, day),
					LocalDate.of(yearEng, month, day), isFormation, "Mission" + i));
			dtoSummaries.add(new ExperienceSummaryDTO(i, "Experience" + i, LocalDate.of(yearStart, month, day),
					LocalDate.of(yearEng, month, day), isFormation));
		}

		return new TestSummaryDatas<>(entities, dtos, dtoSummaries);
	}

	/*
	 * Vérifie que l'objet DTO et ses propriétés ne sont pas null
	 */
	public static void assertNotNullDTO(ExperienceDTO dto) {
		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto.getId(), "L'ID du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getName(), "Le nom du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getDateBeginning(), "La date de début du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getDateEnding(), "La date de fin du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getMission(), "La mission du DTO ne doit pas être null");
	}

	/*
	 * Vérifie que l'objet SummaryDTO et ses propriétés ne sont pas null
	 */
	public static void assertNotNullDTO(ExperienceSummaryDTO dto) {
		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto.getId(), "L'ID du résumé du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getName(), "Le nom du résumé du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getDateBeginning(), "La date de début du résumé du DTO ne doit pas être null");
		Assertions.assertNotNull(dto.getDateEnding(), "La date de fin du résumé du DTO ne doit pas être null");
	}

	/*
	 * Vérifie que l'objet entité et ses propriétés ne sont pas null
	 */
	public static void assertNotNullEntity(Experience entity) {
		Assertions.assertNotNull(entity);
		Assertions.assertNotNull(entity.getId(), "L'ID de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getName(), "Le nom de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getDateBeginning(), "La date de début de l'entité ne doit pas être null");
		Assertions.assertNotNull(entity.getDateEnding(), "La date de fin de l'entité ne doit pas être null");
	}

	/*
	 * Compare la valeur des propriétés de deux entités
	 * 
	 * @isCityNUll : Paramètre indiquant si l'objet testé à une ville null ou non
	 */
	public static void assertEqualsProperties(Experience experience1, Experience experience2, boolean isCityNull) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		ExperienceAssertions.assertNotNullEntity(experience1);
		ExperienceAssertions.assertNotNullEntity(experience2);

		// On compare les propriétés
		Assertions.assertEquals(experience1.getId(), experience2.getId(),
				"L'ID n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(experience1.getName(), experience2.getName(),
				"Le nom n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(experience1.getDateBeginning(), experience2.getDateBeginning(),
				"La date de début n'est pas cohérente entre les deux entités");
		Assertions.assertEquals(experience1.getDateEnding(), experience2.getDateEnding(),
				"Le date de fin n'est pas cohérente entre les deux entités");
		Assertions.assertEquals(experience1.isFormation(), experience2.isFormation(),
				"L'indicateur de formation n'est pas cohérent entre les deux entités");
		Assertions.assertEquals(experience1.getMission(), experience2.getMission(),
				"La mission n'est pas cohérente entre les deux entités");

		if (isCityNull) {
			// On vérifie si les villes doit être null selon le test demandé
			Assertions.assertNull(experience1.getCity(), "L'une des villes lié à l'expérience 1 n'est pas null");
			Assertions.assertNull(experience2.getCity(), "L'une des villes lié à l'expérience 2 n'est pas null");
		} else if (experience1.getCity() == null || experience2.getCity() == null) {
			// On vérifie si l'une des deux villes est null
			Assertions.assertNotNull(experience1.getCity(),
					"L'une des villes lié à l'expérience est null mais pas l'autre (experience1)");
			Assertions.assertNotNull(experience2.getCity(),
					"L'une des villes lié à l'expérience est null mais pas l'autre (experience2");
		} else {
			// On compare le nom des villes
			Assertions.assertEquals(experience1.getCity().getName(), experience2.getCity().getName(),
					"La ville n'est pas cohérente entre les deux entités");
		}
	}

	/*
	 * Compare la valeur des propriétés de d'une entité et d'un DTO
	 * 
	 * @isCityNUll : Paramètre indiquant si l'objet testé à une ville null ou non
	 */
	public static void assertEqualsProperties(Experience experience1, ExperienceDTO experience2, boolean isCityNull) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		ExperienceAssertions.assertNotNullEntity(experience1);
		ExperienceAssertions.assertNotNullDTO(experience2);

		// On compare les propriétés
		Assertions.assertEquals(experience1.getId(), experience2.getId(),
				"L'ID n'est pas cohérent entre l'entité et le DTO");
		Assertions.assertEquals(experience1.getName(), experience2.getName(),
				"Le nom n'est pas cohérent entre les l'entité et le DTO");
		Assertions.assertEquals(experience1.getDateBeginning(), experience2.getDateBeginning(),
				"La date de début n'est pas cohérente entre les l'entité et le DTO");
		Assertions.assertEquals(experience1.getDateEnding(), experience2.getDateEnding(),
				"La date de fin n'est pas cohérente entre les l'entité et le DTO");
		Assertions.assertEquals(experience1.isFormation(), experience2.isFormation(),
				"L'indicateur de formation n'est pas cohérent entre les l'entité et le DTO");
		Assertions.assertEquals(experience1.getMission(), experience2.getMission(),
				"La mission n'est pas cohérente entre les l'entité et le DTO");

		if (isCityNull) {
			// On vérifie si les villes doit être null selon le test demandé
			Assertions.assertNull(experience1.getCity(), "L'une des villes lié à l'expérience 1 n'est pas null");
			Assertions.assertNull(experience2.getCity(), "L'une des villes lié à l'expérience 2 n'est pas null");
		} else if (experience1.getCity() == null || experience2.getCity() == null) {
			// On vérifie si l'une des deux villes est null
			Assertions.assertNotNull(experience1.getCity(),
					"L'une des villes lié à l'expérience 1 est null mais pas l'autre");
			Assertions.assertNotNull(experience2.getCity(),
					"L'une des villes lié à l'expérience 2 est null mais pas l'autre");
		} else {
			// On compare le nom des villes
			Assertions.assertEquals(experience1.getCity().getName(), experience2.getCity().getName(),
					"La ville n'est pas cohérente entre les deux entités");
		}
	}

	/*
	 * Compare la valeur des propriétés de d'une entité et d'un DTOSummary
	 */
	public static void assertEqualsProperties(Experience experience1, ExperienceSummaryDTO summary) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		ExperienceAssertions.assertNotNullEntity(experience1);
		ExperienceAssertions.assertNotNullDTO(summary);

		// On compare les propriétés
		Assertions.assertEquals(experience1.getId(), summary.getId(),
				"L'ID n'est pas cohérent entre l'entité et le résumé du DTO");
		Assertions.assertEquals(experience1.getName(), summary.getName(),
				"Le nom n'est pas cohérent entre les l'entité et le résumé du DTO");
		Assertions.assertEquals(experience1.getDateBeginning(), summary.getDateBeginning(),
				"La date de début n'est pas cohérente entre les l'entité et le résumé du DTO");
		Assertions.assertEquals(experience1.getDateEnding(), summary.getDateEnding(),
				"La date de fin n'est pas cohérente entre les l'entité et le résumé du DTO");
		Assertions.assertEquals(experience1.isFormation(), summary.isFormation(),
				"L'indicateur de formation n'est pas cohérent entre les l'entité et le résumé du DTO");
	}

	/*
	 * Compare la valeur des propriétés de deux DTO
	 * 
	 * @isCityNUll : Paramètre indiquant si l'objet testé à une ville null ou non
	 */
	public static void assertEqualsProperties(ExperienceDTO experience1, ExperienceDTO experience2,
			boolean isCityNull) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		ExperienceAssertions.assertNotNullDTO(experience1);
		ExperienceAssertions.assertNotNullDTO(experience2);

		// On compare les propriétés
		Assertions.assertEquals(experience1.getId(), experience2.getId(), "L'ID n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(experience1.getName(), experience2.getName(),
				"Le nom n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(experience1.getDateBeginning(), experience2.getDateBeginning(),
				"La date de début n'est pas cohérente entre entre les deux DTO");
		Assertions.assertEquals(experience1.getDateEnding(), experience2.getDateEnding(),
				"La date de fin n'est pas cohérente entre entre les deux DTO");
		Assertions.assertEquals(experience1.isFormation(), experience2.isFormation(),
				"L'indicateur de formation n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(experience1.getMission(), experience2.getMission(),
				"La mission n'est pas cohérente entre les deux DTO");

		if (isCityNull) {
			// On vérifie si les villes doit être null selon le test demandé
			Assertions.assertNull(experience1.getCity(), "L'une des villes lié à l'expérience 1 n'est pas null");
			Assertions.assertNull(experience2.getCity(), "L'une des villes lié à l'expérience 2 n'est pas null");
		} else if (experience1.getCity() == null || experience2.getCity() == null) {
			// On vérifie si l'une des deux villes est null
			Assertions.assertNotNull(experience1.getCity(),
					"L'une des villes lié à l'expérience 1 est null mais pas l'autre");
			Assertions.assertNotNull(experience2.getCity(),
					"L'une des villes lié à l'expérience 2 est null mais pas l'autre");
		} else {
			// On compre le nom des villes
			Assertions.assertEquals(experience1.getCity().getName(), experience2.getCity().getName(),
					"La ville n'est pas cohérente entre les deux entités");
		}
	}

	/*
	 * Compare la valeur des propriétés de d'un DTOSummary et d'un DTO
	 */
	public static void assertEqualsProperties(ExperienceSummaryDTO experienceSummary, ExperienceDTO experience2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		ExperienceAssertions.assertNotNullDTO(experienceSummary);
		ExperienceAssertions.assertNotNullDTO(experience2);

		// On compare les propriétés
		Assertions.assertEquals(experienceSummary.getId(), experience2.getId(),
				"L'ID n'est pas cohérent entre le résumé et le DTO");
		Assertions.assertEquals(experienceSummary.getName(), experience2.getName(),
				"Le nom n'est pas cohérent entre entre le résumé et le DTO");
		Assertions.assertEquals(experienceSummary.getDateBeginning(), experience2.getDateBeginning(),
				"La date de début n'est pas cohérente entre le résumé et le DTO");
		Assertions.assertEquals(experienceSummary.getDateEnding(), experience2.getDateEnding(),
				"La date de fin n'est pas cohérente entre le résumé et le DTO");
		Assertions.assertEquals(experienceSummary.isFormation(), experience2.isFormation(),
				"L'indicateur de formation n'est pas cohérent entre le résumé et le DTO");
	}

	/*
	 * Compare la valeur des propriétés de deux DTO Summary
	 */
	public static void assertEqualsProperties(ExperienceSummaryDTO experience1, ExperienceSummaryDTO experience2) {
		// On vérifie la nullité des objets avant d'accéder aux propriétés
		ExperienceAssertions.assertNotNullDTO(experience1);
		ExperienceAssertions.assertNotNullDTO(experience2);

		// On compare les propriétés
		Assertions.assertEquals(experience1.getId(), experience2.getId(), "L'ID n'est pas cohérent entre les deux DTO");
		Assertions.assertEquals(experience1.getName(), experience2.getName(),
				"Le nom n'est pas cohérent entre les deux résumés du DTO");
		Assertions.assertEquals(experience1.getDateBeginning(), experience2.getDateBeginning(),
				"La date de début n'est pas cohérente entre entre les deux résumés du  DTO");
		Assertions.assertEquals(experience1.getDateEnding(), experience2.getDateEnding(),
				"La date de fin n'est pas cohérente entre entre les deux résumés du  DTO");
		Assertions.assertEquals(experience1.isFormation(), experience2.isFormation(),
				"L'indicateur de formation n'est pas cohérent entre les deux résumés du  DTO");
	}

	/*
	 * Compare les propriétés à partir de deux listes d'entités
	 * 
	 * @isCityNUll : Paramètre indiquant si l'objet testé à une ville null ou non
	 */
	public static void assertListEntities(List<Experience> entities1, List<Experience> entities2, boolean isCityNull) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(entities2.size(), entities1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities1.size(); i++) {
			// On récupère les entités courantes
			Experience actualEntity = entities1.get(i);
			Experience expectedEntity = entities2.get(i);

			// On les compare
			ExperienceAssertions.assertEqualsProperties(actualEntity, expectedEntity, isCityNull);
		}
	}

	/*
	 * Compare les propriétés à partir de d'une liste d'entités et d'une liste DTO
	 * 
	 * @isCityNUll : Paramètre indiquant si l'objet testé à une ville null ou non
	 */
	public static void assertListEntitiesDTOs(List<Experience> entities, List<ExperienceDTO> dtos, boolean isCityNull) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos.size(), entities.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities.size(); i++) {
			// On récupère les entités courantes
			Experience actualEntity = entities.get(i);
			ExperienceDTO expectedDTO = dtos.get(i);

			// On les compare
			ExperienceAssertions.assertEqualsProperties(actualEntity, expectedDTO, isCityNull);
		}
	}

	/*
	 * Compare les propriétés à partir de d'une liste d'entités et d'une liste
	 * DTOSummary
	 */
	public static void assertListEntitiesSummaries(List<Experience> entities, List<ExperienceSummaryDTO> summaries) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(summaries.size(), entities.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < entities.size(); i++) {
			// On récupère les entités courantes
			Experience actualEntity = entities.get(i);
			ExperienceSummaryDTO expectedDTO = summaries.get(i);

			// On les compare
			ExperienceAssertions.assertEqualsProperties(actualEntity, expectedDTO);
		}
	}

	/*
	 * Compare les propriétés à partir de deux listes DTO
	 * 
	 * @isCityNUll : Paramètre indiquant si l'objet testé à une ville null ou non
	 */
	public static void assertListDTOs(List<ExperienceDTO> dtos1, List<ExperienceDTO> dtos2, boolean isCityNull) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos2.size(), dtos1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < dtos1.size(); i++) {
			// On récupère les entités courantes
			ExperienceDTO actualDTO = dtos1.get(i);
			ExperienceDTO expectedDTO = dtos2.get(i);

			// On les compare
			ExperienceAssertions.assertNotNullDTO(actualDTO);
			ExperienceAssertions.assertNotNullDTO(expectedDTO);
			ExperienceAssertions.assertEqualsProperties(actualDTO, expectedDTO, isCityNull);
		}
	}

	/*
	 * Compare les propriétés à partir de d'une liste DTOSummary et d'une liste DTO
	 */
	public static void assertListSumariesDTOs(List<ExperienceSummaryDTO> summaries, List<ExperienceDTO> dtos) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(dtos.size(), summaries.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < summaries.size(); i++) {
			// On récupère les entités courantes
			ExperienceSummaryDTO actualDTO = summaries.get(i);
			ExperienceDTO expectedDTO = dtos.get(i);

			// On les compare
			ExperienceAssertions.assertNotNullDTO(actualDTO);
			ExperienceAssertions.assertNotNullDTO(expectedDTO);
			ExperienceAssertions.assertEqualsProperties(actualDTO, expectedDTO);
		}
	}

	/*
	 * Compare les propriétés à partir de deux listes DTOSummary
	 */
	public static void assertListSummaries(List<ExperienceSummaryDTO> summaries1,
			List<ExperienceSummaryDTO> summaries2) {
		// On vérifie que la taille des listes sont identiques
		Assertions.assertEquals(summaries2.size(), summaries1.size(), "Les listes ne sont pas de la même taille");

		for (int i = 0; i < summaries1.size(); i++) {
			// On récupère les entités courantes
			ExperienceSummaryDTO actualDTO = summaries1.get(i);
			ExperienceSummaryDTO expectedDTO = summaries2.get(i);

			// On les compare
			ExperienceAssertions.assertNotNullDTO(actualDTO);
			ExperienceAssertions.assertNotNullDTO(expectedDTO);
			ExperienceAssertions.assertEqualsProperties(actualDTO, expectedDTO);
		}
	}
}
