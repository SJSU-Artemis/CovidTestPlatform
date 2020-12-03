package com.artemis.covidtestingplatform.services;

import com.artemis.covidtestingplatform.models.AvailableAppointment;
import com.artemis.covidtestingplatform.repositories.AvailableAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AvailableAppointmentService {
    @Autowired
    AvailableAppointmentRepository availableAppointmentRepository;

    public AvailableAppointment save(AvailableAppointment availableAppointment){
        availableAppointment.setAvailableAppointmentsId(UUID.randomUUID().toString());
        return availableAppointmentRepository.save(availableAppointment);
    }


}
