package com.WB.API.dto;

import jakarta.validation.constraints.NotBlank;

public class CityDTO {

	private int id;
	@NotBlank(message = "Un nom de ville est nécessaire")
	private String name;
	private Integer zipCode;
	private int countryId;
	private String countryName;

	public CityDTO() {

	}

	public CityDTO(int id, String name, Integer zipCode) {
		this();
		this.setId(id);
		this.setName(name);
		this.setZipCode(zipCode);
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

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
