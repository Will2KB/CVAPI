package com.WB.API;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

//	@Autowired
//	private CityService cityService;
//
//	@Autowired
//	private PersonService personService;
//
//	@Autowired
//	private NationalityService nationalityService;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		Iterable<Country> countries = countryService.getCountries();
//		countries.forEach(country -> System.out.println(country.getName()));

//		City city = cityService.getCityByID(2);
//		System.out.println(city.getName());
//		System.out.println(city.getCountry().getName());
//
//		Person person = personService.getPerson(1);
//		System.out.println(person.getFirstName() + " " + person.getName());
//		System.out.println(person.getBirthday());
//		System.out.println(person.getTitle());
//		System.out.println(person.getAge());

//		Nationality newNationality = new Nationality();
//		newNationality.setName("Espagnole");
//
//		Nationality frenchNationality = nationalityService.getNationalityById(1);
//
//		Person newPerson = new Person();
//		newPerson.setName("Dupont");
//		newPerson.setFirstName("Toto");
//		newPerson.setMail("toto@gmail.com");
//		newPerson.addNationality(newNationality);
//		newPerson.addNationality(frenchNationality);
//		newPerson = personService.savePerson(newPerson);
//		System.out.println(newPerson.getId());
//		System.out.println(newPerson.getFirstName() + " " + newPerson.getName());
//		System.out.println(newPerson.getMail());

	}

}
