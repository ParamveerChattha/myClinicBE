package com.dental.clinic.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;


@Setter
@Getter
@Entity
@Table(name="appointments")
public class Appointments {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate date;
    private String status; // ["Pending", "upComing", "completed", "canceled"]

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonIgnoreProperties("appointment")
    private Patient patient;

    public Appointments(){}

    public Appointments(LocalDate date, String status,  Patient patient) {
        this.date = date;
        this.status =status;
        this.patient = patient;

    }
}
