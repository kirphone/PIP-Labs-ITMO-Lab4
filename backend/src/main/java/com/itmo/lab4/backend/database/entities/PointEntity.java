package com.itmo.lab4.backend.database.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "POINTS", schema = "S224907")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIgnoreProperties("user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointEntity implements Serializable {

    private Double xcoord;
    private Double ycoord;
    private Double radius;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "ISHIT")
    private String isHit;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REQUESTDATE")
    private Date requestDate;

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