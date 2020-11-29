package com.artemis.covidtestingplatform.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class AppointmentHistory {
    @Id
    private String appointmentHistoryId;
    private String physicianId;
    private boolean followUpNeeded;
    private Date appointmentDate;
    private TimeSlot slot;
    private String testCenterId;
    @Column(updatable = false)
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updateAt;
}
