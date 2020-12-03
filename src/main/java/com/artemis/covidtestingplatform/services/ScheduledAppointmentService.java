package com.artemis.covidtestingplatform.services;

import com.artemis.covidtestingplatform.models.*;
import com.artemis.covidtestingplatform.repositories.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class ScheduledAppointmentService {
    @Autowired
    ScheduledAppointmentRepository scheduledAppointmentRepository;
    @Autowired
    AvailableAppointmentRepository availableAppointmentRepository;
    @Autowired
    TestCenterAvailabilityRepository testCenterAvailabilityRepository;
    @Autowired
    AppointmentHistoryRepository appointmentHistoryRepository;
    @Autowired
    PublisherService publisherService;
    @Autowired
    PatientRepository patientRepository;

    @Transactional
    public ScheduledAppointment save(ScheduledAppointment scheduledAppointment, String testCenterAvailabilityId) throws JsonProcessingException {
        scheduledAppointment.setScheduledAppointmentsId(UUID.randomUUID().toString());
        AvailableAppointment availableAppointment = availableAppointmentRepository.findById(scheduledAppointment.getAvailableAppointmentsId()).get();
        int slotBalance = availableAppointment.getAppointmentCount()-1;
        availableAppointment.setAppointmentCount(slotBalance);
        TestCenterAvailability testCenterAvailability = testCenterAvailabilityRepository.findById(testCenterAvailabilityId).get();
        int dayBalance = testCenterAvailability.getAvailableCount()-1;
        scheduledAppointment.setTestCentreAvailabilityId(testCenterAvailabilityId);
        scheduledAppointment.setAppointmentDate(testCenterAvailability.getDay());
        testCenterAvailability.setAvailableCount(dayBalance);
        testCenterAvailabilityRepository.save(testCenterAvailability);
        availableAppointmentRepository.save(availableAppointment);
        scheduledAppointment = scheduledAppointmentRepository.save(scheduledAppointment);
        publisherService.publishScheduledAppointmentEvent(scheduledAppointment);
        return scheduledAppointment;
    }

    public ScheduledAppointment get(String id){
        return scheduledAppointmentRepository.findById(id).get();
    }

    public void delete(String id){
        ScheduledAppointment scheduledAppointment = scheduledAppointmentRepository.findById(id).get();
        AvailableAppointment availableAppointment = availableAppointmentRepository.findById(scheduledAppointment.getAvailableAppointmentsId()).get();
        availableAppointment.setAppointmentCount(availableAppointment.getAppointmentCount()+1);
        availableAppointmentRepository.save(availableAppointment);
        TestCenterAvailability testCenterAvailability = testCenterAvailabilityRepository.findById(scheduledAppointment.getTestCentreAvailabilityId()).get();
        testCenterAvailability.setAvailableCount(testCenterAvailability.getAvailableCount()+1);
        testCenterAvailabilityRepository.save(testCenterAvailability);
        scheduledAppointment.setDeleteFlag(true);
        scheduledAppointmentRepository.save(scheduledAppointment);
    }

    public Iterable<ScheduledAppointment> getScheduledAppointments(String patientId){
        return scheduledAppointmentRepository.findAllByPatientIdAndCheckedInEqualsAndDeleteFlagEquals(patientId,false,false);
    }

    @Transactional
    public ScheduledAppointment save(ScheduledAppointment appointment) {
        AppointmentHistory history = new AppointmentHistory();
        history.setAppointmentHistoryId(UUID.randomUUID().toString());
        history.setAppointmentDate(appointment.getAppointmentDate());
        history.setFollowUpNeeded(false);
        history.setPhysicianId(null);
        history.setTime(appointment.getTime());
        Patient patient = patientRepository.findById(appointment.getPatientId()).get();
        history.setPatient(patient);
        String testCenterId = testCenterAvailabilityRepository.findById(appointment.getTestCentreAvailabilityId()).get().getTestCenter().getTestCentreId();
        history.setTestCenterId(testCenterId);
        appointmentHistoryRepository.save(history);
        return scheduledAppointmentRepository.save(appointment);
    }
}
