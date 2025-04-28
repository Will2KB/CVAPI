package com.WB.API.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.model.Experience;
import com.WB.API.repository.ExperienceRepository;

@Service
public class ExperienceService {

	@Autowired
	private ExperienceRepository experienceRepository;

	public List<Experience> getExperiences() {
		return experienceRepository.findAll();
	}

	public Experience getExperienceById(int id) {
		return experienceRepository.findById(id).get();
	}

	public Experience saveExperience(Experience Experience) {
		return experienceRepository.save(Experience);
	}
}
