package com.artemis.covidtestingplatform.repositories;

import com.artemis.covidtestingplatform.models.ScheduledAppointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledAppointmentRepository extends CrudRepository<ScheduledAppointment,String> {
    Iterable<ScheduledAppointment> findAllByPatientIdAndCheckedInEqualsAndDeleteFlagEquals(String patientId, boolean checkedIn,boolean deleteFlag);
}
