package com.WB.API.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.Valid;

@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore les champs null dans le JSON
public class ExperienceDTO {

	@Valid
	private ExperienceSummaryDTO summary;
	private EstablishmentDTO establishment;
	private CityDTO city;

	private String description;
	private List<SkillDTO> skills;

	public ExperienceDTO() {
		this.loadSummary();
		this.skills = new ArrayList<>();
	}

	public ExperienceDTO(int id, String name, LocalDate begin, LocalDate end, boolean isFormation, String mission) {
		this();
		this.summary.setId(id);
		this.summary.setName(name);
		this.summary.setDateBeginning(begin);
		this.summary.setDateEnding(end);
		this.summary.setFormation(isFormation);
		this.setMission(mission);
	}

	private void loadSummary() {
		if (this.summary == null)
			this.summary = new ExperienceSummaryDTO();
	}

	public ExperienceSummaryDTO getSummary() {
		this.loadSummary();
		return summary;
	}

	public void setSummary(ExperienceSummaryDTO summary) {
		this.summary = summary;
	}

	public int getId() {
		return this.getSummary().getId();
	}

	public void setId(int id) {
		this.getSummary().setId(id);
	}

	public String getName() {
		return this.getSummary().getName();
	}

	public void setName(String name) {
		this.getSummary().setName(name);
	}

	public LocalDate getDateBeginning() {
		return this.getSummary().getDateBeginning();
	}

	public void setDateBeginning(LocalDate dateBeginning) {
		this.getSummary().setDateBeginning(dateBeginning);
	}

	public LocalDate getDateEnding() {
		return this.getSummary().getDateEnding();
	}

	public void setDateEnding(LocalDate dateEnding) {
		this.getSummary().setDateEnding(dateEnding);
	}

	public String getMission() {
		return getSummary().getMission();
	}

	public void setMission(String mission) {
		this.getSummary().setMission(mission);
	}

	public boolean isFormation() {
		return this.getSummary().isFormation();
	}

	public void setFormation(boolean isFormation) {
		this.getSummary().setFormation(isFormation);
	}

	public EstablishmentDTO getEstablishment() {
		return establishment;
	}

	public void setEstablishment(EstablishmentDTO establishment) {
		this.establishment = establishment;
		if (establishment != null) {
			this.getSummary().setEstablishmentId(establishment.getId());
		}
	}

	public CityDTO getCity() {
		return city;
	}

	public void setCity(CityDTO city) {
		this.city = city;
		if (city != null) {
			this.getSummary().setCityId(city.getId());
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SkillDTO> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillDTO> skills) {
		this.skills = skills;
	}

}
