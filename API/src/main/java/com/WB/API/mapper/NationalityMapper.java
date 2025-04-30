package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.NationalityDTO;
import com.WB.API.model.Nationality;

public class NationalityMapper {

	public static NationalityDTO toDTO(Nationality nationality) {
		if (nationality == null)
			return null;

		NationalityDTO nationalityDTO = new NationalityDTO();

		nationalityDTO.setId(nationality.getId());
		nationalityDTO.setName(nationality.getName());

		return nationalityDTO;
	}

	public static List<NationalityDTO> toDTOList(List<Nationality> nationalities) {
		if (nationalities == null)
			return null;

		List<NationalityDTO> nationalitiesDTO = new ArrayList<>();

		for (Nationality nationality : nationalities) {
			nationalitiesDTO.add(NationalityMapper.toDTO(nationality));
		}

		return nationalitiesDTO;
	}

	public static Nationality toEntity(NationalityDTO nationalityDTO) {
		if (nationalityDTO == null)
			return null;

		Nationality nationality = new Nationality(nationalityDTO.getId(), nationalityDTO.getName());
		return nationality;
	}

	public static List<Nationality> toEntityList(List<NationalityDTO> nationalitiesDTO) {
		if (nationalitiesDTO == null)
			return null;

		List<Nationality> nationalities = new ArrayList<>();

		for (NationalityDTO nationalityDTO : nationalitiesDTO) {
			nationalities.add(NationalityMapper.toEntity(nationalityDTO));
		}

		return nationalities;
	}

}
