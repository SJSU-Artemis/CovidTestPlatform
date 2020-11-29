package com.artemis.covidtestingplatform.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Physician {
    @Id
    private String physicianId;
    private String firstName;
    private String lastName;
    private String email;
    private String address1;
    private String address2;
    private String zip;
    private String city;
    private String state;
    private String password;
    @OneToOne
    private TestCenter testCenter;
    @Column(updatable = false)
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updateAt;
}
