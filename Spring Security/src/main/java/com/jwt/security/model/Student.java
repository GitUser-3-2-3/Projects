package com.jwt.security.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer stdntId;

    @NotBlank(message = "Name is mandatory.")
    private String stdntName;

    @Column(unique = true)
    @NotBlank(message = "Email is mandatory.")
    private String stdntEmail;

    @NotBlank(message = "DOB is mandatory.")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Residence is mandatory.")
    private String stdntResidence;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;
}







