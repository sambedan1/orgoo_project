package com.tuespotsolutions.controller;

import java.util.List;
import java.util.Map;

import com.tuespotsolutions.models.ContactUsModelWithPagination;
import com.tuespotsolutions.models.JobResponseWithPagination;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tuespotsolutions.models.ContactUsModel;
import com.tuespotsolutions.service.ContactUsService;


@RequestMapping("/contactUs")
@CrossOrigin("*")
@RestController
public class ContactUsController {

	@Autowired
	private ContactUsService contactUsService;

	@PostMapping("/saveContactUsData")
	public ResponseEntity<?> saveContactUsData(@Valid @RequestBody ContactUsModel contactUsModel) {
		ContactUsModel saveContactUsData = this.contactUsService.saveContactUsData(contactUsModel);
		return new ResponseEntity<ContactUsModel>(saveContactUsData, HttpStatus.CREATED);
	}

	@PutMapping("/updateContactUsData")
	public ResponseEntity<?> updateContactUsData(@Valid @RequestBody ContactUsModel contactUsModel) {
		ContactUsModel updateContactUsData = this.contactUsService.updateContactUsData(contactUsModel);
		return new ResponseEntity<ContactUsModel>(updateContactUsData, HttpStatus.CREATED);
	}

	@GetMapping("/get/all")
	public ResponseEntity<?> getJobs(
			@RequestParam(value = "keyword", defaultValue = "N/A", required = false) String keyword,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
			ContactUsModelWithPagination contactUsData = this.contactUsService.getContactUsData(keyword, pageNumber, pageSize);
		return new ResponseEntity <ContactUsModelWithPagination>(contactUsData, HttpStatus.OK);
	}

	@GetMapping("/by")
	public ResponseEntity<?> getContactUsDataById(Long id) {
		ContactUsModel contactUsDataById = this.contactUsService.getContactUsDataById(id);
		return new ResponseEntity<ContactUsModel>(contactUsDataById, HttpStatus.OK);

	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteContactUsData(Long id) {
		Map<String, String> deleteContactUsData = this.contactUsService.deleteContactUsData(id);
		return new ResponseEntity<Map<String, String>>(deleteContactUsData, HttpStatus.OK);
	}

}
