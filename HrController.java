package com.tuespotsolutions.controller;
import com.tuespotsolutions.entity.Hr;
import com.tuespotsolutions.models.HrRequest;
import com.tuespotsolutions.models.HrResponse;
import com.tuespotsolutions.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hrs")
public class HrController {

    @Autowired
    private HrService hrService;

    // CREATE
    @PostMapping
    public HrResponse createHr(@RequestBody HrRequest hrRequest) {
        return hrService.saveHr(hrRequest);
    }

    // READ - Get All
    @GetMapping
    public List<HrResponse> getAllHrs() {
        return hrService.getAllHrs();
    }

    // READ - Get by ID
    @GetMapping("/{id}")
    public HrResponse getHrById(@PathVariable Long id) {
        return hrService.getHrById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public HrResponse updateHr(@PathVariable Long id, @RequestBody HrRequest hrRequest) {
        return hrService.updateHr(id, hrRequest);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteHr(@PathVariable Long id) {
        hrService.deleteHr(id);
    }

}

