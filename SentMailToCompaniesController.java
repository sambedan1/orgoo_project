package com.tuespotsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuespotsolutions.service.SentMailToCompaniesService;

@RestController
@CrossOrigin("*")
@RequestMapping("/sendmail")
public class SentMailToCompaniesController {

	
	@Autowired
	private SentMailToCompaniesService sentMailToCompaniesService;
	
	@PostMapping("/to/companies")
	public void sentMailToCompanies() {
		sentMailToCompaniesService.sentMailToCompanies();
	}
	
	
}
