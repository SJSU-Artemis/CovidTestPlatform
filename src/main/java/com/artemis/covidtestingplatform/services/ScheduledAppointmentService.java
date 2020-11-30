package com.artemis.covidtestingplatform.services;

import com.artemis.covidtestingplatform.models.AppointmentHistory;
import com.artemis.covidtestingplatform.models.AvailableAppointment;
import com.artemis.covidtestingplatform.models.ScheduledAppointment;
import com.artemis.covidtestingplatform.models.TestCenterAvailability;
import com.artemis.covidtestingplatform.repositories.AppointmentHistoryRepository;
import com.artemis.covidtestingplatform.repositories.AvailableAppointmentRepository;
import com.artemis.covidtestingplatform.repositories.ScheduledAppointmentRepository;
import com.artemis.covidtestingplatform.repositories.TestCenterAvailabilityRepository;
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
        return scheduledAppointmentRepository.findAllByPatientIdAndCheckedInEquals(patientId,false);
    }

    @Transactional
    public ScheduledAppointment update(ScheduledAppointment appointment) {
        AppointmentHistory history = new AppointmentHistory();
        history.setPatientId(appointment.getPatientId());
        history.setAppointmentHistoryId(UUID.randomUUID().toString());
        history.setAppointmentDate(appointment.getAppointmentDate());
        history.setFollowUpNeeded(false);
        history.setPhysicianId(null);
        history.setTime(appointment.getTime());
        String testCenterId = testCenterAvailabilityRepository.findById(appointment.getTestCentreAvailabilityId()).get().getTestCenter().getTestCentreId();
        history.setTestCenterId(testCenterId);
        appointmentHistoryRepository.save(history);
        return scheduledAppointmentRepository.save(appointment);
    }
}
