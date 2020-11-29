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
public class Admin {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Column(updatable = false)
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updateAt;
}
