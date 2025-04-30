package com.WB.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.dto.CityDTO;
import com.WB.API.service.CityService;

@RestController
public class CityController {

	@Autowired
	private CityService cityService;

	@GetMapping("/cities")
	public List<CityDTO> getCitys() {
		return cityService.getCities();
	}

	@GetMapping("/cities/id/{id}")
	public CityDTO getCity(@PathVariable int id) {
		return cityService.getCityByID(id);
	}

	@GetMapping("/cities/name/{name}")
	public CityDTO getCity(@PathVariable String name) {
		return cityService.getCityByName(name);
	}
}
