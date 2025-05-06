package com.WB.API.mapper;

import com.WB.API.dto.AddressDTO;
import com.WB.API.model.Address;

public class AddressMapper {

	public static AddressDTO toDTO(Address address) {
		if (address == null)
			return null;

		AddressDTO addressDTO = new AddressDTO();

		addressDTO.setId(address.getId());
		addressDTO.setStreetNumber(address.getStreetNumber());
		addressDTO.setStreet(address.getStreet());
		addressDTO.setComplement(address.getComplement());
		addressDTO.setCity(CityMapper.toDTO(address.getCity()));

		return addressDTO;
	}

	public static Address toEntity(AddressDTO addressDTO) {
		if (addressDTO == null)
			return null;

		Address address = new Address(addressDTO.getId());

		address.setStreetNumber(addressDTO.getStreetNumber());
		address.setStreet(addressDTO.getStreet());
		address.setComplement(addressDTO.getComplement());
		address.setCity(CityMapper.toEntity(addressDTO.getCity()));

		return address;
	}
}
