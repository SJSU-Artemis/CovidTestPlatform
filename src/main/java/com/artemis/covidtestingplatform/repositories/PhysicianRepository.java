package com.artemis.covidtestingplatform.repositories;

import com.artemis.covidtestingplatform.models.Physician;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicianRepository extends CrudRepository<Physician,String> {
}
