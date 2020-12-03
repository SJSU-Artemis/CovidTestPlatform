package com.artemis.covidtestingplatform.controllers;

import com.artemis.covidtestingplatform.models.AppointmentHistory;
import com.artemis.covidtestingplatform.models.Physician;
import com.artemis.covidtestingplatform.services.AppointmentHistoryService;
import com.artemis.covidtestingplatform.services.PhysicianService;
import com.artemis.covidtestingplatform.services.UploadAmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medical-rep")
@CrossOrigin
public class PhysicianController {
    @Autowired
    PhysicianService physicianService;
    @Autowired
    AppointmentHistoryService appointmentHistoryService;

    @PostMapping
    public Physician save(@RequestBody Physician physician){
        return physicianService.save(physician);
    }

    @GetMapping("testCenter/{testCenterId}")
    public Iterable<AppointmentHistory> getAppointmentsWithNoReport(@PathVariable String testCenterId){
        return appointmentHistoryService.findAppointmentsWithNoReport(testCenterId);
    }

    @GetMapping("/{physicianId}")
    public Physician get(@PathVariable String physicianId){
        return physicianService.get(physicianId);
    }
}
