package com.dental.clinic.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class PatientResponseDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private int phoneNumber;
    private String email;
    private String address;
    private String city;
    private int visitCount;
    private String caseComplexity;
    private boolean hasFutureAppointment;
    private LocalDate appointmentDate;


}
