package com.tuespotsolutions.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuespotsolutions.models.CandiateListWithPagination;
import com.tuespotsolutions.models.JobAppliedRequest;
import com.tuespotsolutions.service.JobAppliedService;

@RestController
@CrossOrigin("*")
@RequestMapping("/apply")
public class JobAppliedController {
	
	@Autowired
	private JobAppliedService jobAppliedService;

	@PostMapping("/job")
	public ResponseEntity<?> applyJob(@RequestBody JobAppliedRequest jobAppliedRequest){
		Map<String, Long> jobApply = this.jobAppliedService.jobApply(jobAppliedRequest);
		return new ResponseEntity<Map<String, Long>>(jobApply, HttpStatus.CREATED);
	}
	
	@GetMapping("/getappliedJob")
	public ResponseEntity<?> appliedJob(@RequestParam("companyId") Long companyId,
			@RequestParam("candidateId") Long candidateId,@RequestParam("jobId") Long jobId){
		Long jobApply = this.jobAppliedService.jobApplied(companyId,candidateId,jobId);
		return new ResponseEntity<Long>(jobApply, HttpStatus.OK);
	}
	
	@GetMapping("/getappliedJobByJobID")
	public ResponseEntity<?> appliedCandidateByJob(@RequestParam("companyId") Long companyId,
			@RequestParam("jobId") Long jobId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize){
		CandiateListWithPagination jobApply = this.jobAppliedService.appliedCandidateByJob(pageNumber,pageSize,companyId,jobId);
		return new ResponseEntity<CandiateListWithPagination>(jobApply, HttpStatus.OK);
	} 
	//change
	@GetMapping("/list/with/appliercount")
	public ResponseEntity<?> getAppliedJobListWithApplierCount(
			@RequestParam(value = "keyword", defaultValue = "N/A", required = false) String keyword,
			@RequestParam(value = "companyId", defaultValue = "0", required = false) Long companyId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
		    @RequestParam(value = "startDate", defaultValue = "", required = false) String startDate,
			@RequestParam(value = "endDate", defaultValue = "", required = false) String endDate){
		return ResponseEntity.ok(this.jobAppliedService.appliedJobList(keyword,companyId, pageNumber, pageSize, startDate, endDate));
	}
	
}
