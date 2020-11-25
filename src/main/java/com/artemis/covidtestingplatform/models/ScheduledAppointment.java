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
public class ScheduledAppointment {
    @Id
    private String scheduledAppointmentsId;
    private String patientId;
    private String testCentreAvailabilityId;
    private String appointmentDate;
    private boolean checkedIn;
    private boolean deleteFlag=false;
    private String availableAppointmentsId;
    @Column(updatable = false)
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updateAt;
}
