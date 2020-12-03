package com.artemis.covidtestingplatform.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Patient {
    @Id
    private String patientId;
    private String firstName;
    private String lastName;
    private String email;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date dob;
    private String gender;
    private String address1;
    private String address2;
    private String zip;
    private String city;
    private String state;
    private String insuranceProvider;
    private String phoneNumber;
    @OneToMany
    private List<AppointmentHistory> appointmentHistories;
    @Column(updatable = false)
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updateAt;

}
