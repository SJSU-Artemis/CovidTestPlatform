package com.artemis.covidtestingplatform.services;

import com.artemis.covidtestingplatform.models.Physician;
import com.artemis.covidtestingplatform.models.TestCenter;
import com.artemis.covidtestingplatform.repositories.PhysicianRepository;
import com.artemis.covidtestingplatform.repositories.TestCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PhysicianService {
    @Autowired
    PhysicianRepository physicianRepository;
    @Autowired
    TestCenterRepository testCenterRepository;

    public Physician save(Physician physician){
        TestCenter testCenter = testCenterRepository.findById(physician.getTestCenter().getTestCentreId()).get();
        physician.setTestCenter(testCenter);
        physician.setPhysicianId(UUID.randomUUID().toString());
        return physicianRepository.save(physician);
    }
}
