package com.tuespotsolutions.controller;

import com.tuespotsolutions.models.PhonePeRequest;
import com.tuespotsolutions.models.PhonePeResponse;
import com.tuespotsolutions.service.PhonePeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/")
public class PhonePayControl  {


    @Autowired
    private PhonePeService myService;

    @PostMapping("/call")
    public PhonePeResponse callApi(@RequestBody PhonePeRequest myRequest) {
        return myService.callProcessApi(myRequest);
    }

}
