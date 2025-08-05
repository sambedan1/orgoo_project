package com.tuespotsolutions.controller;

import java.util.List;

import com.tuespotsolutions.models.*;
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

import com.tuespotsolutions.service.BotDashboardService;

@RestController
@CrossOrigin("*")
@RequestMapping("/botdashboard")
public class BotDashboardController {
	@Autowired
	private BotDashboardService botDashboardService;
	@GetMapping("/job/count")
	public ResponseEntity<?> getJobCountByBot() {
		BotDashboardJobCount botJobCount = this.botDashboardService.getBotJobCount();
		return new ResponseEntity<BotDashboardJobCount>(botJobCount, HttpStatus.OK);
	}
	@GetMapping("/job/list")
	public ResponseEntity<JobResponseWithDuplicateCountWithPagingnation> getJobList(
			@RequestParam(value = "keyword", defaultValue = "N/A", required = false) String keyword,
			@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "botName", defaultValue = "All") String botName,
			@RequestParam(value = "isToday") boolean isToday) {

		JobResponseWithDuplicateCountWithPagingnation jobList = this.botDashboardService.getJobList(keyword,pageNumber,pageSize,botName,isToday);
		return new ResponseEntity<>(jobList, HttpStatus.OK);
	}
	@GetMapping("/duplicate/job/history")
	public ResponseEntity<?> getJobHistory() {
		List<DuplicateJobHistory> jobHistroy = this.botDashboardService.getJobHistroy();
		return new ResponseEntity<>(jobHistroy, HttpStatus.OK);
	}
	@PostMapping("/job/list/by")
	public ResponseEntity<JobResponseWithDuplicateCountWithPagingnation> findByBotJobId(
			@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestBody JobLinkModel jobLink) {

		JobResponseWithDuplicateCountWithPagingnation jobList = this.botDashboardService.findByBotJobId(pageNumber,
				pageSize, jobLink);
		return new ResponseEntity<>(jobList, HttpStatus.OK);
	}
}
