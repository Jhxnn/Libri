package com.Libri.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "fines")
public class Fine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID fineId;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

    private double value;

    private Timestamp time;

    public UUID getFineId() {
        return fineId;
    }

    public void setFineId(UUID fineId) {
        this.fineId = fineId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
