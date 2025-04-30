package com.WB.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.dto.LanguageDTO;
import com.WB.API.service.LanguageService;

@RestController
public class LanguageController {

	@Autowired
	private LanguageService languageService;

	@GetMapping("/languages")
	public List<LanguageDTO> getLanguages() {
		return languageService.getLanguages();
	}

}
