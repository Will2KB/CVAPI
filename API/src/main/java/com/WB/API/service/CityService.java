package com.WB.API.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.model.City;
import com.WB.API.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	public City getCityByID(Integer ID) {
		return cityRepository.findById(ID).get();
	}

	public City getCityByName(String name) {
		return cityRepository.findFirstCityByName(name);
	}

	public List<City> getCities() {
		return cityRepository.findAll();
	}
}
