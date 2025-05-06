package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.SkillDTO;
import com.WB.API.model.Skill;

public class SkillMapper {

	public static SkillDTO toDTO(Skill skill) {
		if (skill == null)
			return null;

		SkillDTO skillDTO = new SkillDTO();

		skillDTO.setId(skill.getId());
		skillDTO.setName(skill.getName());
		skillDTO.setEnable(skill.isEnable());
		if (skill.getType() != null) {
			skillDTO.setTypeId(skill.getType().getId());
			skillDTO.setTypeName(skill.getType().getName());
		}

		return skillDTO;
	}

	public static List<SkillDTO> toDTOList(List<Skill> skills) {
		if (skills == null)
			return null;

		List<SkillDTO> skillsDTO = new ArrayList<>();

		for (Skill skill : skills) {
			skillsDTO.add(SkillMapper.toDTO(skill));
		}

		return skillsDTO;
	}

	public static Skill toEntity(SkillDTO skillDTO) {
		if (skillDTO == null)
			return null;

		Skill skill = new Skill(skillDTO.getId(), skillDTO.getTypeId());
		skill.setName(skillDTO.getName());
		skill.setEnable(skillDTO.isEnable());

		return skill;
	}

	public static List<Skill> toEntityList(List<SkillDTO> skillsDTO) {
		if (skillsDTO == null)
			return null;

		List<Skill> skills = new ArrayList<>();

		for (SkillDTO skillDTO : skillsDTO) {
			skills.add(SkillMapper.toEntity(skillDTO));
		}

		return skills;
	}

}
