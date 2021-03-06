package com.artemis.covidtestingplatform.repositories;

import com.artemis.covidtestingplatform.models.TestCenterAvailability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TestCenterAvailabilityRepository extends CrudRepository<TestCenterAvailability,String> {
   TestCenterAvailability findByTestCenter_TestCentreIdAndDay(String id, LocalDate day);
   Iterable<TestCenterAvailability> findAllByDayBetween(LocalDate start, LocalDate end);
}
