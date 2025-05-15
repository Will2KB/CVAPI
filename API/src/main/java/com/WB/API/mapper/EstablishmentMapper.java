package com.WB.API.mapper;

import com.WB.API.dto.EstablishmentDTO;
import com.WB.API.model.Address;
import com.WB.API.model.Establishment;

/**
 * Classe permettant de mapper une entité en objet de trasnfert et inverssement
 * pour les Establishment
 */
public class EstablishmentMapper {

	/**
	 * Transfert une entité en objet de transfert
	 * 
	 * @param establishement: Entité à mapper
	 * 
	 * @return Retourne l'objet de transfert après le mappage
	 */
	public static EstablishmentDTO toDTO(Establishment establishment) {
		// Si l'entité d'entrée est null => on retroune null
		if (establishment == null)
			return null;

		// Création de l'objet de transfert
		EstablishmentDTO establishmentDTO = new EstablishmentDTO();

		// Mappage des paramètres
		establishmentDTO.setId(establishment.getId());
		establishmentDTO.setName(establishment.getName());
		establishmentDTO.setAddressId(establishment.getAddress().getId());
		// Mappage si il y a une adresse
		if (establishment.getAddress() != null) {
			// Mappage si il y a une ville
			if (establishment.getAddress().getCity() != null) {
				establishmentDTO.setCityName(establishment.getAddress().getCity().getName());
				// Mappage si il y un pays
				if (establishment.getAddress().getCity().getCountry() != null) {
					establishmentDTO.setCountryName(establishment.getAddress().getCity().getCountry().getName());
				}
			}
		}

		return establishmentDTO;
	}

	/**
	 * Transfert un objet de transfert en entité
	 * 
	 * @param establishmentDTO: Objet de transfert à mapper
	 * 
	 * @return Retourne l'entité après le mappage
	 */
	public static Establishment toEntity(EstablishmentDTO establishmentDTO) {
		// Si l'objet de transfert d'entrée est null => on retroune null
		if (establishmentDTO == null)
			return null;

		// Création de l'entité
		Establishment establishment = new Establishment(establishmentDTO.getId());

		// Mappage des paramètres
		establishment.setName(establishmentDTO.getName());
		establishment.setAddress(new Address(establishmentDTO.getAddressId()));

		return establishment;
	}

}
