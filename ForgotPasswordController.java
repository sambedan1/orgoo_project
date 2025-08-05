package com.tuespotsolutions.controller;

import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuespotsolutions.models.ForgotPasswordReciveEmailModel;
import com.tuespotsolutions.models.OtpConfirmedRequest;
import com.tuespotsolutions.models.ResetPasswordModel;
import com.tuespotsolutions.service.ForgotPasswordService;

@RestController
@CrossOrigin
@RequestMapping("/forgot")
public class ForgotPasswordController {

	@Autowired
	private ForgotPasswordService forgotPasswordService;

	@PostMapping("/password")
	public ResponseEntity<?> forgotPassword(
		@Valid	@RequestBody ForgotPasswordReciveEmailModel forgotPasswordReciveEmailModel) {
		Map<String, String> forgotPasswordResonse = this.forgotPasswordService
				.forgotPasswordReviceEmail(forgotPasswordReciveEmailModel);
		return new ResponseEntity<Map<String, String>>(forgotPasswordResonse, HttpStatus.OK);
	}
	
	@PostMapping("/password/otp/process")
	public ResponseEntity<?> optProcess(@Valid @RequestBody OtpConfirmedRequest confirmedRequest){
		Map<String, String> receiveOtp = this.forgotPasswordService.receiveOtp(confirmedRequest);
		return new ResponseEntity<Map<String, String>>(receiveOtp, HttpStatus.OK);
	}
	
	@PostMapping("/password/reset")
	public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordModel passwordModel){
		Map<String, String> resetPassword = this.forgotPasswordService.resetPassword(passwordModel);
		return new ResponseEntity<Map<String, String>>(resetPassword, HttpStatus.OK);
	}

}
