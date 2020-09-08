package com.artemis.covidtestingplatform.repositories;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TestCentres {
    @Id
    private String id;
    private String name;
    private String address1;
    private String address2;
    private String zip;
    private String city;
    private String state;

}
