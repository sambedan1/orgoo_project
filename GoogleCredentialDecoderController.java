package com.tuespotsolutions.controller;

import jakarta.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuespotsolutions.service.GooglCredentialDecoderService;

@RestController
@RequestMapping("/google")
@CrossOrigin("*")
public class GoogleCredentialDecoderController {
	
	@Autowired
	GooglCredentialDecoderService credentialDecoderService;
	
	@GetMapping("/token")
	public ResponseEntity<?> GoogleCredentialDecode(@RequestParam ("token") String token) {
		System.out.println("line no:17  "+token);
		ResponseEntity<?> googleCredentialDecode = this.credentialDecoderService.googleCredentialDecode(token);
		return googleCredentialDecode;
		
	}

}
