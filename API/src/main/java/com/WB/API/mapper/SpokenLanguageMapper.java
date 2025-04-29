package com.WB.API.mapper;

import java.util.ArrayList;
import java.util.List;

import com.WB.API.dto.SpokenLanguageDTO;
import com.WB.API.model.SpokenLanguage;

public class SpokenLanguageMapper {

	private int personId;
	private int languageId;
	private String name;
	private String level;

	public static SpokenLanguageDTO toDTO(SpokenLanguage language) {
		SpokenLanguageDTO languageDTO = new SpokenLanguageDTO();

		languageDTO.setLanguageId(language.getLanguage().getId());
		languageDTO.setName(language.getLanguage().getName());
		languageDTO.setPersonId(language.getPerson().getId());
		languageDTO.setLevel(language.getLevel());

		return languageDTO;
	}

	public static List<SpokenLanguageDTO> toDTOList(List<SpokenLanguage> languages) {
		List<SpokenLanguageDTO> languagesDTO = new ArrayList<>();

		for (SpokenLanguage spokenLanguage : languages) {
			languagesDTO.add(SpokenLanguageMapper.toDTO(spokenLanguage));
		}

		return languagesDTO;
	}

	public static SpokenLanguage toEntity(SpokenLanguageDTO languageDTO) {
		SpokenLanguage language = new SpokenLanguage(languageDTO.getPersonId(), languageDTO.getLanguageId());
		language.setLevel(languageDTO.getLevel());

		return language;
	}

	public static List<SpokenLanguage> toEntityList(List<SpokenLanguageDTO> languagesDTO) {
		List<SpokenLanguage> languages = new ArrayList<>();

		for (SpokenLanguageDTO spokenLanguageDTO : languagesDTO) {
			languages.add(SpokenLanguageMapper.toEntity(spokenLanguageDTO));
		}

		return languages;
	}
}
