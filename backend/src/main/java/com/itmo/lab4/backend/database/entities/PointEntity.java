package com.itmo.lab4.backend.database.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "POINTS", schema = "S224907")
public class PointEntity implements Serializable {

    private Double xcoord;
    private Double ycoord;
    private Double radius;

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    private String isHit;

    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getXcoord() {
        return xcoord;
    }

    public void setXcoord(Double xcoord) {
        this.xcoord = xcoord;
    }

    public Double getYcoord() {
        return ycoord;
    }

    public void setYcoord(Double ycoord) {
        this.ycoord = ycoord;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    //@Basic
    //@Column(name = "USER_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIsHit() {
        return isHit;
    }

    public void setIsHit(String isHit) {
        this.isHit = isHit;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointEntity that = (PointEntity) o;
        return Objects.equals(xcoord, that.xcoord) &&
                Objects.equals(ycoord, that.ycoord) &&
                Objects.equals(radius, that.radius);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xcoord, ycoord, radius);
    }
}