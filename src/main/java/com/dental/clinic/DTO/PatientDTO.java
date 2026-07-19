package com.dental.clinic.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;


// TODO: REMOVE THIS FILE
@Setter
@Getter
@AllArgsConstructor

public class PatientDTO {

    private UUID id;

    @NotBlank(message= "FirstName is required")
    private String firstName;
    @NotBlank(message= "LastName is required")
    private String lastName;
    @Past(message= "date must be of past")
    private LocalDate dob;
    private int phoneNumber;
    @Email(message= "Please provide a valid email")
    private String email;
    private String address;
    @NotBlank(message ="must provide city or sector")
    private String city;

    public PatientDTO(){

    }

}
