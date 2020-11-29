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
public class MedicalReport {
    @Id
    private String id;
    private String result;
    private String reportUrl;
    @ManyToOne
    AppointmentHistory appointmentHistory;
    @OneToOne
    private Patient patient;
    @OneToOne
    private Physician physician;
    @OneToOne
    private TestCenter testCenter;
    @Column(updatable = false)
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updateAt;

}
