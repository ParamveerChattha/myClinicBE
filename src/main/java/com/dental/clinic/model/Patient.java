package com.dental.clinic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name="patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private int phoneNumber;
    private String email;
    private String address;
    private String city;
    private int visitCount =0;
    private String caseComplexity; // ["low", "medium", "high"]
    private boolean hasFutureAppointment;
    private LocalDate appointmentDate;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("patient")
    private List<Appointments> appointment;

    public Patient(UUID id, String city, String email, String address, int phoneNumber, LocalDate dob, String lastName, String firstName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.city = city;
        this.visitCount = 1;
        this.caseComplexity = "low";

    }

    public Patient(){}

    public Patient(String firstName, String lastName, LocalDate dob, int phoneNumber, String email, String address, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.city = city;

    }
}
