package com.WB.API.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.model.Skill;
import com.WB.API.repository.SkillRepository;

@Service
public class SkillService {

	@Autowired
	private SkillRepository skillRepository;

	public Skill getSkillByID(Integer ID) {
		return skillRepository.findById(ID).get();
	}

	public List<Skill> getSkills() {
		return skillRepository.findAll();
	}
}
