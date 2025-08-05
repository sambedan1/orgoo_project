package com.tuespotsolutions.controller;

import com.tuespotsolutions.models.FilterRequestV2;
import com.tuespotsolutions.models.JobResponseWithPagination;
import com.tuespotsolutions.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v2/filter")
public class FilterControllerV2 {


    @Autowired
    private JobService jobService;


    @PostMapping("/jobs")
    public ResponseEntity<?> getFilteredJobList(
            @RequestBody FilterRequestV2 filterRequestV2,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ) {
        return new ResponseEntity<JobResponseWithPagination>(jobService.searchJobByFiltersV2(filterRequestV2, pageNumber, pageSize), HttpStatus.OK);
    }
    
    @PostMapping("loggedin/candidate/jobs")
    public ResponseEntity<?> getFilteredJobListForLoggedInCandidate(
            @RequestBody FilterRequestV2 filterRequestV2,
            @RequestParam(value = "candidateId") Long candidateId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
    ) {
        return new ResponseEntity<JobResponseWithPagination>(jobService.searchJobByFiltersForLoggedInCandidateV2(filterRequestV2, candidateId ,pageNumber, pageSize), HttpStatus.OK);
    }


}
