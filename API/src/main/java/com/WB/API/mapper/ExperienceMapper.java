package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.ExperienceDTO;
import com.WB.API.dto.ExperienceSummaryDTO;
import com.WB.API.model.City;
import com.WB.API.model.Establishment;
import com.WB.API.model.Experience;

/**
 * Classe permettant de mapper une entité en objet de trasnfert et inverssement
 * pour les Experience
 */
public class ExperienceMapper {

	/**
	 * Transfert une entité en objet de transfert résumé
	 * 
	 * @param experience: Entité à mapper
	 * 
	 * @return Retourne l'objet de transfert résumé après le mappage
	 */
	public static ExperienceSummaryDTO toSummaryDTO(Experience experience) {
		// Si l'entité d'entrée est null => on retroune null
		if (experience == null)
			return null;

		// Création de l'objet de trasnfert résumé
		ExperienceSummaryDTO summary = new ExperienceSummaryDTO();

		// Mappage des paramètres
		summary.setId(experience.getId());
		summary.setName(experience.getName());
		summary.setMission(experience.getMission());
		summary.setDateBeginning(experience.getDateBeginning());
		summary.setDateEnding(experience.getDateEnding());
		summary.setFormation(experience.isFormation());

		// Mappage si il y a un établissement
		if (experience.getEstablishement() != null) {
			summary.setEstablishmentId(experience.getEstablishement().getId());
		}

		// Mappage si il y a une ville
		if (experience.getCity() != null) {
			summary.setCityId(experience.getCity().getId());
			summary.setCityName(experience.getCity().getName());
			// Mappage si il y a un pays
			if (experience.getCity().getCountry() != null) {
				summary.setCountryName(experience.getCity().getCountry().getName());
			}
		}

		return summary;
	}

	/**
	 * Transfert une liste d'entité en liste d'objet de trasnfert résumé
	 * 
	 * @param expriences: Liste d'entité à mapper
	 * 
	 * @return Retourne une liste d'objet de transfert résumé après le mappage
	 */
	public static List<ExperienceSummaryDTO> toSummaryDTOList(List<Experience> experiences) {
		// Si la liste d'entrée est null => on retroune null
		if (experiences == null)
			return null;

		// Création de la liste d'objet de trasnfert résumé
		List<ExperienceSummaryDTO> summaries = new ArrayList<>();

		// Mappage de chaque élément de la liste
		for (Experience experience : experiences) {
			summaries.add(ExperienceMapper.toSummaryDTO(experience));
		}

		return summaries;
	}

	/**
	 * Transfert une entité en objet de transfert
	 * 
	 * @param experience: Entité à mapper
	 * 
	 * @return Retourne l'objet de transfert après le mappage
	 */
	public static ExperienceDTO toDTO(Experience experience) {
		// Si l'entité d'entrée est null => on retroune null
		if (experience == null)
			return null;

		// Création de l'objet de transfert
		ExperienceDTO experienceDTO = new ExperienceDTO();

		// Mappage des paramètres
		experienceDTO.setSummary(ExperienceMapper.toSummaryDTO(experience));
		experienceDTO.setCity(CityMapper.toDTO(experience.getCity()));
		experienceDTO.setEstablishment(EstablishmentMapper.toDTO(experience.getEstablishement()));
		experienceDTO.setDescription(experience.getDescription());
		experienceDTO.setSkills(SkillMapper.toDTOList(experience.getSkills()));

		return experienceDTO;
	}

	/**
	 * Transfert une liste d'entité en liste d'objet de trasnfert
	 * 
	 * @param experiences: Liste d'entité à mapper
	 * 
	 * @return Retourne une liste d'objet de transfert après le mappage
	 */
	public static List<ExperienceDTO> toDTOList(List<Experience> experiences) {
		// Si la liste d'entrée est null => on retroune null
		if (experiences == null)
			return null;

		// Création de la liste des objets de transfert
		List<ExperienceDTO> experiencesDTO = new ArrayList<>();

		// Mappage de chque élément de la liste
		for (Experience experience : experiences) {
			experiencesDTO.add(ExperienceMapper.toDTO(experience));
		}

		return experiencesDTO;
	}

	/**
	 * Transfert un objet de transfert en entité
	 * 
	 * @param experienceDTO: Objet de transfert à mapper
	 * 
	 * @return Retourne l'entité après le mappage
	 */
	public static Experience toEntity(ExperienceDTO experienceDTO) {
		// Si l'objet de trasnfert d'entrée est null => on retroune null
		if (experienceDTO == null)
			return null;

		// Cration de l'entité et mapapge partiel des paramètres
		Experience experience = new Experience(experienceDTO.getSummary().getId(), experienceDTO.getSummary().getName(),
				experienceDTO.getSummary().getDateBeginning(), experienceDTO.getSummary().getDateEnding(),
				experienceDTO.getSummary().isFormation(), experienceDTO.getMission());

		// Mappage des autres paramètres
		experience.setMission(experienceDTO.getSummary().getMission());
		experience.setDescription(experienceDTO.getDescription());
		experience.setSkills(SkillMapper.toEntityList(experienceDTO.getSkills()));
		// Mappage si il y a une établissement
		if (experienceDTO.getEstablishment() != null) {
			experience.setEstablishement(new Establishment(experienceDTO.getEstablishment().getId()));
		} else {
			// Sinon set to NULL
			experience.setEstablishement(null);
		}
		// Mappage si il y a une ville
		if (experience.getCity() != null) {
			experience.setCity(new City(experienceDTO.getCity().getId()));
		} else {
			// Sinon set to NULL
			experience.setCity(null);
		}

		return experience;
	}

	/**
	 * Transfert un objet de transfert résumé en entité
	 * 
	 * @param summary: Objet de transfert résumé à mapper
	 * 
	 * @return Retourne l'entité après le mappage
	 */
	public static Experience toEntity(ExperienceSummaryDTO summary) {
		// Si l'objet de trasnfert résumé d'entrée est null => on retroune null
		if (summary == null)
			return null;

		// Création de l'entité et mappage partiel des paramètres
		Experience experience = new Experience(summary.getId(), summary.getName(), summary.getDateBeginning(),
				summary.getDateEnding(), summary.isFormation(), null);

		// Mappage des autres paramètres
		experience.setMission(summary.getMission());
		experience.setEstablishement(new Establishment(summary.getEstablishmentId()));
		experience.setCity(new City(summary.getCityId()));

		return experience;
	}

	/**
	 * Transfert une liste d'objet de transfert en liste d'entité
	 * 
	 * @param experiencesDTO: Liste d'objet de transfert à mapper
	 * 
	 * @return Retourne une liste d'entité après le mappage
	 */
	public static List<Experience> toEntityListFromDTO(List<ExperienceDTO> experiencesDTO) {
		// Si la liste d'entrée est null => on retroune null
		if (experiencesDTO == null)
			return null;

		// Création de la liste d'entités
		List<Experience> experiences = new ArrayList<>();

		// Mappage de chaque élément de la liste
		for (ExperienceDTO experienceDTO : experiencesDTO) {
			experiences.add(ExperienceMapper.toEntity(experienceDTO));
		}

		return experiences;
	}

	/**
	 * Transfert une liste d'objet de transfert résumé en liste d'entité
	 * 
	 * @param summaries: Liste d'objet de transfert résumé à mapper
	 * 
	 * @return Retourne une liste d'entité après le mappage
	 */
	public static List<Experience> toEntityListFromSummary(List<ExperienceSummaryDTO> summaries) {
		// Si la liste d'entrée est null => on retroune null
		if (summaries == null)
			return null;

		// Création de la liste d'entité
		List<Experience> experiences = new ArrayList<>();

		// Mappage de chque élément de la liste
		for (ExperienceSummaryDTO experienceDTO : summaries) {
			experiences.add(ExperienceMapper.toEntity(experienceDTO));
		}

		return experiences;
	}

}
