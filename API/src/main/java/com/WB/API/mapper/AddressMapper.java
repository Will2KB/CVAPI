package com.WB.API.mapper;

import com.WB.API.dto.AddressDTO;
import com.WB.API.model.Address;

public class AddressMapper {

	private int id;
	private Integer streetNumber;
	private String street;
	private String complement;
	private CityMapper city;

	public static AddressDTO toDTO(Address address) {
		AddressDTO addressDTO = new AddressDTO();

		addressDTO.setId(address.getId());
		addressDTO.setStreetNumber(address.getNb_street());
		addressDTO.setStreet(address.getStreet());
		addressDTO.setComplement(address.getComplement());
		addressDTO.setCity(CityMapper.toDTO(address.getCity()));

		return addressDTO;
	}

	public static Address toEntity(AddressDTO addressDTO) {
		Address address = new Address(addressDTO.getId());

		address.setNb_street(addressDTO.getStreetNumber());
		address.setStreet(addressDTO.getStreet());
		address.setComplement(addressDTO.getComplement());
		address.setCity(CityMapper.toEntity(addressDTO.getCity()));

		return address;
	}
}
