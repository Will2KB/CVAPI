package com.WB.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.dto.SkillTypeDTO;
import com.WB.API.service.SkillTypeService;

@RestController
public class SkillTypeController {

	@Autowired
	private SkillTypeService skillTypeService;

	@GetMapping("/skillTypes")
	public List<SkillTypeDTO> getSkillTypes() {
		return skillTypeService.getSkillTypes();
	}

	@GetMapping("/skillTypes/id/{id}")
	public SkillTypeDTO getSkillType(@PathVariable int id) {
		return skillTypeService.getSkillTypeByID(id);
	}
}
