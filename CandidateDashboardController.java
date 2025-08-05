package com.tuespotsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tuespotsolutions.models.CandidateDashboardModel;
import com.tuespotsolutions.service.CandidateDashboardService;
@RestController
@CrossOrigin("*")
@RequestMapping("/candidate/dashboard")
public class CandidateDashboardController {
	@Autowired
	private CandidateDashboardService candidateDashboardService;
	@GetMapping("/count")
	public ResponseEntity<?> getCandidateDashboardData(@RequestParam("candidateTypeId") long candidateTypeId) {
		return new ResponseEntity<CandidateDashboardModel>(
				this.candidateDashboardService.getCandidateDashboardData(candidateTypeId), HttpStatus.OK);
	}

}
