package com.WB.API.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.model.Nationality;
import com.WB.API.repository.NationalityRepository;

@Service
public class NationalityService {

	@Autowired
	private NationalityRepository nationalityRepository;

	public List<Nationality> getNationalities() {
		return nationalityRepository.findAll();
	}

	public Nationality getNationalityById(int id) {
		return nationalityRepository.findById(id).get();
	}

}
