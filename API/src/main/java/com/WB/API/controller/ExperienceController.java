package com.WB.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.model.Experience;
import com.WB.API.service.ExperienceService;

import jakarta.validation.Valid;

@RestController
public class ExperienceController {

	@Autowired
	private ExperienceService experienceService;

	@GetMapping("/experiences")
	public List<Experience> getExperiences() {
		return experienceService.getExperiences();
	}

	@GetMapping("/experiences/id/{id}")
	public ResponseEntity<Experience> getExperience(@PathVariable int id) {
		Experience experience = experienceService.getExperienceById(id);
		if (experience == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(experience);
	}

	@PostMapping("/experiences")
	public Experience saveExperience(@Valid @RequestBody Experience experience) {
		return experienceService.saveExperience(experience);
	}
}
