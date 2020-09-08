package com.artemis.covidtestingplatform.repositories;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AvailableAppointments {
    @Id
    private String id;
    private String time;
    private int appointmentCount;
}
