package com.WB.API.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore les champs null dans le JSON
public class ExperienceSummaryDTO {

	private int id;

	@NotBlank(message = "Le nom de l'expérience ne peut pas être vide")
	private String name;

	@NotNull(message = "La date de début d'expérience doit être renseigné")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateBeginning;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateEnding;

	private EstablishmentDTO establishment;
	private int cityId;
	private String cityName;
	private String countryName;
	private String mission;
	@JsonProperty("hasDescription")
	private boolean hasDescription;
	@JsonProperty("hasSkills")
	private boolean hasSkills;
	@JsonProperty("isFormation")
	private boolean isFormation;

	public ExperienceSummaryDTO() {

	}

	public ExperienceSummaryDTO(int id, String name, LocalDate dateBeginning, LocalDate dateEnding,
			boolean isFormation) {
		this();
		this.setId(id);
		this.setName(name);
		this.setDateBeginning(dateBeginning);
		this.setDateEnding(dateEnding);
		this.setFormation(isFormation);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateBeginning() {
		return dateBeginning;
	}

	public void setDateBeginning(LocalDate dateBeginning) {
		this.dateBeginning = dateBeginning;
	}

	public LocalDate getDateEnding() {
		return dateEnding;
	}

	public void setDateEnding(LocalDate dateEnding) {
		this.dateEnding = dateEnding;
	}

	public EstablishmentDTO getEstablishment() {
		return establishment;
	}

	public void setEstablishment(EstablishmentDTO establishment) {
		this.establishment = establishment;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public boolean hasDescription() {
		return hasDescription;
	}

	public void setHasDescription(boolean hasDscription) {
		this.hasDescription = hasDscription;
	}

	public boolean hasSkills() {
		return hasSkills;
	}

	public void setHasSkills(boolean hasSkills) {
		this.hasSkills = hasSkills;
	}

	@JsonIgnore
	public boolean isFormation() {
		return isFormation;
	}

	public void setFormation(boolean isFormation) {
		this.isFormation = isFormation;
	}

}
