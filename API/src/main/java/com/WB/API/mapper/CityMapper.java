package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.CityDTO;
import com.WB.API.model.City;
import com.WB.API.model.Country;

public class CityMapper {

	public static CityDTO toDTO(City city) {
		if (city == null) {
			return null;
		}
		CityDTO dto = new CityDTO();
		dto.setId(city.getId());
		dto.setName(city.getName());
		dto.setZipCode(city.getZipCode());
		if (city.getCountry() != null) {
			dto.setCountryId(city.getCountry().getId());
			dto.setCountryName(city.getCountry().getName());
		}
		return dto;
	}

	public static List<CityDTO> toDTOList(List<City> cities) {
		if (cities == null)
			return null;

		List<CityDTO> citiesDTO = new ArrayList<>();

		for (City city : cities) {
			citiesDTO.add(CityMapper.toDTO(city));
		}

		return citiesDTO;
	}

	public static City toEntity(CityDTO dto) {
		if (dto == null) {
			return null;
		}
		City city = new City(dto.getId(), dto.getName(), dto.getZipCode());

		Country country = new Country(dto.getCountryId(), dto.getCountryName());
		city.setCountry(country);

		return city;
	}

}
