package com.jwt.security.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @NotBlank(message = "Name is mandatory.")
    private String instName;

    @Column(unique = true)
    @NotBlank(message = "Email is mandatory.")
    private String instEmail;

    @NotBlank(message = "City is mandatory.")
    private String instCity;
    private String instFee;

    @OneToMany(mappedBy = "institution")
    @JsonManagedReference
    private List<Student> students;
}







