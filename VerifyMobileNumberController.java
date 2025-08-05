package com.tuespotsolutions.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuespotsolutions.customexception.InvalidMobileNumberException;
import com.tuespotsolutions.customexception.WrongOtpException;
import com.tuespotsolutions.service.VerifyMobileNumberService;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/numberverification")
public class VerifyMobileNumberController {

	
	@Autowired
    private VerifyMobileNumberService verifyMobileNumberService;

    @PostMapping("/sendotp")
    public ResponseEntity<Map<String, String>> sendOtp(
    		@RequestParam("mobileNumber") String mobileNumber) {
        try {
            Map<String, String> response = verifyMobileNumberService.sendOtpToMobileNumber(mobileNumber);
            return ResponseEntity.ok(response);
        } catch (InvalidMobileNumberException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to send OTP");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/verifyotp")
    public ResponseEntity<Map<String, String>> verifyOtp(
    		@RequestParam("otp") String otp,
    		@RequestParam("mobileNumber") String mobileNumber
    		) {
            Map<String, String> response = verifyMobileNumberService.verifyOtp(otp,mobileNumber);
            return ResponseEntity.ok(response);
        
    }
	
	
}
