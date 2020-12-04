package com.artemis.covidtestingplatform.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicalReportDTO {
    private String id;
    private String result;
    private LocalDate appointmentDate;
    private String time;
    private String patientGender;
    private int patientAge;
    private String testCenterName;
    private String testCenterAddress1;
    private String testCenterAddress2;
    private String testCenterZip;
    private String testCenterCity;
    private String testCenterState;
}
