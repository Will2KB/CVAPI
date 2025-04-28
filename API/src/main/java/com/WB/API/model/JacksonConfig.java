package com.WB.API.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class JacksonConfig {

	@Bean
	public Module javaTimeModule() {
		return new JavaTimeModule(); // Sérialisation et désérialisation des dates/temps
	}

	@Bean
	public ObjectMapper objectMapper(JavaTimeModule javaTimeModule) {
		return new ObjectMapper().registerModule(javaTimeModule); // Enregistre le module dans ObjectMapper
	}
}
