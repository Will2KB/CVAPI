package com.WB.API.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {

	private int id;
	private Integer streetNumber;
	@NotBlank(message = "Il faut au minimum une rue pour définir une adresse")
	private String street;
	private String complement;
	private CityDTO city;

	public AddressDTO() {

	}

	public AddressDTO(int id, Integer streetNumber, String street, String complement) {
		this();
		this.id = id;
		this.streetNumber = streetNumber;
		this.street = street;
		this.complement = complement;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public CityDTO getCity() {
		return city;
	}

	public void setCity(CityDTO city) {
		this.city = city;
	}

}
