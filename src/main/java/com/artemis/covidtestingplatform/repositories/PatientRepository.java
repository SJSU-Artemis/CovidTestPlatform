package com.artemis.covidtestingplatform.repositories;

import com.artemis.covidtestingplatform.models.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, String> {
}
