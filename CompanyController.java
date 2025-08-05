package com.tuespotsolutions.controller;

import java.util.List;
import java.util.Map;

import com.tuespotsolutions.models.*;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuespotsolutions.service.CompanyRegistrationService;
import com.tuespotsolutions.service.OtpConfirmationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin("*")
@RequestMapping("/company")
public class CompanyController {
	Logger logger = LoggerFactory.getLogger(CompanyController.class);
	@GetMapping("/test")
	public String hello() {
		logger.trace("A TRACE Message");
		logger.debug("A DEBUG Message");
		logger.info("An INFO Message");
		logger.warn("A WARN Message");
		logger.error("An ERROR Message");
		return "Hello Scaler!";
	}
	@Autowired
	private CompanyRegistrationService companyRegistrationService;

	@Autowired
	private OtpConfirmationService otpConfirmationService;

	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> registerCompany(@Valid @RequestBody CompanyRequest companyRequest) {
		logger.info("line no : 58 registerCompany() method");
		Map<String, String> registerCompany = this.companyRegistrationService.registerCompany(companyRequest);
		return new ResponseEntity<Map<String, String>>(registerCompany, HttpStatus.OK);
	}

	@PostMapping("/registerWithoutOtp")
	public ResponseEntity<Map<String, String>> registerCompanyWithoutOtp(
			@Valid @RequestBody CompanyRequest companyRequest) {
		logger.info("line no : 58 registerCompany() method");
		Map<String, String> registerCompany = this.companyRegistrationService.registerCompanyWithoutOtp(companyRequest);
		return new ResponseEntity<Map<String, String>>(registerCompany, HttpStatus.OK);
	}

	@PostMapping("/enterotp")
	public ResponseEntity<?> enterOtp(@RequestBody OtpConfirmedRequest confirmedRequest) {
		logger.info("line no : 66 enterOtp() method");
		CompanyResponse otpConfirmation = this.otpConfirmationService.otpConfirmation(confirmedRequest);
		return new ResponseEntity<CompanyResponse>(otpConfirmation, HttpStatus.ACCEPTED);
	}

	@GetMapping("/profile")
	public ResponseEntity<?> getCompanyProfile(@RequestParam("companyId") long companyId) {
		CompanyProfileResponse companyProfileDetails = this.companyRegistrationService
				.getCompanyProfileDetails(companyId);
		return new ResponseEntity<CompanyProfileResponse>(companyProfileDetails, HttpStatus.OK);
	}

	@PutMapping("/update/profile")
	public ResponseEntity<?> updateComanyProfile(@Valid @RequestBody CompanyProfileRequest companyProfileResponse) {
		Map<String, String> updateCompanyProfile = this.companyRegistrationService
				.updateCompanyProfile(companyProfileResponse);
		return new ResponseEntity<Map<String, String>>(updateCompanyProfile, HttpStatus.OK);
	}
	

	@PatchMapping("/update/actuallEamil")
	public ResponseEntity<?> updateComanyActuallEmil(@Valid @RequestBody CompanyActuallEamilConfigRequest companyActuallEamilConfigRequest) {
		Map<String, String> updateCompanyProfile = this.companyRegistrationService
				.updateCompanyActuallEamilConfigRequest(companyActuallEamilConfigRequest);
		return new ResponseEntity<Map<String, String>>(updateCompanyProfile, HttpStatus.OK);
	}
	
	@PutMapping("/update/Status")
	public ResponseEntity<?> updateComanyStatus(@Valid @RequestBody CompanyStatusRequest companyStatusResponse) {
		Map<String, String> updateCompanyProfile = this.companyRegistrationService
				.updateComanyStatus(companyStatusResponse);
		return new ResponseEntity<Map<String, String>>(updateCompanyProfile, HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<?> companyList(
			@RequestParam(value = "keyword", defaultValue = "N/A", required = false) String keyword,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		CompanyListWithPagination companyList = this.companyRegistrationService.getCompanyList(keyword, pageNumber,
				pageSize);
		return new ResponseEntity<CompanyListWithPagination>(companyList, HttpStatus.OK);
	}
	@GetMapping("/botlist")
	public ResponseEntity<?> botcompanyList(
			@RequestParam(value = "keyword", defaultValue = "N/A", required = false) String keyword,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		CompanyListWithPagination companyList = this.companyRegistrationService.getBotCompanyList(keyword, pageNumber,
				pageSize);
		return new ResponseEntity<CompanyListWithPagination>(companyList, HttpStatus.OK);
	}
	@GetMapping("/orogoolist")
	public ResponseEntity<?> orogoocompanyList(
			@RequestParam(value = "keyword", defaultValue = "N/A", required = false) String keyword,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		CompanyListWithPagination companyList = this.companyRegistrationService.getorogoCompanyList(keyword, pageNumber,
				pageSize);
		return new ResponseEntity<CompanyListWithPagination>(companyList, HttpStatus.OK);
	}
	@GetMapping("/deatil")
	public ResponseEntity<?> getCompanyDetail(@RequestParam("companyId") long companyId) {
		CompanyDetailResponse response = this.companyRegistrationService.getCompanyDetailById(companyId);
		return new ResponseEntity<CompanyDetailResponse>(response, HttpStatus.OK);
	}

	@PutMapping("/update/interviewlink")
	public ResponseEntity<?> updateInterViewLink(@Valid @RequestBody UpdateCompanyInterViewLink companyInterViewLink) {
		Map<String, String> updateInterViewLink = this.companyRegistrationService
				.updateInterViewLink(companyInterViewLink);
		return new ResponseEntity<Map<String, String>>(updateInterViewLink, HttpStatus.OK);
	}

	@GetMapping("/byCompanyName")
	public ResponseEntity<?> getCompanyByCompanyName(@RequestParam("companyName") String companyName) {
		CompanyIdModel response = this.companyRegistrationService.getCompanyByCompanyName(companyName);
		return new ResponseEntity<CompanyIdModel>(response, HttpStatus.OK);
	}

	@GetMapping("/company/list")
	public ResponseEntity<?> companyAlList() {
		List<CompanyProfileResponse> companyList = this.companyRegistrationService.getCompanyList();
		return new ResponseEntity<List<CompanyProfileResponse>>(companyList, HttpStatus.OK);
	}

	@DeleteMapping("/delete/by")
	public ResponseEntity<?> deleteCompany(@RequestParam("companyId") Long companyId) {
		return new ResponseEntity<Map<String, String>>(this.companyRegistrationService.deleteCompany(companyId),
				HttpStatus.OK);
	}
}
