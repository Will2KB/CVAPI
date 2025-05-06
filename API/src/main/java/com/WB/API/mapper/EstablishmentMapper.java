package com.WB.API.mapper;

import com.WB.API.dto.EstablishmentDTO;
import com.WB.API.model.Address;
import com.WB.API.model.Establishment;

public class EstablishmentMapper {

	public static EstablishmentDTO toDTO(Establishment establishment) {
		if (establishment == null)
			return null;

		EstablishmentDTO establishmentDTO = new EstablishmentDTO();

		establishmentDTO.setId(establishment.getId());
		establishmentDTO.setName(establishment.getName());
		establishmentDTO.setAddressId(establishment.getAddress().getId());
		if (establishment.getAddress() != null) {
			if (establishment.getAddress().getCity() != null) {
				establishmentDTO.setCityName(establishment.getAddress().getCity().getName());
				if (establishment.getAddress().getCity().getCountry() != null) {
					establishmentDTO.setCountryName(establishment.getAddress().getCity().getCountry().getName());
				}
			}
		}

		return establishmentDTO;
	}

	public static Establishment toEntity(EstablishmentDTO establishmentDTO) {
		if (establishmentDTO == null)
			return null;

		Establishment establishment = new Establishment(establishmentDTO.getId());

		establishment.setName(establishmentDTO.getName());
		establishment.setAddress(new Address(establishmentDTO.getAddressId()));

		return establishment;
	}

}
