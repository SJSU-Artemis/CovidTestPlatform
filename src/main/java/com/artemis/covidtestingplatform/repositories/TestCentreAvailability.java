package com.artemis.covidtestingplatform.repositories;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class TestCentreAvailability {
    @Id
    private String Id;
    private Date day;

    @OneToMany
    private List<AvailableAppointments> availableAppointments;
}
