package com.artemis.covidtestingplatform.controllers;

import com.artemis.covidtestingplatform.models.TimeSlot;
import com.artemis.covidtestingplatform.services.GeoCodeService;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("api/demo")
@RestController
public class DemoController {

    @Autowired
    private GeoCodeService geoCodeService;

    @GetMapping("/{city}")
    public Double[] get(@PathVariable String city) throws InterruptedException, ApiException, IOException {
        return geoCodeService.getCoordinatesForAddress(city);
    }

    @GetMapping("/enum")
    public TimeSlot getEnum(){
        return TimeSlot.valueOf(2);
    }

    @GetMapping("/enum/num")
    public int getNum(){
        TimeSlot timeSlot = TimeSlot.EIGHTEEN;
        return timeSlot.getValue();
    }
}
