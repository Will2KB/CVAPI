package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.ExperienceDTO;
import com.WB.API.dto.ExperienceSummaryDTO;
import com.WB.API.model.City;
import com.WB.API.model.Establishment;
import com.WB.API.model.Experience;

public class ExperienceMapper {

	public static ExperienceSummaryDTO toSummaryDTO(Experience experience) {
		if (experience == null)
			return null;

		ExperienceSummaryDTO summary = new ExperienceSummaryDTO();

		summary.setId(experience.getId());
		summary.setName(experience.getName());
		summary.setMission(experience.getMission());
		summary.setDateBeginning(experience.getDateBeginning());
		summary.setDateEnding(experience.getDateEnding());
		summary.setFormation(experience.isFormation());

		if (experience.getEstablishement() != null) {
			summary.setEstablishmentId(experience.getEstablishement().getId());
		}

		if (experience.getCity() != null) {
			summary.setCityId(experience.getCity().getId());
			summary.setCityName(experience.getCity().getName());
			if (experience.getCity().getCountry() != null) {
				summary.setCountryName(experience.getCity().getCountry().getName());
			}
		}

		return summary;
	}

	public static List<ExperienceSummaryDTO> toSummaryDTOList(List<Experience> experiences) {
		if (experiences == null)
			return null;

		List<ExperienceSummaryDTO> summaries = new ArrayList<>();

		for (Experience experience : experiences) {
			summaries.add(ExperienceMapper.toSummaryDTO(experience));
		}

		return summaries;
	}

	public static ExperienceDTO toDTO(Experience experience) {
		if (experience == null)
			return null;

		ExperienceDTO experienceDTO = new ExperienceDTO();

		experienceDTO.setSummary(ExperienceMapper.toSummaryDTO(experience));
		experienceDTO.setCity(CityMapper.toDTO(experience.getCity()));
		experienceDTO.setEstablishment(EstablishmentMapper.toDTO(experience.getEstablishement()));
		experienceDTO.setDescription(experience.getDescription());
		experienceDTO.setSkills(SkillMapper.toDTOList(experience.getSkills()));

		return experienceDTO;
	}

	public static List<ExperienceDTO> toDTOList(List<Experience> experiences) {
		if (experiences == null)
			return null;

		List<ExperienceDTO> experiencesDTO = new ArrayList<>();

		for (Experience experience : experiences) {
			experiencesDTO.add(ExperienceMapper.toDTO(experience));
		}

		return experiencesDTO;
	}

	public static Experience toEntity(ExperienceDTO experienceDTO) {
		if (experienceDTO == null)
			return null;

		Experience experience = new Experience(experienceDTO.getSummary().getId(), experienceDTO.getSummary().getName(),
				experienceDTO.getSummary().getDateBeginning(), experienceDTO.getSummary().getDateEnding(),
				experienceDTO.getSummary().isFormation());
		experience.setMission(experienceDTO.getSummary().getMission());
		experience.setEstablishement(new Establishment(experienceDTO.getEstablishment().getId()));
		experience.setCity(new City(experienceDTO.getCity().getId()));
		experience.setDescription(experienceDTO.getDescription());
		experience.setSkills(SkillMapper.toEntityList(experienceDTO.getSkills()));

		return experience;
	}

	public static Experience toEntity(ExperienceSummaryDTO summary) {
		if (summary == null)
			return null;

		Experience experience = new Experience(summary.getId(), summary.getName(), summary.getDateBeginning(),
				summary.getDateEnding(), summary.isFormation());
		experience.setMission(summary.getMission());
		experience.setEstablishement(new Establishment(summary.getEstablishmentId()));
		experience.setCity(new City(summary.getCityId()));

		return experience;
	}

	public static List<Experience> toEntityListFromDTO(List<ExperienceDTO> experiencesDTO) {
		if (experiencesDTO == null)
			return null;

		List<Experience> experiences = new ArrayList<>();

		for (ExperienceDTO experienceDTO : experiencesDTO) {
			experiences.add(ExperienceMapper.toEntity(experienceDTO));
		}

		return experiences;
	}

	public static List<Experience> toEntityListFromSummary(List<ExperienceSummaryDTO> summaries) {
		if (summaries == null)
			return null;

		List<Experience> experiences = new ArrayList<>();

		for (ExperienceSummaryDTO experienceDTO : summaries) {
			experiences.add(ExperienceMapper.toEntity(experienceDTO));
		}

		return experiences;
	}

}
