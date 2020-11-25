package com.artemis.covidtestingplatform.models;

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
public class AppointmentHistory {
    @Id
    private String appointmentHistoryId;
    private String physicianId;
    @OneToMany
    private List<MedicalReport> reportList;
    private boolean followUpNeeded;
    private Date appointmentDate;
    private TimeSlot slot;
    @Column(updatable = false)
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updateAt;
}
