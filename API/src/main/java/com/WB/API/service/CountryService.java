package com.WB.API.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.dto.CountryDTO;
import com.WB.API.mapper.CountryMapper;
import com.WB.API.repository.CountryRepository;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	public List<CountryDTO> getCountries() {
		return CountryMapper.toDTOList(countryRepository.findAll());
	}

	public CountryDTO getContryById(Integer ID) {
		return CountryMapper.toDTO(countryRepository.findById(ID).get());
	}
}
