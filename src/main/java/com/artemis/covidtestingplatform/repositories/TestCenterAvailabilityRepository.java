package com.artemis.covidtestingplatform.repositories;

import com.artemis.covidtestingplatform.models.TestCenterAvailability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCenterAvailabilityRepository extends CrudRepository<TestCenterAvailability,String> {
   TestCenterAvailability findByTestCenter_TestCentreId(String id);
}
