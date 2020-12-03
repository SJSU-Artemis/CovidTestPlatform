package com.artemis.covidtestingplatform.models;

import lombok.Data;
import org.locationtech.jts.geom.Point;
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
public class TestCenter {
    @Id
    private String testCentreId;
    private String name;
    @Column(name = "address1")
    private String address1;
    @Column(name = "address2")
    private String address2;
    private String zip;
    private String city;
    private String state;
    private String phone;
//    @JsonSerialize(using = GeometrySerializer.class)
//    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    @Column(name = "location")
    private Point location;
    @Column(updatable = false)
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updateAt;
}
