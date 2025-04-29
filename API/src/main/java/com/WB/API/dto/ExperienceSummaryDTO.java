package com.WB.API.dto;

import java.time.LocalDate;

public class ExperienceSummaryDTO {

	private int id;
	private String name;
	private LocalDate dateBeginning;
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
