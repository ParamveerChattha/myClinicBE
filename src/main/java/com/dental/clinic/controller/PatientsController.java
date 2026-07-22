package com.dental.clinic.controller;


import com.dental.clinic.DTO.PatientCreateRequest;
import com.dental.clinic.DTO.PatientDTO;
import com.dental.clinic.DTO.PatientResponseDTO;
import com.dental.clinic.model.Patient;
import com.dental.clinic.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientsController {

    @Autowired
    PatientService patientService;

    @GetMapping("/all")
    public ResponseEntity<Page<PatientResponseDTO>> getAllPatients(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(this.patientService.findAllPatients(pageable));
    }

    @GetMapping("")
    public ResponseEntity<List<PatientResponseDTO>> getPatientById(@RequestParam String firstName, String phoneNumber, @PageableDefault(page =0, size =10) Pageable pageable){

        return  ResponseEntity.ok(this.patientService.findPatient(phoneNumber, firstName));
    }

    @PostMapping("")
    public ResponseEntity<PatientResponseDTO> savePatient(@RequestBody @Valid PatientCreateRequest request) {
        PatientResponseDTO savedPatient = this.patientService.createPatient(request);

        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }
}