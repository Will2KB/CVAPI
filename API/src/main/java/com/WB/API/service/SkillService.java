package com.WB.API.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.dto.SkillDTO;
import com.WB.API.mapper.SkillMapper;
import com.WB.API.repository.SkillRepository;

@Service
public class SkillService {

	@Autowired
	private SkillRepository skillRepository;

	public SkillDTO getSkillByID(Integer ID) {
		return SkillMapper.toDTO(skillRepository.findById(ID).get());
	}

	public List<SkillDTO> getSkills() {
		return SkillMapper.toDTOList(skillRepository.findAll());
	}
}
