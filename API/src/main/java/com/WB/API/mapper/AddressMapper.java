package com.WB.API.mapper;

import com.WB.API.dto.AddressDTO;
import com.WB.API.model.Address;

/*
 * Classe permettant de mapper une entité en objet de trasnfert et inverssement
 * pour les Address
 */
public class AddressMapper {

	/*
	 * Transfert une entité en objet de transfert
	 * 
	 * @Param address: Entité à mapper
	 * 
	 * @Return Retourne l'objet de transfert après le mappage
	 */
	public static AddressDTO toDTO(Address address) {
		// Si l'entité d'entrée est null => on retroune null
		if (address == null)
			return null;

		// Création de l'objet de transfert
		AddressDTO addressDTO = new AddressDTO();

		// Mappage des paramètres
		addressDTO.setId(address.getId());
		addressDTO.setStreetNumber(address.getStreetNumber());
		addressDTO.setStreet(address.getStreet());
		addressDTO.setComplement(address.getComplement());
		addressDTO.setCity(CityMapper.toDTO(address.getCity()));

		return addressDTO;
	}

	/*
	 * Transfert un objet de transfert en entité
	 * 
	 * @Param addressDTO: Objet de transfert à mapper
	 * 
	 * @Return Retourne l'entité après le mappage
	 */
	public static Address toEntity(AddressDTO addressDTO) {
		// Si l'objet de transfert d'entrée est null => on retroune null
		if (addressDTO == null)
			return null;

		// Création de l'entité
		Address address = new Address(addressDTO.getId());

		// Mappage des paramètres
		address.setStreetNumber(addressDTO.getStreetNumber());
		address.setStreet(addressDTO.getStreet());
		address.setComplement(addressDTO.getComplement());
		address.setCity(CityMapper.toEntity(addressDTO.getCity()));

		return address;
	}
}
