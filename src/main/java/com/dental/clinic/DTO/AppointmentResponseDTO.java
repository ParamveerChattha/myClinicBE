package com.dental.clinic.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class AppointmentResponseDTO {

    private UUID id;
    private LocalDate date;
    private String status;
    private UUID patientId;
    private String patientName;

}
