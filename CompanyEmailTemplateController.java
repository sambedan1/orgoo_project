package com.tuespotsolutions.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuespotsolutions.models.CompanyEmailTemplateRequest;
import com.tuespotsolutions.models.CompanyEmailTemplateResponse;
import com.tuespotsolutions.models.EmailTemplateResponse;
import com.tuespotsolutions.service.CompanyEmailTemplateService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/companyemail")
public class CompanyEmailTemplateController {

	@Autowired
	private CompanyEmailTemplateService companyEmailTemplateService;

	@GetMapping("/get/template")
	public ResponseEntity<?> getTemplateList() {
		return new ResponseEntity<List<EmailTemplateResponse>>(companyEmailTemplateService.getEmailTemplate(),
				HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<?> getTemplateByCompanyIdAndJobId(
			@Valid @RequestBody CompanyEmailTemplateRequest companyEmailTemplateRequest) {
		return new ResponseEntity<CompanyEmailTemplateResponse>(
				companyEmailTemplateService.getCompanyEmailTemplate(companyEmailTemplateRequest), HttpStatus.OK);
	}

}
