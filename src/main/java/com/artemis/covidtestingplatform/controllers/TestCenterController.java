package com.artemis.covidtestingplatform.controllers;

import com.artemis.covidtestingplatform.models.TestCenter;
import com.artemis.covidtestingplatform.services.TestCenterService;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/testcenter")
@CrossOrigin
public class TestCenterController {
    @Autowired
    TestCenterService testCenterService;

    @PostMapping
    public TestCenter save(@RequestBody TestCenter testCenter) throws InterruptedException, ApiException, IOException {
        return testCenterService.save(testCenter);
    }

    @GetMapping
    public List<TestCenter> getAll(){
        return testCenterService.getAll();
    }
}
