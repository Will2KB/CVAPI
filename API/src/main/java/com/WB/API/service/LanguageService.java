package com.WB.API.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WB.API.dto.LanguageDTO;
import com.WB.API.mapper.LanguageMapper;
import com.WB.API.repository.LanguageRepository;

@Service
public class LanguageService {

	@Autowired
	private LanguageRepository languageRepository;

	public List<LanguageDTO> getLanguages() {
		return LanguageMapper.toDTOList(languageRepository.findAll());
	}
}
