package com.WB.API.controller;

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
	public ResponseEntity<String> sendMail(@RequestBody MailDTO mailDTO) {
		if (!recaptchaService.isCaptchaValid(mailDTO.getReCaptchaToken())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Échec de la vérification reCAPTCHA");
		}

		mailService.sendSimpleMail(mailDTO);
		return ResponseEntity.ok("Message envoyé avec succès !");
	}
}