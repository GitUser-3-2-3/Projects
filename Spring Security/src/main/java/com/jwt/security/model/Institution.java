package com.jwt.security.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "_institution")
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer instId;

    @Column(unique = true)
    @NotBlank(message = "Name is mandatory.")
    private String institutionName;

    @NotBlank(message = "Email is mandatory.")
    private String institutionEmail;

    @NotBlank(message = "Zipcode is mandatory.")
    private String zipcode;
    private String instFee;

    @OneToMany(mappedBy = "institution")
    private List<Student> students;
}







