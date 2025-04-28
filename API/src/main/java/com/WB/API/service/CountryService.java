package com.WB.API.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.model.Country;
import com.WB.API.repository.CountryRepository;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	public List<Country> getCountries() {
		return countryRepository.findAll();
	}

	public Country getContryById(Integer ID) {
		return countryRepository.findById(ID).get();
	}
}
