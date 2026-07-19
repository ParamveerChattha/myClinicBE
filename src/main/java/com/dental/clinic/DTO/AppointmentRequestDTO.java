package com.dental.clinic.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class AppointmentRequestDTO {

    @NotNull(message = "Appointment date is required")
    private LocalDate date;
    @NotNull(message =  "Appintment status is required")
    private String status;

    private UUID patientId;
    @Valid
    private PatientCreateRequest newPatient;



}
