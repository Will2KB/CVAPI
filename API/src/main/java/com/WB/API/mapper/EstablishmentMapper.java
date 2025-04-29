package com.WB.API.mapper;

import com.WB.API.dto.EstablishmentDTO;
import com.WB.API.model.Address;
import com.WB.API.model.Establishement;

public class EstablishmentMapper {

	private int id;
	private String name;
	private int addressId;
	private String cityName;
	private String countryName;

	public static EstablishmentDTO toDTO(Establishement establishment) {
		EstablishmentDTO establishmentDTO = new EstablishmentDTO();

		establishmentDTO.setId(establishment.getId());
		establishmentDTO.setName(establishment.getName());
		establishmentDTO.setAddressId(establishment.getAddress().getId());
		establishmentDTO.setCityName(establishment.getAddress().getCity().getName());
		establishmentDTO.setCountryName(establishment.getAddress().getCity().getCountry().getName());

		return establishmentDTO;
	}

	public static Establishement toEntity(EstablishmentDTO establishmentDTO) {
		Establishement establishment = new Establishement(establishmentDTO.getId());

		establishment.setName(establishmentDTO.getName());
		establishment.setAddress(new Address(establishmentDTO.getAddressId()));

		return establishment;
	}

}
