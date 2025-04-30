package com.WB.API.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.dto.SkillTypeDTO;
import com.WB.API.mapper.SkillTypeMapper;
import com.WB.API.repository.SkillTypeRepository;

@Service
public class SkillTypeService {

	@Autowired
	private SkillTypeRepository skillTypeRepository;

	public SkillTypeDTO getSkillTypeByID(Integer ID) {
		return SkillTypeMapper.toDTO(skillTypeRepository.findById(ID).get());
	}

	public List<SkillTypeDTO> getSkillTypes() {
		return SkillTypeMapper.toDTOList(skillTypeRepository.findAll());
	}
}
