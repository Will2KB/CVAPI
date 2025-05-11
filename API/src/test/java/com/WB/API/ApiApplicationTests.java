package com.WB.API;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import jakarta.annotation.PostConstruct;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
class ApiApplicationTests {
	@Autowired
	Environment env;

	@PostConstruct
	void debugProfile() {
		System.out.println("PROFILS ACTIFS : " + Arrays.toString(env.getActiveProfiles()));
		System.out.println("spring.datasource.url = " + env.getProperty("spring.datasource.url"));
	}
}