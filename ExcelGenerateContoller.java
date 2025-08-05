package com.tuespotsolutions.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuespotsolutions.entity.Candidate;
import com.tuespotsolutions.models.CandiateListWithPagination;
import com.tuespotsolutions.models.CandidateRegistrationResponse;
import com.tuespotsolutions.models.CompanyProfileResponse;
import com.tuespotsolutions.models.JobResponse;
import com.tuespotsolutions.models.JobResponseForExcel;
import com.tuespotsolutions.repository.CandidateRepository;
import com.tuespotsolutions.service.CandidateRegistrationService;
import com.tuespotsolutions.service.CompanyRegistrationService;
import com.tuespotsolutions.service.JobService;
import com.tuespotsolutions.util.GenerateExcel;

@RestController
@CrossOrigin
@RequestMapping("/excel")
public class ExcelGenerateContoller {
	@Autowired
	CandidateRegistrationService candidateRegistrationService;
	
	@Autowired
	CompanyRegistrationService companyRegistrationService;
	
	@Autowired
	JobService jobService;

	@GetMapping("/candidate")
	public ResponseEntity<byte[]> candidateList() throws IOException {
		List<CandidateRegistrationResponse> candidateDetail = candidateRegistrationService.getCandidateDetail();
		GenerateExcel generateExcel = new GenerateExcel();
		return  generateExcel.generateExcel((List<Object>) (List<?>) candidateDetail, "CandidateSheet");

	}

	@GetMapping("/company")
	public ResponseEntity<byte[]> companyList() throws IOException {
		List<CompanyProfileResponse> companyList = companyRegistrationService.getCompanyList();
		GenerateExcel generateExcel = new GenerateExcel();
		return  generateExcel.generateExcel((List<Object>) (List<?>) companyList, "companySheet");

	}
	@GetMapping("/jobs")
	public ResponseEntity<byte[]> getJobList(@RequestParam(value = "columnName", defaultValue = "N/A", required = false) String columnName,
			@RequestParam(value = "operator", defaultValue = "N/A", required = false) String operator,
			@RequestParam(value = "value", defaultValue = "N/A", required = false) String value) throws IOException {
		 List<JobResponseForExcel> jobDetail = jobService.getJobDetail(columnName,operator,value);
		GenerateExcel generateExcel = new GenerateExcel();
		return  generateExcel.generateExcel((List<Object>) (List<?>) jobDetail, "Job List");

	}
}
