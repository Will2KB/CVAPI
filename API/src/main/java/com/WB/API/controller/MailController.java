package com.WB.API.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.dto.MailDTO;
import com.WB.API.service.MailService;
import com.WB.API.service.RecaptchaService;

@RestController
public class MailController {

	@Autowired
	private MailService mailService;

	@Autowired
	private RecaptchaService recaptchaService;

	@PostMapping("/mail")
	public ResponseEntity<Map<String, String>> sendMail(@RequestBody MailDTO mailDTO) {
		if (recaptchaService.isCaptchaValid(mailDTO.getReCaptchaToken())) {
			mailService.sendSimpleMail(mailDTO);
			return ResponseEntity.ok(Map.of("message", "Message envoyé avec succès !"));
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body(Map.of("error", "Échec de la vérification reCAPTCHA"));
		}

	}
}