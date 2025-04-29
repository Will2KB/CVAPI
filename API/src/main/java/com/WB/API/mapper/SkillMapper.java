package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.SkillDTO;
import com.WB.API.model.Skill;

public class SkillMapper {

	private int id;
	private String name;
	private int typeId;
	private String typeName;
	private boolean enable;

	public static SkillDTO toDTO(Skill skill) {
		SkillDTO skillDTO = new SkillDTO();

		skillDTO.setId(skill.getId());
		skillDTO.setName(skill.getName());
		skillDTO.setEnable(skill.isEnable());
		skillDTO.setTypeId(skill.getType().getId());
		skillDTO.setTypeName(skill.getType().getName());

		return skillDTO;
	}

	public static List<SkillDTO> toDTOList(List<Skill> skills) {
		List<SkillDTO> skillsDTO = new ArrayList<>();

		for (Skill skill : skills) {
			skillsDTO.add(SkillMapper.toDTO(skill));
		}

		return skillsDTO;
	}

	public static Skill toEntity(SkillDTO skillDTO) {
		Skill skill = new Skill(skillDTO.getId(), skillDTO.getTypeId());

		return skill;
	}

	public static List<Skill> toEntityList(List<SkillDTO> skillsDTO) {
		List<Skill> skills = new ArrayList<>();

		for (SkillDTO skillDTO : skillsDTO) {
			skills.add(SkillMapper.toEntity(skillDTO));
		}

		return skills;
	}

}
