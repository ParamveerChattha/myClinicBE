package com.dental.clinic.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientCreateRequest {

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

}
