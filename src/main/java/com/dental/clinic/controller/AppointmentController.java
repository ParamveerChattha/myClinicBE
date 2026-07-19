package com.dental.clinic.controller;


import com.dental.clinic.DTO.AppointmentRequestDTO;
import com.dental.clinic.DTO.AppointmentResponseDTO;
import com.dental.clinic.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("")
  public ResponseEntity<List<AppointmentResponseDTO>> getAppointments(){
            return ResponseEntity.ok(this.appointmentService.getAllAppointments());
    }

    // Handle the multi error scenario, create a marker Interface, and put the new user fields into that interface and then
// handle that in the controller
    @PostMapping("/book")
    public ResponseEntity<AppointmentResponseDTO> bookAppointment(@Valid @RequestBody AppointmentRequestDTO request){
        AppointmentResponseDTO savedAppointment = this.appointmentService.createAppointment(request);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<AppointmentResponseDTO> patchAppointment(@Valid @RequestBody AppointmentResponseDTO request){
        AppointmentResponseDTO updatedAppointment = this.appointmentService.updateAppointment(request);
        return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
    }
}
