package com.artemis.covidtestingplatform.repositories;

import com.artemis.covidtestingplatform.models.MedicalReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MedicalReportRepository extends CrudRepository <MedicalReport,String>{

    Iterable<MedicalReport> findAllByPatient_PatientIdAndAndAppointmentHistory_AppointmentDate(String patientId, LocalDate date);
}
