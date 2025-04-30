package com.WB.API.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

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

	private int establishmentId;
	private int cityId;
	private String cityName;
	private String countryName;
	private String mission;
	private boolean isFormation;

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

	public int getEstablishmentId() {
		return establishmentId;
	}

	public void setEstablishmentId(int establishmentId) {
		this.establishmentId = establishmentId;
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

	public boolean isFormation() {
		return isFormation;
	}

	public void setFormation(boolean isFormation) {
		this.isFormation = isFormation;
	}

}
