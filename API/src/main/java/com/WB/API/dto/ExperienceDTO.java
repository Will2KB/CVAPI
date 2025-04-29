package com.WB.API.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExperienceDTO {

	private ExperienceSummaryDTO summary;
	private EstablishmentDTO establishment;
	private CityDTO city;
	private String description;
	private List<SkillDTO> skills;

	public ExperienceDTO() {
		this.skills = new ArrayList<>();
	}

	public int getId() {
		return this.summary.getId();
	}

	public void setId(int id) {
		this.summary.setId(id);
	}

	public String getName() {
		return this.summary.getName();
	}

	public void setName(String name) {
		this.summary.setName(name);
	}

	public LocalDate getDateBeginning() {
		return this.summary.getDateBeginning();
	}

	public void setDateBeginning(LocalDate dateBeginning) {
		this.summary.setDateBeginning(dateBeginning);
	}

	public LocalDate getDateEnding() {
		return this.summary.getDateEnding();
	}

	public void setDateEnding(LocalDate dateEnding) {
		this.summary.setDateEnding(dateEnding);
	}

	public EstablishmentDTO getEstablishment() {
		return establishment;
	}

	public void setEstablishment(EstablishmentDTO establishment) {
		this.establishment = establishment;
		this.summary.setEstablishmentId(establishment.getId());
	}

	public CityDTO getCity() {
		return city;
	}

	public void setCity(CityDTO city) {
		this.city = city;
		this.summary.setCityId(city.getId());
	}

	public String getMission() {
		return summary.getMission();
	}

	public void setMission(String mission) {
		this.summary.setMission(mission);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFormation() {
		return this.summary.isFormation();
	}

	public void setFormation(boolean isFormation) {
		this.summary.setFormation(isFormation);
	}

	public ExperienceSummaryDTO getSummary() {
		return summary;
	}

	public void setSummary(ExperienceSummaryDTO summary) {
		this.summary = summary;
	}

	public List<SkillDTO> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillDTO> skills) {
		this.skills = skills;
	}

}
