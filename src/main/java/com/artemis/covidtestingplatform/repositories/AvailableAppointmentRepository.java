package com.artemis.covidtestingplatform.repositories;

import com.artemis.covidtestingplatform.models.AvailableAppointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableAppointmentRepository extends CrudRepository<AvailableAppointment,String> {
}
