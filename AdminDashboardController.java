package com.tuespotsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuespotsolutions.models.AdminDashboardCountModel;
import com.tuespotsolutions.service.AdminDashboardCountService;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminDashboardController {

	@Autowired
	private AdminDashboardCountService adminDashboardCountService;

	@GetMapping("/data")
	public ResponseEntity<?> getAdminDashboardData() {
		return new ResponseEntity<AdminDashboardCountModel>(this.adminDashboardCountService.getAdminDashboardCount(),
				HttpStatus.OK);
	}

}
