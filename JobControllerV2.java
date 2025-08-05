package com.tuespotsolutions.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuespotsolutions.models.JobResponseWithPagination;
import com.tuespotsolutions.service.JobService;

@RestController
@CrossOrigin
@RequestMapping("api/v2/job")
public class JobControllerV2 {

	Logger logger = LoggerFactory.getLogger(JobControllerV2.class);

	@Autowired
	private JobService jobService;

	@GetMapping("home/page/search")
	public ResponseEntity<?> seachJobFromHomePage(@RequestParam(value = "jobTitle", required = false) String jobTitle,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		JobResponseWithPagination searchJobByTitleOrLocation = this.jobService
				.searchJobByTitleOrLocationFromHomePageV2(jobTitle, pageNumber, pageSize);
		return new ResponseEntity<JobResponseWithPagination>(searchJobByTitleOrLocation, HttpStatus.OK);
	}
	
	@GetMapping("loggedin/candidate/search")
	public ResponseEntity<?> seachJobAfterLoginCandidate(
			@RequestParam(value = "jobTitle", required = false) String jobTitle,
			@RequestParam(value = "candidateId") Long candidateId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		JobResponseWithPagination searchJobByTitleOrLocation = this.jobService
				.searchJobByTitleOrLocationForLoggedInCandidateV2(jobTitle,candidateId ,pageNumber, pageSize);
		return new ResponseEntity<JobResponseWithPagination>(searchJobByTitleOrLocation, HttpStatus.OK);
	}

}
