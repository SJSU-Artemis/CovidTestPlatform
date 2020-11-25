package com.artemis.covidtestingplatform.services;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Configuration
public class GeoCodeService {

    private String apiKey = "AIzaSyAOqHntmWjaUNkOaw3QIsbZpf4xRZ5kP4Y";

    @Bean
    public GeoApiContext getGeoCodeApiContext() {
        return new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }

    public Double[] getCoordinatesForAddress(String address) throws InterruptedException, ApiException, IOException {
        GeocodingResult[] results =  GeocodingApi.geocode(getGeoCodeApiContext(),
                address).await();
        Double lat = results[0].geometry.location.lat;
        Double longitude = results[0].geometry.location.lng;
        return new Double[]{lat,longitude};
    }

}
