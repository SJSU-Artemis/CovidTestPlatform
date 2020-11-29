package com.artemis.covidtestingplatform.controllers;

import com.artemis.covidtestingplatform.models.TestCenter;
import com.artemis.covidtestingplatform.models.TestCenterAvailability;
import com.artemis.covidtestingplatform.services.TestCenterAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/testcenter-availability")
@CrossOrigin
public class TestCenterAvailabilityController {
    @Autowired
    TestCenterAvailabilityService testCenterAvailabilityService;

    @PostMapping
    public TestCenterAvailability save(@RequestBody TestCenterAvailability testCenterAvailability){
        return testCenterAvailabilityService.save(testCenterAvailability);
    }

    @GetMapping("/{id}")
    public TestCenterAvailability get(@PathVariable String id){
        return testCenterAvailabilityService.get(id);
    }
}
