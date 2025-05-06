package com.WB.API.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstablishmentDTO {

	private int id;
	@NotBlank(message = "Un nom d'établissement est nécessaire")
	private String name;
	private int addressId;
	private String cityName;
	private String countryName;

	public EstablishmentDTO() {

	}

	public EstablishmentDTO(int id, String name, int addressId) {
		this();
		this.id = id;
		this.name = name;
		this.addressId = addressId;
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

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
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

}
