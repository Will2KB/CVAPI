package com.WB.API.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.dto.CityDTO;
import com.WB.API.mapper.CityMapper;
import com.WB.API.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	public CityDTO getCityByID(Integer ID) {
		return CityMapper.toDTO(cityRepository.findById(ID).get());
	}

	public CityDTO getCityByName(String name) {
		return CityMapper.toDTO(cityRepository.findFirstCityByName(name));
	}

	public List<CityDTO> getCities() {
		return CityMapper.toDTOList(cityRepository.findAll());
	}
}
