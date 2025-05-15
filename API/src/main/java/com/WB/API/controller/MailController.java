package com.WB.API.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.WB.API.dto.MailDTO;
import com.WB.API.service.MailService;

@RestController
public class MailController {

	@Autowired
	private MailService mailService;

	@PostMapping("/mail")
	public ResponseEntity<Void> sendMail(@RequestBody MailDTO mailDTO) {
		mailService.sendSimpleMail(mailDTO);
		return ResponseEntity.ok().build();
	}
}