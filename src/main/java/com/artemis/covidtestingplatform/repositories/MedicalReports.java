package com.artemis.covidtestingplatform.repositories;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class MedicalReports {
    @Id
    private String id;
    private String result;
    private String reportUrl;

    @OneToOne
    private Users user;

    @OneToOne
    private Physicians physician;

    @OneToOne
    private TestCentres testCentre;

}
