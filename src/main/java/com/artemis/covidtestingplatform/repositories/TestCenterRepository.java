package com.artemis.covidtestingplatform.repositories;

import com.artemis.covidtestingplatform.models.TestCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCenterRepository extends JpaRepository<TestCenter, String> {
    @Query(value = "SELECT * "
            + "FROM test_center tc "
            + "ORDER BY tc.location <-> ST_SetSRID(ST_Point(:userLongitude,:userLatitude),4326) "
            + "LIMIT 5"
            , nativeQuery = true)
    List<TestCenter> findAllHospitalsByDistanceFromUser(@Param("userLongitude") Double userLongitude, @Param("userLatitude")  Double userLatitude);
}
//    WHERE ST_DWithin(cast(seller_geolocation_ms.location as geography),ST_SetSRID(ST_Point(?2, ?1),4326), 10000);"
//
//        "SELECT nhf.id, nhf.name, nhf.geom, ST_Distance(nhf.geom,ST_SetSRID(ST_Point(:userLongitude,:userLatitude),4326)) AS distance "
//        + "FROM nairobi_Health_facilities nhf "
//        + "ORDER BY nhf.geom  <-> ST_SetSRID(ST_Point(:userLongitude,:userLatitude),4326)