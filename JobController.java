package com.tuespotsolutions.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.validation.Valid;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuespotsolutions.models.BotJobResponse;
import com.tuespotsolutions.models.CityResponse;
import com.tuespotsolutions.models.ComposeMailToCompanyModel;
import com.tuespotsolutions.models.Job;
import com.tuespotsolutions.models.JobResponse;
import com.tuespotsolutions.models.JobResponseWithPagination;
import com.tuespotsolutions.models.JobSearchFilters;
import com.tuespotsolutions.models.JobsNamesForSearch;
import com.tuespotsolutions.service.JobService;

@RestController
@CrossOrigin
@RequestMapping("/job")
public class JobController {

	Logger logger = LoggerFactory.getLogger(JobController.class);

	@Autowired
	private JobService jobService;

	@PostMapping("/post")
	public ResponseEntity<?> postJob(@Valid @RequestBody Job job) {
		JobResponse saveJob = this.jobService.saveJob(job);
		return new ResponseEntity<JobResponse>(saveJob, HttpStatus.CREATED);
	}

	@PostMapping("/post/test")
	public ResponseEntity<?> postJobTest(@Valid @RequestBody Job job) {
		JobResponse saveJob = this.jobService.saveJob(job);
		return new ResponseEntity<JobResponse>(saveJob, HttpStatus.CREATED);
	}

	@PutMapping("update")
	public ResponseEntity<?> updateJob(@Valid @RequestBody Job job) {
		JobResponse saveJob = this.jobService.updateJob(job);
		return new ResponseEntity<JobResponse>(saveJob, HttpStatus.OK);
	}

	@GetMapping("/by")
	public ResponseEntity<?> getJobsByCompanyId(@RequestParam("companyId") Long companyId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		JobResponseWithPagination jobsByComany = this.jobService.getJobsByComany(pageNumber, pageSize, companyId);
		return new ResponseEntity<JobResponseWithPagination>(jobsByComany, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getJobs(
			@RequestParam(value = "keyword", defaultValue = "N/A", required = false) String keyword,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		JobResponseWithPagination jobsByComany = this.jobService.getJobs(keyword, pageNumber, pageSize);
		return new ResponseEntity<JobResponseWithPagination>(jobsByComany, HttpStatus.OK);
	}

	@DeleteMapping("/by")
	public ResponseEntity<?> deleteJob(@RequestParam("jobId") Long jobId) {
		this.jobService.deleteJob(jobId);
		@SuppressWarnings("unchecked")
		Map<String, String> delted = new HashedMap();
		delted.put("status", "Job Deleted with id : " + jobId);
		return new ResponseEntity<Map<String, String>>(delted, HttpStatus.OK);
	}

	@GetMapping("/with")
	public ResponseEntity<?> findByJobId(@RequestParam("jobId") Long jobId) {
		JobResponse jobById = this.jobService.getJobById(jobId);
		return new ResponseEntity<JobResponse>(jobById, HttpStatus.OK);
	}

	@GetMapping("/withoutauth")
	public ResponseEntity<?> findByWithoutJobId(@RequestParam("jobId") Long jobId) {
		JobResponse jobById = this.jobService.getJobByIdWithoutToken(jobId);
		return new ResponseEntity<JobResponse>(jobById, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<?> seachJob(@RequestParam(value = "jobTitle", required = false) String jobTitle,
			@RequestParam(value = "jobLocation", required = false) String jobLocation,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		JobResponseWithPagination searchJobByTitleOrLocation = this.jobService.searchJobByTitleOrLocation(jobTitle,
				jobLocation, pageNumber, pageSize);
		return new ResponseEntity<JobResponseWithPagination>(searchJobByTitleOrLocation, HttpStatus.OK);
	}

	@GetMapping("home/page/search")
	public ResponseEntity<?> seachJobFromHomePage(@RequestParam(value = "jobTitle", required = false) String jobTitle,
			@RequestParam(value = "jobLocation", required = false) String jobLocation,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		JobResponseWithPagination searchJobByTitleOrLocation = this.jobService
				.searchJobByTitleOrLocationFromHomePage(jobTitle, jobLocation, pageNumber, pageSize);
		return new ResponseEntity<JobResponseWithPagination>(searchJobByTitleOrLocation, HttpStatus.OK);
	}

	@GetMapping("/filters")
	public ResponseEntity<?> jobFilters() {
		JobSearchFilters jobSearchingFilters = this.jobService.jobSearchingFilters();
		return new ResponseEntity<JobSearchFilters>(jobSearchingFilters, HttpStatus.OK);
	}

	@PostMapping("findJobWithFilters")
	public ResponseEntity<?> findJobWithFilterAttributes(@RequestBody JobSearchFilters jobSearchFilters) {
		Set<JobResponse> findByFilters = this.jobService.findByFilters(jobSearchFilters);
		return new ResponseEntity<Set<JobResponse>>(findByFilters, HttpStatus.OK);
	}

	@GetMapping("/find/according/to")
	public ResponseEntity<?> findJobAccordingToCandidateSkill(@RequestParam("candidateId") Long candidateId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		JobResponseWithPagination findByJobAccordingToCandidateSkillSet = this.jobService
				.findByJobAccordingToCandidateSkillSet(candidateId, pageNumber, pageSize);
		return new ResponseEntity<JobResponseWithPagination>(findByJobAccordingToCandidateSkillSet, HttpStatus.OK);
	}

	@GetMapping("/for/companypanel/joblist/by")
	public ResponseEntity<?> getJobsListForCompanyPanelByCompanyId(@RequestParam("companyId") Long companyId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		JobResponseWithPagination jobsByComany = this.jobService.getJobsByComanyForCompanyPanelJobList(pageNumber,
				pageSize, companyId);
		return new ResponseEntity<JobResponseWithPagination>(jobsByComany, HttpStatus.OK);
	}

	@GetMapping("/all/cities/list")
	public ResponseEntity<?> findAllCities() {
		List<CityResponse> findAllCities = this.jobService.findAllCities();
		return new ResponseEntity<List<CityResponse>>(findAllCities, HttpStatus.OK);
	}

	@GetMapping("/jobs/by")
	public ResponseEntity<?> getJobByBotId(@RequestParam("botJobId") String jobBotId) {
		return new ResponseEntity<List<BotJobResponse>>(this.jobService.getJobByJobBotId(jobBotId), HttpStatus.OK);
	}

	@GetMapping("/titles")
	public ResponseEntity<?> getJobTitleForSearch() {
		List<JobsNamesForSearch> jobTitleForJobSearch = this.jobService.getJobTitleForJobSearch();
		return new ResponseEntity<List<JobsNamesForSearch>>(jobTitleForJobSearch, HttpStatus.OK);
	}

	@GetMapping("/refreshTitles")
	public ResponseEntity<?> refreshJobTitles() {
		jobService.refreshJobTitles();
		return ResponseEntity.ok("Job titles cache refreshed!");
	}

	@PostMapping("/send/mail/to/company")
	public ResponseEntity<?> sendMailToCompanyWithResumeAttactment(
			@Valid @RequestBody ComposeMailToCompanyModel composeMailToCompanyRequest) {
		logger.info("Request Comming from UI : "+composeMailToCompanyRequest.toString());
		return ResponseEntity.ok(this.jobService.sendCandidateResumeMailToCompany(composeMailToCompanyRequest));
	}
	
	@PostMapping("/v2/send/mail/to/company")
	public ResponseEntity<?> sendMailToCompanyWithResumeAttactmentV2(
			@Valid @RequestBody ComposeMailToCompanyModel composeMailToCompanyRequest) {
		logger.info("Request Comming from UI : "+composeMailToCompanyRequest.toString());
		return ResponseEntity.ok(this.jobService.sendCandidateResumeMailToCompanyV2(composeMailToCompanyRequest));
	}
}
