package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.HobbyDTO;
import com.WB.API.model.Hobby;

public class HobbyMapper {

	public static HobbyDTO toDTO(Hobby hobby) {
		if (hobby == null)
			return null;

		HobbyDTO hobbyDTO = new HobbyDTO();
		hobbyDTO.setId(hobby.getId());
		hobbyDTO.setName(hobby.getName());
		hobbyDTO.setDescriptions(HobbyDescriptionMapper.toDTOList(hobby.getDescriptions()));
		return hobbyDTO;
	}

	public static List<HobbyDTO> toDTOList(List<Hobby> hobbies) {
		if (hobbies == null)
			return null;

		List<HobbyDTO> hobbiesDTO = new ArrayList<>();
		for (Hobby hobby : hobbies) {
			hobbiesDTO.add(HobbyMapper.toDTO(hobby));
		}
		return hobbiesDTO;
	}

	public static Hobby toEntity(HobbyDTO hobbyDTO) {
		if (hobbyDTO == null)
			return null;

		Hobby hobby = new Hobby(hobbyDTO.getId());
		hobby.setName(hobbyDTO.getName());
		hobby.setDescriptions(HobbyDescriptionMapper.toEntityList(hobbyDTO.getDescriptions()));
		return hobby;
	}

	public static List<Hobby> toEntityList(List<HobbyDTO> hobbiesDTO) {
		if (hobbiesDTO == null)
			return null;

		List<Hobby> hobbies = new ArrayList<>();
		for (HobbyDTO hobbyDTO : hobbiesDTO) {
			hobbies.add(HobbyMapper.toEntity(hobbyDTO));
		}
		return hobbies;
	}
}
