package com.tuespotsolutions.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.tuespotsolutions.models.ManageInterviwerDetailModel;
import com.tuespotsolutions.models.ManageInterviwerDetailResponse;
import com.tuespotsolutions.service.ManageInterviwerDetailService;

@RestController
@CrossOrigin("*")
@RequestMapping("/interviewer")
public class ManageInterviwerDetailController {

	@Autowired
	private ManageInterviwerDetailService manageInterviwerDetailService;

	@PostMapping("/")
	public ResponseEntity<?> saveInterviwerDtail(@RequestBody ManageInterviwerDetailModel detailModel) {
		return new ResponseEntity<ManageInterviwerDetailResponse>(
				this.manageInterviwerDetailService.saveInterviewerDetail(detailModel), HttpStatus.CREATED);
	}

	@PutMapping("/")
	public ResponseEntity<?> updateInterviwerDtail(@RequestBody ManageInterviwerDetailModel detailModel) {
		return new ResponseEntity<ManageInterviwerDetailResponse>(
				this.manageInterviwerDetailService.saveInterviewerDetail(detailModel), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllInterviwers() {
		return new ResponseEntity<List<ManageInterviwerDetailResponse>>(this.manageInterviwerDetailService.getAll(),
				HttpStatus.OK);
	}
	
	@GetMapping("/by")
	public ResponseEntity<?> getInterviwer(@RequestParam("id") long id) {
		return new ResponseEntity<ManageInterviwerDetailResponse>(this.manageInterviwerDetailService.getById(id),
				HttpStatus.OK);
	}
	
	@DeleteMapping("/by")
	public ResponseEntity<?> deleteInterviwer(@RequestParam("id") long id){
		this.manageInterviwerDetailService.deleteInterviewerDetail(id);
		Map<String, String> resp = new HashMap<String, String>();
		resp.put("message", "Data deleted successfully");
		return new ResponseEntity<Map<String , String>>(resp,HttpStatus.OK);
	}
	
}
