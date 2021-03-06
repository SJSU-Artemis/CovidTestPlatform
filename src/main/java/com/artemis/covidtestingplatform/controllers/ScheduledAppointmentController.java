package com.artemis.covidtestingplatform.controllers;

import com.artemis.covidtestingplatform.models.ScheduledAppointment;
import com.artemis.covidtestingplatform.services.ScheduledAppointmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/{testCenterAvailabilityId}/schedule-appointment")
@CrossOrigin
public class ScheduledAppointmentController {
    @Autowired
    ScheduledAppointmentService scheduledAppointmentService;

    @PostMapping
    public ScheduledAppointment save(@PathVariable String testCenterAvailabilityId, @RequestBody ScheduledAppointment scheduledAppointment) throws JsonProcessingException {
        return scheduledAppointmentService.save(scheduledAppointment, testCenterAvailabilityId);
    }

    @GetMapping("/{id}")
    public ScheduledAppointment get(@PathVariable String id){
        return scheduledAppointmentService.get(id);
    }

    @PutMapping("/{id}")
    public ScheduledAppointment put(@RequestBody ScheduledAppointment appointment){
        return scheduledAppointmentService.save(appointment);
    }
}
