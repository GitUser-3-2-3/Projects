package com.parth.challengeapp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Challenge {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "challenge_month")
    private String month;
    private String description;

    public Challenge() {
    }

    public Challenge(String month, String description) {
        this.month = month;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
