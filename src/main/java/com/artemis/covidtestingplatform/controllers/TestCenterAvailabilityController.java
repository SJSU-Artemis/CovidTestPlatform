package com.artemis.covidtestingplatform.controllers;

import com.artemis.covidtestingplatform.models.TestCenter;
import com.artemis.covidtestingplatform.models.TestCenterAvailability;
import com.artemis.covidtestingplatform.services.TestCenterAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
    public TestCenterAvailability get(@PathVariable String id, @RequestParam("localDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day){
        return testCenterAvailabilityService.get(id,day);
    }

    @GetMapping("/nextWeekAvailability")
    public Iterable<TestCenterAvailability> getNext7Days() {
        return testCenterAvailabilityService.getAvailabilityForNext7Days();
    }
}
