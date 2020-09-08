package com.artemis.covidtestingplatform.repositories;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admins {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
