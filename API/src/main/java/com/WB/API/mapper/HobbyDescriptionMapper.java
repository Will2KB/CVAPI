package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.HobbyDescriptionDTO;
import com.WB.API.model.HobbyDescription;

public class HobbyDescriptionMapper {
	public static HobbyDescriptionDTO toDTO(HobbyDescription hobbyDescription) {

		HobbyDescriptionDTO hobbyDescriptionDTO = new HobbyDescriptionDTO();
		hobbyDescriptionDTO.setId(hobbyDescription.getId());
		hobbyDescriptionDTO.setDescription(hobbyDescription.getDescription());

		return hobbyDescriptionDTO;
	}

	public static List<HobbyDescriptionDTO> toDTOList(List<HobbyDescription> hobbyDescriptions) {
		List<HobbyDescriptionDTO> hobbyDescriptionsDTO = new ArrayList<>();

		for (HobbyDescription hobbyDescription : hobbyDescriptions) {
			hobbyDescriptionsDTO.add(HobbyDescriptionMapper.toDTO(hobbyDescription));
		}

		return hobbyDescriptionsDTO;
	}

	public static HobbyDescription toEntity(HobbyDescriptionDTO hobbyDescriptionDTO) {
		HobbyDescription hobbyDescription = new HobbyDescription(hobbyDescriptionDTO.getId(),
				hobbyDescriptionDTO.getDescription());
		return hobbyDescription;
	}

	public static List<HobbyDescription> toEntityList(List<HobbyDescriptionDTO> hobbyDescriptionsDTO) {
		List<HobbyDescription> hobbyDescriptions = new ArrayList<>();

		for (HobbyDescriptionDTO hobbyDescriptionDTO : hobbyDescriptionsDTO) {
			hobbyDescriptions.add(toEntity(hobbyDescriptionDTO));
		}

		return hobbyDescriptions;
	}
}
