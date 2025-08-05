package com.tuespotsolutions.controller;

import java.util.List;

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

import com.tuespotsolutions.models.PostCommentResponse;
import com.tuespotsolutions.models.PostCommentsRequest;
import com.tuespotsolutions.service.PostCommentsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/postcomment")
public class PostCommentsController {

	@Autowired
	private PostCommentsService postCommentsService;

	@PostMapping("/")
	public ResponseEntity<?> savePostComment(@RequestBody PostCommentsRequest comments) {
		PostCommentResponse savedMessage = this.postCommentsService.savePostComment(comments);
		return new ResponseEntity<PostCommentResponse>(savedMessage, HttpStatus.CREATED);
	}

	@GetMapping("/by")
	public ResponseEntity<?> getMoreComments(@RequestParam("jobId") Long jobId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		List<PostCommentResponse> moreComments = this.postCommentsService.getMoreComments(jobId, pageNumber, pageSize);
		return new ResponseEntity<List<PostCommentResponse>>(moreComments, HttpStatus.OK);
	}
}
