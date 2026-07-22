package com.dental.clinic.service;


import com.dental.clinic.DTO.PatientCreateRequest;
import com.dental.clinic.DTO.PatientDTO;
import com.dental.clinic.DTO.PatientResponseDTO;
import com.dental.clinic.mapper.PatientMapper;
import com.dental.clinic.model.Patient;
import com.dental.clinic.repo.PatientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientsRepo repo;

    @Autowired
    private PatientMapper patientMapper;

    public Page<PatientResponseDTO> findAllPatients(Pageable pageable){
//       return repo.findAll();
        return repo.findAll(pageable).map(patientMapper::toResponseDTO);
    }

    public PatientResponseDTO createPatient(PatientCreateRequest request) {

        Patient patient = patientMapper.toEntity(request);
        Patient savedPatient = repo.save(patient);
        return patientMapper.toResponseDTO(savedPatient);
    }

    public List<PatientResponseDTO> findPatient(String phoneNumber, String firstName){
        List<Patient> patientsList;
        if(!firstName.isEmpty() && phoneNumber.isEmpty()){
            patientsList = repo.findByFirstName(firstName);
        }
        else if(!phoneNumber.isEmpty() && firstName.isEmpty() ){
            patientsList = repo.findByPhoneNumber(phoneNumber);
        }
        else {
            patientsList = repo.findByPhoneNumberAndFirstName(phoneNumber, firstName);
        }

                return patientMapper.toResponseDTOList(patientsList);
    }
}
