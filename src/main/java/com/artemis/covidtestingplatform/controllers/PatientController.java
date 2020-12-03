package com.artemis.covidtestingplatform.controllers;

import com.artemis.covidtestingplatform.models.*;
import com.artemis.covidtestingplatform.services.*;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class PatientController {
    @Autowired
    PatientService patientService;
    @Autowired
    GeoCodeService geoCodeService;
    @Autowired
    ScheduledAppointmentService scheduledAppointmentService;
    @Autowired
    AppointmentHistoryService appointmentHistoryService;
    @Autowired
    MedicalReportService medicalReportService;

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

    @GetMapping("/{id}/scheduled-appointments")
    public Iterable<ScheduledAppointment> getScheduledAppointments(@PathVariable String id){
        return scheduledAppointmentService.getScheduledAppointments(id);
    }

    @GetMapping("/{id}/appointment-history")
    public Iterable<AppointmentHistory> getHistory(@PathVariable String id){
        return appointmentHistoryService.getAll(id);
    }

    @GetMapping("/{patientId}")
    public Patient get(@PathVariable String patientId){
        return patientService.get(patientId);
    }

    @GetMapping("/{patientId}/report")
    public Iterable<MedicalReport> getReportsOnDate(@PathVariable String patientId, @RequestParam LocalDate date){
        return medicalReportService.getReportsOnDate(patientId,date);
    }
}
