package com.tuespotsolutions.controller;

import com.tuespotsolutions.customexception.ResourceNotFoundException;
import com.tuespotsolutions.entity.PhonePePaymentResponseEntity;
import com.tuespotsolutions.models.*;
import com.tuespotsolutions.models.phonepe.PaymentStatusResponse;
import com.tuespotsolutions.models.phonepe.PhonePeResponseModel;
import com.tuespotsolutions.service.PhonePeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/payment")
public class PhonePayController {

    private static final Logger logger = LoggerFactory.getLogger(PhonePayController.class);

    @Value("${phonepe.checkStatusGenUrl}")
    public String checkStatusGenUrl;

    @Value("${phonepe.checkStatusUpiUrl}")
    public String checkStatusUpiUrl;

    @Value("${phonepe.checkStatusCardUrl}")
    public String checkStatusCardUrl;

    @Value("${phonepe.checkStatusNetUrl}")
    public String checkStatusNetUrl;

    @Value("${phonepe.getResponseFromUIUrl}")
    public String getResponseForUIUrl;

    @Autowired
    private PhonePeService phonePeService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/phonepe")
    public PhonePeResponse callApi(@RequestBody PhonePeRequest phonePeRequest) {
        logger.info("Request Received for PhonePE " + phonePeRequest.toString());
        return phonePeService.callProcessApi(phonePeRequest);
    }

    @PostMapping("/response")
    public String getResponseFromPhonePe(@RequestBody PhonePeResponseModel model) {
        return phonePeService.getResponseFromPhonePe(model);
    }

    @PostMapping("/checkStatus")
    public PaymentStatusResponse getPaymentStatusGen(@RequestBody PhonePeRequest phonePeRequest) {
        return restTemplate.postForObject(checkStatusGenUrl, phonePeRequest, PaymentStatusResponse.class);
    }

    @PostMapping("/checkStatus/upi")
    public PaymentStatusResponse getPaymentStatusUpi(@RequestBody PhonePeRequest phonePeRequest) {
        return restTemplate.postForObject(checkStatusUpiUrl, phonePeRequest, PaymentStatusResponse.class);
    }

    @PostMapping("/checkStatus/card")
    public PaymentStatusResponse getPaymentStatusCard(@RequestBody PhonePeRequest phonePeRequest) {
        return restTemplate.postForObject(checkStatusCardUrl, phonePeRequest, PaymentStatusResponse.class);
    }

    @PostMapping("/checkStatus/net")
    public PaymentStatusResponse getPaymentStatusNet(@RequestBody PhonePeRequest phonePeRequest) {
        return restTemplate.postForObject(checkStatusNetUrl, phonePeRequest, PaymentStatusResponse.class);
    }

    @GetMapping("/status/{merchantTransactionId}")
    public String getResponseForUI(@PathVariable("merchantTransactionId") String merchantTransactionId) {
        try {
            return restTemplate.getForObject(getResponseForUIUrl + merchantTransactionId, String.class);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/paymentDetails")
    public ResponseEntity<?> getAllPaymentDetails(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize)
    {
        PaymentStatusResponseWithPagination findAllPaymentDetails = this.phonePeService.getAllPaymentDetails(pageNumber, pageSize);
       return ResponseEntity.status(HttpStatus.OK).body(findAllPaymentDetails);
    }
}
