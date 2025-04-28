package com.WB.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.model.City;
import com.WB.API.service.CityService;

@RestController
public class CityController {

	@Autowired
	private CityService cityService;

	@GetMapping("/cities")
	public List<City> getCitys() {
		return cityService.getCities();
	}

	@GetMapping("/cities/id/{id}")
	public City getCity(@PathVariable int id) {
		return cityService.getCityByID(id);
	}

	@GetMapping("/cities/name/{name}")
	public City getCity(@PathVariable String name) {
		return cityService.getCityByName(name);
	}
}
