package com.jwt.security.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer studentId;

    @NotBlank(message = "Name is mandatory.")
    private String studentName;

    @Column(unique = true)
    @NotBlank(message = "Email is mandatory.")
    private String studentEmail;

    @NotBlank(message = "DOB is mandatory.")
    private String dateOfBirth;

    @NotBlank(message = "Zipcode is mandatory.")
    private Integer zipcode;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;
}







