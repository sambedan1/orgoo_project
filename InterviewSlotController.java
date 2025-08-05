package com.tuespotsolutions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuespotsolutions.models.InterviewSlotResponse;
import com.tuespotsolutions.service.InterviewSlotService;

@RestController
@CrossOrigin("*")
@RequestMapping("/interviewslot")
public class InterviewSlotController {

	@Autowired
	InterviewSlotService interviewSlotService;

	@GetMapping("/")
	public ResponseEntity<?> getInterviewSlotList() {
		List<InterviewSlotResponse> interviewSlots = this.interviewSlotService.getInterviewSlots();
		return new ResponseEntity<List<InterviewSlotResponse>>(interviewSlots, HttpStatus.OK);
	}

}
