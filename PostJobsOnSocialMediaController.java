package com.tuespotsolutions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuespotsolutions.models.JobResponse;
import com.tuespotsolutions.service.PostJobsOnSocialMediaService;

@RestController
@CrossOrigin("*")
@RequestMapping("/job/list/for/socialmedia")
public class PostJobsOnSocialMediaController {

	@Autowired
	private PostJobsOnSocialMediaService jobsOnSocialMediaService;

	@GetMapping("/")
	public ResponseEntity<?> getJobList(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "500") Integer pageSize) {
		return new ResponseEntity<List<JobResponse>>(this.jobsOnSocialMediaService.getJobs(pageNumber, pageSize),
				HttpStatus.OK);
	}

}
