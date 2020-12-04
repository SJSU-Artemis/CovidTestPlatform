package com.artemis.covidtestingplatform.services;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.artemis.covidtestingplatform.models.*;
import com.artemis.covidtestingplatform.repositories.AppointmentHistoryRepository;
import com.artemis.covidtestingplatform.repositories.PatientRepository;
import com.artemis.covidtestingplatform.repositories.TestCenterAvailabilityRepository;
import com.artemis.covidtestingplatform.repositories.TestCenterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.*;
import java.util.Date;

@Component
public class PublisherService {
    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    @Value("${amazon.scheduled.appointment.sns.topic}")
    private String scheduledAppointmentSnsTopicARN;

    @Value("${amazon.reports.notification.sns.topic}")
    private String reportNotificationSnsTopicArn;

    @Value("${amazon.reports.analytics.sns.topic}")
    private String reportAnalyticsSnsTopicArn;

    @Value("${medispot.publisher.key}")
    public String key;

    @Value("${medispot.publisher.key.secret}")
    public String secret;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    TestCenterRepository testCenterRepository;

    @Autowired
    TestCenterAvailabilityRepository testCenterAvailabilityRepository;

    @Autowired
    AppointmentHistoryRepository appointmentHistoryRepository;

    @Autowired
    ObjectMapper objectMapper;

    private AmazonSNS amazonSNS;

    @PostConstruct
    private void init(){
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(key, secret));
        this.amazonSNS = AmazonSNSClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(awsRegion)
                .build();
    }

    public void publishScheduledAppointmentEvent(ScheduledAppointment appointment) throws JsonProcessingException {
        String jsonPayload = objectMapper.writeValueAsString(toScheduledAppointmentDetail(appointment));
        amazonSNS.publish(scheduledAppointmentSnsTopicARN,jsonPayload);
    }
    private ScheduledAppointmentDetail toScheduledAppointmentDetail(ScheduledAppointment scheduledAppointment) {
        Patient patient = patientRepository.findById(scheduledAppointment.getPatientId()).get();
        TestCenterAvailability testCenterAvailability = testCenterAvailabilityRepository.findById(scheduledAppointment.getTestCentreAvailabilityId()).get();
        ScheduledAppointmentDetail scheduledAppointmentDetail = new ScheduledAppointmentDetail();
        scheduledAppointmentDetail.setTestCenterName(testCenterAvailability.getTestCenter().getName());
        String address = getAddress(testCenterAvailability.getTestCenter());
        scheduledAppointmentDetail.setTestCenterAddress(address);
        scheduledAppointmentDetail.setPhoneNumber(patient.getPhoneNumber());
        scheduledAppointmentDetail.setEmail(patient.getEmail());
        scheduledAppointmentDetail.setAppointmentDate(scheduledAppointment.getAppointmentDate());
        scheduledAppointmentDetail.setAppointmentTime(scheduledAppointment.getTime());
        scheduledAppointmentDetail.setName(patient.getFirstName()+" "+patient.getLastName());
        return scheduledAppointmentDetail;
    }

    private String getAddress(TestCenter testCenter) {
        StringBuilder sb = new StringBuilder();
        sb.append(testCenter.getAddress1()).append(",");
        sb.append(testCenter.getAddress2()).append(",");
        sb.append(testCenter.getCity()).append(",");
        sb.append(testCenter.getState()).append(" ").append(testCenter.getZip());
        return sb.toString();
    }

    public void publishReportEvent(Patient patient) throws JsonProcessingException {
        String jsonPayload = objectMapper.writeValueAsString(toPatientDTO(patient));
        amazonSNS.publish(reportNotificationSnsTopicArn,jsonPayload);
    }
    private PatientDTO toPatientDTO(Patient patient){
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setFirstName(patient.getFirstName());
        patientDTO.setLastName(patient.getLastName());
        patientDTO.setAddress1(patient.getAddress1());
        patientDTO.setAddress2(patient.getAddress2());
        patientDTO.setCity(patient.getCity());
        patientDTO.setState(patient.getState());
        patientDTO.setZip(patient.getZip());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setPhoneNumber(patient.getPhoneNumber());
        return patientDTO;
    }

    public void publishReportForAnalyticsEvent(MedicalReport medicalReport) throws JsonProcessingException {
        String jsonPayload = objectMapper.writeValueAsString(toMedicalReportDTO(medicalReport));
        amazonSNS.publish(reportAnalyticsSnsTopicArn,jsonPayload);
    }

    private MedicalReportDTO toMedicalReportDTO(MedicalReport medicalReport){
        MedicalReportDTO medicalReportDTO = new MedicalReportDTO();
        Date input = medicalReport.getPatient().getDob();
        LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();
        Period diff = Period.between(date, now);
        int age = diff.getYears();
        medicalReportDTO.setPatientAge(age);
        medicalReportDTO.setResult(medicalReport.getResult());
        AppointmentHistory appointmentHistory = appointmentHistoryRepository.findById(medicalReport.getAppointmentHistory().getAppointmentHistoryId()).get();
        medicalReportDTO.setAppointmentDate(appointmentHistory.getAppointmentDate());
        medicalReportDTO.setTime(appointmentHistory.getTime());
        Patient patient = patientRepository.findById(medicalReport.getPatient().getPatientId()).get();
        medicalReportDTO.setPatientGender(patient.getGender());
        TestCenter testCenter = testCenterRepository.findById(medicalReport.getTestCenter().getTestCentreId()).get();
        medicalReportDTO.setTestCenterName(testCenter.getName());
        medicalReportDTO.setTestCenterAddress1(testCenter.getAddress1());
        medicalReportDTO.setTestCenterAddress2(testCenter.getAddress2());
        medicalReportDTO.setTestCenterZip(testCenter.getZip());
        medicalReportDTO.setTestCenterCity(testCenter.getCity());
        medicalReportDTO.setTestCenterState(testCenter.getState());
        medicalReportDTO.setId(medicalReport.getId());
        return medicalReportDTO;
    }
}

