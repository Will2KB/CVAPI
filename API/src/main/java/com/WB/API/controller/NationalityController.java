package com.WB.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.model.Nationality;
import com.WB.API.service.NationalityService;

@RestController
public class NationalityController {

	@Autowired
	private NationalityService nationalityService;

	@GetMapping("/nationalities")
	public List<Nationality> getNationalitys() {
		return nationalityService.getNationalities();
	}

	@GetMapping("/nationalities/id/{id}")
	public Nationality getNationality(@PathVariable int id) {
		return nationalityService.getNationalityById(id);
	}
}
