package com.artemis.covidtestingplatform.services;

import com.artemis.covidtestingplatform.models.AppointmentHistory;
import com.artemis.covidtestingplatform.repositories.AppointmentHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentHistoryService {
    @Autowired
    AppointmentHistoryRepository appointmentHistoryRepository;

    public Iterable<AppointmentHistory> getAll(String patientId){
        return appointmentHistoryRepository.findAllByPatient_PatientId(patientId);
    }

    public Iterable<AppointmentHistory> findAppointmentsWithNoReport(String testCenterId){
        return appointmentHistoryRepository.findAllByTestCenterIdAndDocumentUploadedEquals(testCenterId,false);
    }
}
