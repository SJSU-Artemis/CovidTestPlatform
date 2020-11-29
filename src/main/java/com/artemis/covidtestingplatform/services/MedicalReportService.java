package com.artemis.covidtestingplatform.services;

import com.artemis.covidtestingplatform.models.MedicalReport;
import com.artemis.covidtestingplatform.models.Patient;
import com.artemis.covidtestingplatform.models.Physician;
import com.artemis.covidtestingplatform.models.TestCenter;
import com.artemis.covidtestingplatform.repositories.MedicalReportRepository;
import com.artemis.covidtestingplatform.repositories.PatientRepository;
import com.artemis.covidtestingplatform.repositories.PhysicianRepository;
import com.artemis.covidtestingplatform.repositories.TestCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class MedicalReportService {
    @Autowired
    UploadAmazonClient amazonClient;
    @Autowired
    MedicalReportRepository medicalReportRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PhysicianRepository physicianRepository;
    @Autowired
    TestCenterRepository testCenterRepository;

    public String saveFile(MultipartFile file){
        return amazonClient.uploadFile(file);
    }

    public MedicalReport save(MedicalReport medicalReport){
        Patient patient = patientRepository.findById(medicalReport.getPatient().getPatientId()).get();
        medicalReport.setPatient(patient);
        Physician physician = physicianRepository.findById(medicalReport.getPhysician().getPhysicianId()).get();
        medicalReport.setPhysician(physician);
        TestCenter testCenter = testCenterRepository.findById(medicalReport.getTestCenter().getTestCentreId()).get();
        medicalReport.setTestCenter(testCenter);
        medicalReport.setId(UUID.randomUUID().toString());
        return medicalReportRepository.save(medicalReport);
    }
}
