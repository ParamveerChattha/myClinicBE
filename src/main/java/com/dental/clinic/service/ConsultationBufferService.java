package com.dental.clinic.service;

import com.dental.clinic.DTO.PatientDTO;
import com.dental.clinic.DTO.PatientResponseDTO;
import com.dental.clinic.exception.ResourceNotFoundException;
import com.dental.clinic.mapper.PatientMapper;
import com.dental.clinic.model.Patient;
import com.dental.clinic.repo.PatientsRepo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConsultationBufferService {

    //replace the key of PatientResponse of UUID to Integer, and start it from 1 ...-- DONE
    // as a method to reset and flush the queue.
    // if patientuuid (from value) already exists, do not incerement the token.  -- MAYBE DONE ..
    // increment the token (key) and new patient. it solves, checkin, and waiting both .. -- DONE ??


    private HashMap<Integer, PatientResponseDTO> waitingCache = new HashMap<>();

    private PatientsRepo patientRepository;


    private PatientMapper patientMapper;

    public ConsultationBufferService(PatientsRepo patRepo, PatientMapper patientMapper) {
        this.patientRepository = patRepo;
        this.patientMapper = patientMapper;
    }

    public HashMap<Integer, PatientResponseDTO> checkInPatientWithTokenNo(UUID patientId) {
        boolean isPatientInQueue = waitingCache.values().stream()
                .anyMatch((f) -> f.getId().equals(patientId));
        if (isPatientInQueue) {
            throw new IllegalStateException("patient already in queue");
        }
        Patient patientDetail = patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("patient with id:" + patientId + "not found"));

        PatientResponseDTO patientDto = patientMapper.toResponseDTO(patientDetail);
        int count = waitingCache.size();
        waitingCache.put(count + 1, patientDto);

        return waitingCache;
    }


}


