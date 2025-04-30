package com.WB.API.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.dto.NationalityDTO;
import com.WB.API.mapper.NationalityMapper;
import com.WB.API.repository.NationalityRepository;

@Service
public class NationalityService {

	@Autowired
	private NationalityRepository nationalityRepository;

	public List<NationalityDTO> getNationalities() {
		return NationalityMapper.toDTOList(nationalityRepository.findAll());
	}

	public NationalityDTO getNationalityById(int id) {
		return NationalityMapper.toDTO(nationalityRepository.findById(id).get());
	}

}
