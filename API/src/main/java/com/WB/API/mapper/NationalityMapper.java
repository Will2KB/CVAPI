package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.NationalityDTO;
import com.WB.API.model.Nationality;

public class NationalityMapper {

	public static NationalityDTO toDTO(Nationality nationality) {
		NationalityDTO nationalityDTO = new NationalityDTO();

		nationalityDTO.setId(nationality.getId());
		nationalityDTO.setName(nationality.getName());

		return nationalityDTO;
	}

	public static List<NationalityDTO> toDTOList(List<Nationality> nationalities) {
		List<NationalityDTO> nationalitiesDTO = new ArrayList<>();

		for (Nationality nationality : nationalities) {
			nationalitiesDTO.add(NationalityMapper.toDTO(nationality));
		}

		return nationalitiesDTO;
	}

	public static Nationality toEntity(NationalityDTO nationalityDTO) {
		Nationality nationality = new Nationality(nationalityDTO.getId(), nationalityDTO.getName());
		return nationality;
	}

	public static List<Nationality> toEntityList(List<NationalityDTO> nationalitiesDTO) {
		List<Nationality> nationalities = new ArrayList<>();

		for (NationalityDTO nationalityDTO : nationalitiesDTO) {
			nationalities.add(NationalityMapper.toEntity(nationalityDTO));
		}

		return nationalities;
	}

}
