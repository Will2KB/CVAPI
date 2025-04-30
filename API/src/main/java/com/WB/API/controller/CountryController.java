package com.WB.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.dto.CountryDTO;
import com.WB.API.service.CountryService;

@RestController
public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping("/countries")
	public List<CountryDTO> getCountrys() {
		return countryService.getCountries();
	}

	@GetMapping("/countries/id/{id}")
	public CountryDTO getCountry(@PathVariable int id) {
		return countryService.getContryById(id);
	}

}
