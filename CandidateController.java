package com.tuespotsolutions.controller;

import java.util.Map;

import com.tuespotsolutions.models.*;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tuespotsolutions.service.CandidateRegistrationService;
import com.tuespotsolutions.service.OtpConfirmationService;

@RestController
@CrossOrigin("*")
@RequestMapping("/candidate")
public class CandidateController {
	Logger logger = LoggerFactory.getLogger(CandidateController.class);
	@Autowired
	private CandidateRegistrationService candidateRegistrationService;
	@Autowired
	private OtpConfirmationService otpConfirmationService;
	@PostMapping("/registeration")
	public ResponseEntity<?> registerCandidate(@Valid @RequestBody CandidateRequest candidateRequest) {
		logger.info("line no : 40 registerCandidate() method");
		Map<String, String> registerCandidate = this.candidateRegistrationService.registerCandidate(candidateRequest);
		return new ResponseEntity<Map<String, String>>(registerCandidate, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateCandidate(@Valid @RequestBody CandidateRegistrationResponse candidateRequest) {
		logger.info("line no : 50 registerCandidate() method");
		Map<String, String> registerCandidate = this.candidateRegistrationService.updateCandidate(candidateRequest);
		return new ResponseEntity<Map<String, String>>(registerCandidate, HttpStatus.OK);
	}

	@PostMapping("/enterotp")
	public ResponseEntity<?> enterOtp(@RequestBody OtpConfirmedRequest confirmedRequest) {
		logger.info("line no : 47 enterOtp() method");
		CandidateRegistrationResponse otpConfirmationForCandidate = this.otpConfirmationService
				.otpConfirmationForCandidate(confirmedRequest);
		return new ResponseEntity<CandidateRegistrationResponse>(otpConfirmationForCandidate, HttpStatus.ACCEPTED);
	}

	@GetMapping("/by")
	public ResponseEntity<?> getCandidateById(@RequestParam("candidateId") long candidateId) {
		CandidateRegistrationResponse candidateRegistrationResponse = this.candidateRegistrationService
				.getCandidate(candidateId);
		return new ResponseEntity<CandidateRegistrationResponse>(candidateRegistrationResponse, HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<?> getCandidteList(
			@RequestParam(value = "keyword", defaultValue = "N/A", required = false) String keyword,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		CandiateListWithPagination candidateList = this.candidateRegistrationService.getCandidateList(keyword,
				pageNumber, pageSize);
		return new ResponseEntity<CandiateListWithPagination>(candidateList, HttpStatus.OK);
	}

	@PatchMapping("/update/Status")
		public ResponseEntity<?> updateComanyStatus(@Valid @RequestBody CandidateStatusRequest candidateStatusRequest) {
		Map<String, String> updateCompanyProfile = this.candidateRegistrationService
				.updateCandidateStatus(candidateStatusRequest);
		return new ResponseEntity<Map<String, String>>(updateCompanyProfile, HttpStatus.OK);
	}

	@GetMapping("/searchpeople")
	public ResponseEntity<?> seachPeopleWithProfileHandle(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam("profileHandle") String profileHandle) {
		return new ResponseEntity<SearchedPeopleResponseWithPagination>(this.candidateRegistrationService.searchedPeople (profileHandle, pageNumber, pageSize), HttpStatus.OK);
	}

	@GetMapping("/searchpeople/filterList")
	public ResponseEntity<?> searchpeopleWithFilterList(
			@RequestParam (value = "profileHeadline",  required = false) String profileHeadline)
	{
		return new ResponseEntity<searchPeopleFilter>(this.candidateRegistrationService.getSearchFilterPeople(profileHeadline), HttpStatus.OK);
	}

	@GetMapping("/searchpeople/get/detail/by")
	public ResponseEntity<?> searchedpeopleDetailById(@RequestParam("candidateId") long candidateId) {
		SearchPeopleDetail searchedpeopleDetailById = this.candidateRegistrationService
				.searchedpeopleDetailById(candidateId);
		return new ResponseEntity<SearchPeopleDetail>(searchedpeopleDetailById, HttpStatus.OK);
	}

	@DeleteMapping("/delete/by")
	public ResponseEntity<?> deleteCandidate(@RequestParam("userTypeId") Long userTypeId) {
		return new ResponseEntity<Map<String, String>>(this.candidateRegistrationService.deleteCandidate(userTypeId),
				HttpStatus.OK);
	}

	@PostMapping("/searchpeople/filterByData")
	public ResponseEntity<?> getFilteredCandidateList(
			@RequestBody FilterRequestPeople filterRequestPeople,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
	) {
		return new ResponseEntity<SearchedPeopleResponseWithPagination>(candidateRegistrationService.searchCandidateByFilters(filterRequestPeople, pageNumber, pageSize), HttpStatus.OK);
	}
	@GetMapping("/{id}/completion")
	public ResponseEntity<profilePercentage> getCandidateCompletionStatus(@PathVariable Long id) {
		profilePercentage status = candidateRegistrationService.getCandidateCompletionStatus(id);
		return new ResponseEntity<profilePercentage>(status, HttpStatus.OK);
	}

	@PatchMapping("/update/images")
	public ResponseEntity<?> updateImage(@Valid @RequestBody CandiadateImage candiadateImage) {
		logger.info("line no : 50 registerCandidate() method");
		Map<String, String> imageCandidate = this.candidateRegistrationService.updateImage(candiadateImage);
		return new ResponseEntity<Map<String, String>>(imageCandidate, HttpStatus.OK);
	}
}
