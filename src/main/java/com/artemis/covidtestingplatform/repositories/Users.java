package com.artemis.covidtestingplatform.repositories;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Users {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Date dob;
    private String address1;
    private String address2;
    private String zip;
    private String city;
    private String state;
    private String insuranceProvider;
    private String password;

}
