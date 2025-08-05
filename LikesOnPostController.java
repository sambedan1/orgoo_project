package com.tuespotsolutions.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuespotsolutions.models.LikesOnPostRequest;
import com.tuespotsolutions.service.LikesOnPostService;

@RestController
@CrossOrigin
@RequestMapping("/like")
public class LikesOnPostController {

	@Autowired
	private LikesOnPostService likesOnPostService;

	@PutMapping("/")
	public ResponseEntity<?> like(@RequestBody LikesOnPostRequest like) {
		Map<String, Boolean> liked = this.likesOnPostService.like(like);
		return new ResponseEntity<Map<String, Boolean>>(liked, HttpStatus.OK);
	}

}
