package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.HobbyDescriptionDTO;
import com.WB.API.model.HobbyDescription;

public class HobbyDescriptionMapper {
	public static HobbyDescriptionDTO toDTO(HobbyDescription hobbyDescription) {
		if (hobbyDescription == null)
			return null;

		HobbyDescriptionDTO hobbyDescriptionDTO = new HobbyDescriptionDTO();
		hobbyDescriptionDTO.setId(hobbyDescription.getId());
		hobbyDescriptionDTO.setDescription(hobbyDescription.getDescription());

		return hobbyDescriptionDTO;
	}

	public static List<HobbyDescriptionDTO> toDTOList(List<HobbyDescription> hobbyDescriptions) {
		if (hobbyDescriptions == null)
			return null;

		List<HobbyDescriptionDTO> hobbyDescriptionsDTO = new ArrayList<>();

		for (HobbyDescription hobbyDescription : hobbyDescriptions) {
			hobbyDescriptionsDTO.add(HobbyDescriptionMapper.toDTO(hobbyDescription));
		}

		return hobbyDescriptionsDTO;
	}

	public static HobbyDescription toEntity(HobbyDescriptionDTO hobbyDescriptionDTO) {
		if (hobbyDescriptionDTO == null)
			return null;

		HobbyDescription hobbyDescription = new HobbyDescription(hobbyDescriptionDTO.getId(),
				hobbyDescriptionDTO.getDescription());
		return hobbyDescription;
	}

	public static List<HobbyDescription> toEntityList(List<HobbyDescriptionDTO> hobbyDescriptionsDTO) {
		if (hobbyDescriptionsDTO == null)
			return null;

		List<HobbyDescription> hobbyDescriptions = new ArrayList<>();

		for (HobbyDescriptionDTO hobbyDescriptionDTO : hobbyDescriptionsDTO) {
			hobbyDescriptions.add(toEntity(hobbyDescriptionDTO));
		}

		return hobbyDescriptions;
	}
}
