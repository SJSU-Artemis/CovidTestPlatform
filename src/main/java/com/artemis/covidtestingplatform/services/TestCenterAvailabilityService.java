package com.artemis.covidtestingplatform.services;

import com.artemis.covidtestingplatform.models.AvailableAppointment;
import com.artemis.covidtestingplatform.models.TestCenter;
import com.artemis.covidtestingplatform.models.TestCenterAvailability;
import com.artemis.covidtestingplatform.models.TimeSlot;
import com.artemis.covidtestingplatform.repositories.TestCenterAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TestCenterAvailabilityService {
    @Autowired
    TestCenterAvailabilityRepository testCenterAvailabilityRepository;
    @Autowired
    TestCenterService testCenterService;
    @Autowired
    AvailableAppointmentService availableAppointmentService;

    @Transactional
    public TestCenterAvailability save(TestCenterAvailability testCenterAvailability){
        testCenterAvailability.setTestCentreAvailabilityId(UUID.randomUUID().toString());
        TestCenter testCenter = testCenterService.get(testCenterAvailability.getTestCenter().getTestCentreId());
        testCenterAvailability.setTestCenter(testCenter);
        int slots = testCenterAvailability.getAvailableCount()/8;
        int rem = testCenterAvailability.getAvailableCount()%8;
        List<AvailableAppointment> availableAppointmentList = new ArrayList<>();
        for(int i=10;i<18;i++){
            AvailableAppointment availableAppointment = new AvailableAppointment();
            availableAppointment.setTime(TimeSlot.valueOf(i));
            availableAppointment.setAppointmentCount(slots);
            availableAppointmentService.save(availableAppointment);
            availableAppointmentList.add(availableAppointment);
        }
        if(rem != 0){
           int count =  availableAppointmentList.get(0).getAppointmentCount()+rem;
           availableAppointmentList.get(0).setAppointmentCount(count);
           availableAppointmentService.save(availableAppointmentList.get(0));
        }
        testCenterAvailability.setAvailableAppointments(availableAppointmentList);
        return testCenterAvailabilityRepository.save(testCenterAvailability);
    }

    public TestCenterAvailability get(String id, LocalDate day){
        return testCenterAvailabilityRepository.findByTestCenter_TestCentreIdAndDay(id,day);
    }
}
