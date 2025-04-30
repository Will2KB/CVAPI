package com.WB.API.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.dto.ExperienceDTO;
import com.WB.API.dto.ExperienceSummaryDTO;
import com.WB.API.mapper.ExperienceMapper;
import com.WB.API.model.Experience;
import com.WB.API.repository.ExperienceRepository;

@Service
public class ExperienceService {

	@Autowired
	private ExperienceRepository experienceRepository;

	public List<ExperienceSummaryDTO> getExperiences() {
		return ExperienceMapper.toSummaryDTOList(experienceRepository.findAll());
	}

	public ExperienceDTO getExperienceById(int id) {
		return ExperienceMapper.toDTO(experienceRepository.findById(id).get());
	}

	public ExperienceSummaryDTO saveExperience(ExperienceDTO experience) {
		Experience toSave = ExperienceMapper.toEntity(experience);
		return ExperienceMapper.toSummaryDTO(experienceRepository.save(toSave));
	}
}
