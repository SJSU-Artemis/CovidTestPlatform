package com.artemis.covidtestingplatform.services;

import com.artemis.covidtestingplatform.models.TestCenter;
import com.artemis.covidtestingplatform.repositories.TestCenterRepository;
import com.google.maps.errors.ApiException;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class TestCenterService {
    @Autowired
    TestCenterRepository testCenterRepository;

    @Autowired
    GeoCodeService geoCodeService;

    public TestCenter save(TestCenter testCenter) throws InterruptedException, ApiException, IOException {
        String address = getNormalizedAddress(testCenter.getAddress1(), testCenter.getAddress2(), testCenter.getCity(), testCenter.getZip(), testCenter.getState());
        Double loc[] = geoCodeService.getCoordinatesForAddress(address);
        PrecisionModel precisionModel = new PrecisionModel();
        GeometryFactory geometryFactory = new GeometryFactory(precisionModel, 4326);
        Coordinate coordinate = new Coordinate( loc[1], loc[0]);
        CoordinateSequence sequence = new CoordinateArraySequence(new Coordinate[]{coordinate});
        Point newPoint = new Point(sequence, geometryFactory);

        testCenter.setLocation(newPoint);
        testCenter.setTestCentreId(UUID.randomUUID().toString());
        return testCenterRepository.save(testCenter);
    }

    private String getNormalizedAddress(String address1, String address2, String city, String zip, String state){
        StringBuilder sb = new StringBuilder();
        sb.append(address1).append(",");
        if(!StringUtils.isEmpty(address2)){
            sb.append(address2).append(",");
        }
        sb.append(city).append(",").append(state).append(" ").append(zip);
        return sb.toString();
    }

    public TestCenter get(String centerId){
        return testCenterRepository.findById(centerId).get();
    }

    public List<TestCenter> getAll(){
        return testCenterRepository.findAll();
    }
}
