package com.artemis.covidtestingplatform.controllers;

import com.artemis.covidtestingplatform.models.Patient;
import com.artemis.covidtestingplatform.models.TestCenter;
import com.artemis.covidtestingplatform.services.GeoCodeService;
import com.artemis.covidtestingplatform.services.PatientService;
import com.artemis.covidtestingplatform.services.ScheduledAppointmentService;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class PatientController {
    @Autowired
    PatientService patientService;
    @Autowired
    GeoCodeService geoCodeService;
    @Autowired
    ScheduledAppointmentService scheduledAppointmentService;

    @PostMapping
    public Patient save(@RequestBody Patient patient){
        return patientService.save(patient);
    }

    @GetMapping("/centers")
    public List<TestCenter> findNearestCenters(@RequestParam String address) throws InterruptedException, ApiException, IOException {
        Double[] loc = geoCodeService.getCoordinatesForAddress(address);
        Double userLongitude = loc[1];
        Double userLatitude = loc[0];
        return patientService.findNearestTestCenter(userLongitude,userLatitude);
    }

    @DeleteMapping("/{scheduledId}")
    public void delete(@PathVariable String scheduledId){
        scheduledAppointmentService.delete(scheduledId);
    }

}
