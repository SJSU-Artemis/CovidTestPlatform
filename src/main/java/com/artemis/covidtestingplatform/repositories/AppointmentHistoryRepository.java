package com.artemis.covidtestingplatform.repositories;

import com.artemis.covidtestingplatform.models.AppointmentHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentHistoryRepository extends CrudRepository<AppointmentHistory,String> {
    Iterable<AppointmentHistory> findAllByPatientId(String patientId);
}
