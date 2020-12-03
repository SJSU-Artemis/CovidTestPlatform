package com.artemis.covidtestingplatform.services;

import com.artemis.covidtestingplatform.models.*;
import com.artemis.covidtestingplatform.repositories.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    @Autowired
    AppointmentHistoryRepository appointmentHistoryRepository;
    @Autowired
    PublisherService publisherService;

    public List<String> saveFile(MultipartFile[] files){
        List<String> list = new ArrayList<>();
        for(MultipartFile file: files) {
            String url = amazonClient.uploadFile(file);
            list.add(url);
        }
        return list;
    }

    public MedicalReport save(MedicalReport medicalReport) throws JsonProcessingException {
        Patient patient = patientRepository.findById(medicalReport.getPatient().getPatientId()).get();
        medicalReport.setPatient(patient);
        Physician physician = physicianRepository.findById(medicalReport.getPhysician().getPhysicianId()).get();
        medicalReport.setPhysician(physician);
        TestCenter testCenter = testCenterRepository.findById(medicalReport.getTestCenter().getTestCentreId()).get();
        medicalReport.setTestCenter(testCenter);
        AppointmentHistory appointmentHistory = appointmentHistoryRepository.findById(medicalReport.getAppointmentHistory().getAppointmentHistoryId()).get();
        medicalReport.setAppointmentHistory(appointmentHistory);
        medicalReport.setId(UUID.randomUUID().toString());
        publisherService.publishReportEvent(patient);
        return medicalReportRepository.save(medicalReport);
    }

    public Iterable<MedicalReport> getReportsOnDate(String patientId, LocalDate date){
        return medicalReportRepository.findAllByPatient_PatientIdAndAndAppointmentHistory_AppointmentDate(patientId,date);
    }
}
