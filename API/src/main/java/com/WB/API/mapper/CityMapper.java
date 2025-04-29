package com.WB.API.mapper;

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
		dto.setZip_code(city.getZip_code());
		if (city.getCountry() != null) {
			dto.setCountryId(city.getCountry().getId());
			dto.setCountryName(city.getCountry().getName());
		}
		return dto;
	}

	public static City toEntity(CityDTO dto) {
		if (dto == null) {
			return null;
		}
		City city = new City(dto.getId(), dto.getName(), dto.getZip_code());

		// Ici, on crée un objet Country minimal (sans tout charger depuis la BDD)
		Country country = new Country(dto.getCountryId(), dto.getCountryName());
		city.setCountry(country);

		return city;
	}

}
