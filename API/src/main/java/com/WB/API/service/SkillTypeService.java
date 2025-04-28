package com.WB.API.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.model.SkillType;
import com.WB.API.repository.SkillTypeRepository;

@Service
public class SkillTypeService {

	@Autowired
	private SkillTypeRepository skillTypeRepository;

	public SkillType getSkillTypeByID(Integer ID) {
		return skillTypeRepository.findById(ID).get();
	}

	public List<SkillType> getSkillTypes() {
		return skillTypeRepository.findAll();
	}
}
