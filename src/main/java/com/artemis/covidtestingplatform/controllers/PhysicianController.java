package com.artemis.covidtestingplatform.controllers;

import com.artemis.covidtestingplatform.models.Physician;
import com.artemis.covidtestingplatform.services.PhysicianService;
import com.artemis.covidtestingplatform.services.UploadAmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medical-rep")
@CrossOrigin
public class PhysicianController {
    @Autowired
    PhysicianService physicianService;

    @PostMapping
    public Physician save(Physician physician){
        return physicianService.save(physician);
    }
}
