package com.artemis.covidtestingplatform.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class AppointmentHistory {
    @Id
    private String appointmentHistoryId;
    private String physicianId;
    private boolean followUpNeeded;
    private LocalDate appointmentDate;
    private String time;
    private String testCenterId;
    private boolean documentUploaded=false;
    @OneToOne
    private Patient patient;
    @Column(updatable = false)
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updateAt;
}
