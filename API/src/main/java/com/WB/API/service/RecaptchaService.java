package com.WB.API.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class RecaptchaService {

	@Value("${recaptcha.secret}")
	private String recaptchaSecret;

	@Value("${recaptcha.verify.url}")
	private String recaptchaVerifyUrl;

	@Value("${recaptcha.threshold}")
	private float threshold;

	private final RestTemplate restTemplate;

	private static final Logger logger = LoggerFactory.getLogger(RecaptchaService.class);

	public RecaptchaService() {
		this.restTemplate = new RestTemplate();
	}

	public RecaptchaService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public boolean isCaptchaValid(String token) {
		// Préparation des paramètres de la requête
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		params.add("secret", recaptchaSecret);
		params.add("response", token);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

		try {
			ResponseEntity<Map> response = restTemplate.postForEntity(recaptchaVerifyUrl, request, Map.class);

			// Si la réponse est null ou vide
			if (response == null || response.getBody() == null) {
				logger.warn("reCAPTCHA failed: response is null");
				return false;
			}

			Map<String, Object> recaptchaResponse = response.getBody();

			// Si la vérification du reCaptcha a échoué
			if (Boolean.FALSE.equals(recaptchaResponse.get("success"))) {
				logger.warn("reCAPTCHA failed: invalid token or API error.");
				logger.info("reCAPTCHA Response: " + recaptchaResponse);
				return false;
			}

			// Si le score du reCaptcha n'est pas suffisant
			if ((double) recaptchaResponse.get("score") <= threshold) {
				logger.warn("reCAPTCHA rejected with low score: " + recaptchaResponse.get("score"));
				return false;
			}

			logger.info("Return true");

			return true;

		} catch (Exception ex) {
			// Journaliser si besoin
			logger.warn("Error during reCAPTCHA validation " + ex);
			return false;
		}
	}
}