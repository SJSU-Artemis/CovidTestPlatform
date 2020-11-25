package com.artemis.covidtestingplatform.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class TestCenterAvailability {
    @Id
    private String testCentreAvailabilityId;
    private LocalDate day;
    @OneToOne
    TestCenter testCenter;
    @OneToMany
    private List<AvailableAppointment> availableAppointments;
    int availableCount;
    @Column(updatable = false)
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updateAt;
}
