package com.artemis.covidtestingplatform.controllers;

import com.artemis.covidtestingplatform.models.TestCenter;
import com.artemis.covidtestingplatform.services.TestCenterService;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/testcentre")
public class TestCenterController {
    @Autowired
    TestCenterService testCenterService;

    @PostMapping
    public TestCenter save(@RequestBody TestCenter testCenter) throws InterruptedException, ApiException, IOException {
        return testCenterService.save(testCenter);
    }
}
