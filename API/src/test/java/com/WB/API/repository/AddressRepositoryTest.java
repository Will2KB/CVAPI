package com.WB.API.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.WB.API.model.Address;

/*
 * Tests du repository des adresses
 * Lors de ces test, nous souhaitons vérifier le fonctionnement du repository 
 * Ces tests seront effectué sur une base de test H2
 */
@ActiveProfiles("test")
@DataJpaTest
@DisplayName("Test du repository des adresses")
class AddressRepositoryTest {

	@Autowired
	private AddressRepository addressRepository;

	private List<Address> addresses;

	/**
	 * Chargement des données de tests spécifique au repository
	 */
	@BeforeEach
	void loadData() {

		addresses = new ArrayList<>();

		Address address1 = new Address();
		address1.setStreetNumber(12);
		address1.setStreet("Rue du Général");
		address1.setComplement("Bis");
		addressRepository.save(address1);
		addresses.add(address1);

		Address address2 = new Address();
		address2.setStreetNumber(253);
		address2.setStreet("Rue du Maréchal");
		address2.setComplement("Ter");
		addressRepository.save(address2);
		addresses.add(address2);

		Address address3 = new Address();
		address3.setStreetNumber(75);
		address3.setStreet("Rue Peter");
		addressRepository.save(address3);
		addresses.add(address3);

		Address address4 = new Address();
		address4.setStreetNumber(167);
		address4.setStreet("Rue Duchemin");
		address4.setComplement("Entrée A");
		addressRepository.save(address4);
		addresses.add(address4);
	}

	@Test
	@DisplayName("Chargement d'une adresse à partir de son ID")
	void findAddressById_ReturnCorrectAddress() {
		// Arrange
		Address searchAddress = addresses.get(2);

		// Act
		Optional<Address> optAddress = addressRepository.findById(searchAddress.getId());

		// Assert
		Assertions.assertTrue(optAddress.isPresent(),
				"L'adresse n'a pas été trouvée pour l'ID " + searchAddress.getId());
		Address address = optAddress.get();

		Assertions.assertNotNull(address);
		Assertions.assertEquals(searchAddress.getId(), address.getId());
		Assertions.assertEquals(searchAddress.getStreetNumber(), address.getStreetNumber());
		Assertions.assertEquals(searchAddress.getStreet(), address.getStreet());
		Assertions.assertEquals(searchAddress.getComplement(), address.getComplement());
	}

	@Test
	@DisplayName("Chargement d'une adresse à partir d'un ID inexistant")
	void findAddressById_ReturnNullWhenNotFound() {
		// Arrange
		Integer id = 999;

		// Act
		Optional<Address> optAddress = addressRepository.findById(id);

		// Assert
		Assertions.assertFalse(optAddress.isPresent(), "L'adresse a été trouvée pour l'ID " + id);
	}
}
