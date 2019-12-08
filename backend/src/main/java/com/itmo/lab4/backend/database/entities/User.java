package com.itmo.lab4.backend.database.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "LOGIN", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Collection<PointEntity> points;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<PointEntity> getPoints() {
        return points;
    }

    public void setPoints(Collection<PointEntity> points) {
        this.points = points;
    }
}
