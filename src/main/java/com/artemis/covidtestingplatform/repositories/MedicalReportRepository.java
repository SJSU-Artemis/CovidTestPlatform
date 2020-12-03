package com.artemis.covidtestingplatform.repositories;

import com.artemis.covidtestingplatform.models.MedicalReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalReportRepository extends CrudRepository <MedicalReport,String>{
}
