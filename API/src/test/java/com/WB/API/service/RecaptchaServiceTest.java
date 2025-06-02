package com.WB.API.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.WB.API.dto.RecaptchaResponse;

/*
 * Test du service de validation du reCAPTACHA
 * Lors de ces test, nous souhaitons vérifier le fonctionnement du service 
 */
@ActiveProfiles("test")
@DisplayName("Test du service de validation du reCAPTACHA")
@ExtendWith(MockitoExtension.class)
class RecaptchaServiceTest {

	@InjectMocks
	private RecaptchaService recaptchaService;

	@Mock
	private RestTemplate restTemplate;

	@BeforeEach
	void setUp() {
		ReflectionTestUtils.setField(recaptchaService, "recaptchaSecret", "test-secret");
		ReflectionTestUtils.setField(recaptchaService, "recaptchaVerifyUrl",
				"https://www.google.com/recaptcha/api/siteverify");
		ReflectionTestUtils.setField(recaptchaService, "threshold", 0.5f);
	}

	@Test
	@DisplayName("Test la validation d'un reCAPTCHA valide")
	void testValidResponse_shouldReturnTrue() {
		// Arrange
		RecaptchaResponse mockResponse = new RecaptchaResponse();
		mockResponse.setSuccess(true);
		mockResponse.setScore(0.9f);

		ResponseEntity<RecaptchaResponse> responseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);
		when(restTemplate.postForEntity(anyString(), any(), eq(RecaptchaResponse.class))).thenReturn(responseEntity);

		// Act
		boolean result = recaptchaService.isCaptchaValid("valid-token");

		// Assert
		Assertions.assertTrue(result);
	}

	@Test
	@DisplayName("Test la validation d'un reCAPTCHA avec un score trop bas")
	void testScoreTooLow_shouldReturnFlase() {
		// Arrange
		RecaptchaResponse mockResponse = new RecaptchaResponse();
		mockResponse.setSuccess(true);
		mockResponse.setScore(0.3f);

		ResponseEntity<RecaptchaResponse> responseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);
		when(restTemplate.postForEntity(anyString(), any(), eq(RecaptchaResponse.class))).thenReturn(responseEntity);

		// Act
		boolean result = recaptchaService.isCaptchaValid("token-low-score");

		// Assert
		Assertions.assertFalse(result);
	}

	@Test
	@DisplayName("Test la validation d'un reCAPTCHA invalide")
	void testRecaptchaFails_shouldReturnFalse() {
		// Arrange
		RecaptchaResponse mockResponse = new RecaptchaResponse();
		mockResponse.setSuccess(false);
		mockResponse.setScore(0.9f);

		ResponseEntity<RecaptchaResponse> responseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);
		when(restTemplate.postForEntity(anyString(), any(), eq(RecaptchaResponse.class))).thenReturn(responseEntity);

		// Act
		boolean result = recaptchaService.isCaptchaValid("invalid-token");

		// Assert
		Assertions.assertFalse(result);
	}

	@Test
	@DisplayName("Test la validation d'un reCAPTCHA avec une exception levé par le serveur")
	void testExceptionThrown_shouldReturnFalse() {
		// Arrange
		when(restTemplate.postForEntity(anyString(), any(), eq(RecaptchaResponse.class)))
				.thenThrow(new RestClientException("API error"));

		// Act
		boolean result = recaptchaService.isCaptchaValid("token-exception");

		// Assert
		Assertions.assertFalse(result);
	}
}