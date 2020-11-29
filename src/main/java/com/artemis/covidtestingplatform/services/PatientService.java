package com.artemis.covidtestingplatform.services;

import com.artemis.covidtestingplatform.models.Patient;
import com.artemis.covidtestingplatform.models.TestCenter;
import com.artemis.covidtestingplatform.repositories.PatientRepository;
import com.artemis.covidtestingplatform.repositories.TestCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    TestCenterRepository testCenterRepository;

    public Patient save(Patient patient){
       return patientRepository.save(patient);
    }

    public List<TestCenter> findNearestTestCenter(Double userLongitude, Double userLatitude){
        return testCenterRepository.findAllHospitalsByDistanceFromUser(userLongitude,userLatitude);
    }
}
