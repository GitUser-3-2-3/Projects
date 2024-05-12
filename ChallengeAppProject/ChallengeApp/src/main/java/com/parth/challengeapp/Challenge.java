package com.parth.challengeapp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Challenge {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "challenge_month")
    @NotBlank(message = "Month is mandatory")
    @Size(min = 3, max = 10, message = "Month must be between 3-10 characters")
    private String month;

    @NotBlank(message = "Description is mandatory")
    @Size(min = 10, max = 50, message = "Description must be between 10-50 characters")
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
