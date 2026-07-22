package com.dental.clinic.controller;

import com.dental.clinic.DTO.PatientDTO;
import com.dental.clinic.DTO.PatientResponseDTO;
import com.dental.clinic.service.ConsultationBufferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/checkin")
public class CheckinController {

    @Autowired
    ConsultationBufferService consultationService;

    @GetMapping("")
    public ResponseEntity<HashMap<Integer, PatientResponseDTO>> getCheckin(@RequestParam UUID patientId) {
        HashMap<Integer, PatientResponseDTO> checkedInDetails = this.consultationService.checkInPatientWithTokenNo(patientId);
        return new ResponseEntity<>(checkedInDetails,HttpStatus.OK);
    }


//    checkInPatientWithTokenNo

}
