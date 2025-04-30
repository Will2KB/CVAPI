package com.WB.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.dto.SkillDTO;
import com.WB.API.service.SkillService;

@RestController
public class SkillController {

	@Autowired
	private SkillService skillService;

	@GetMapping("/skills")
	public List<SkillDTO> getSkills() {
		return skillService.getSkills();
	}

	@GetMapping("/skills/id/{id}")
	public SkillDTO getSkill(@PathVariable int id) {
		return skillService.getSkillByID(id);
	}
}
