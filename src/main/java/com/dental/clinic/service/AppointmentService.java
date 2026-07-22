package com.dental.clinic.service;

import com.dental.clinic.DTO.AppointmentRequestDTO;
import com.dental.clinic.DTO.AppointmentResponseDTO;
import com.dental.clinic.DTO.PatientCreateRequest;
import com.dental.clinic.exception.ResourceNotFoundException;
import com.dental.clinic.mapper.AppointmentMapper;
import com.dental.clinic.model.Patient;
import com.dental.clinic.repo.AppointmentsRepo;
import com.dental.clinic.model.Appointments;
import com.dental.clinic.repo.PatientsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private AppointmentsRepo appointmentsRepo;


    private PatientsRepo patientRepo;

    @Autowired
    private AppointmentMapper appointmentMappper;

    public AppointmentService(AppointmentsRepo appointRepo, PatientsRepo patRepo){
        this.appointmentsRepo = appointRepo;
        this.patientRepo = patRepo;
    }



    @Transactional
    public AppointmentResponseDTO updateAppointment(AppointmentResponseDTO dto){

        if(dto.getPatientId() == null){
            throw new IllegalArgumentException("Patient ID must be provided to update an appointment");
        }
        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(()-> new ResourceNotFoundException("Patient ID not found"));
        Appointments appointment = appointmentsRepo.findByPatientId(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("No appointment found"));
        if(dto.getDate() != null){
            appointment.setDate(dto.getDate());
            patient.setAppointmentDate(dto.getDate());
        }
        if(dto.getStatus() !=null){
            appointment.setStatus(dto.getStatus());
        }
        Appointments saved = appointmentsRepo.save(appointment);
        return appointmentMappper.toResponseDTO(saved);
    }

    @Transactional
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO dto){
        Patient patient;

        if(dto.getPatientId() != null){
            patient = patientRepo.findById(dto.getPatientId())
                    .orElseThrow(()-> new ResourceNotFoundException("patient not found"));
            boolean hasExistingAppointment = appointmentsRepo.findByPatientId(dto.getPatientId()).isPresent();
            if (hasExistingAppointment) {
                throw new IllegalStateException("Appointment already exists, try updating existing appointments");
            }
            patient.setVisitCount(patient.getVisitCount()+1);
            patient.setHasFutureAppointment(true);
            patient.setAppointmentDate(dto.getDate());
            patientRepo.save(patient);
        }else if(dto.getNewPatient() != null) {
            PatientCreateRequest newPatientDto = dto.getNewPatient();

            patient = new Patient(newPatientDto.getFirstName(), newPatientDto.getLastName(), newPatientDto.getDob(), newPatientDto.getPhoneNumber(), newPatientDto.getEmail(), newPatientDto.getAddress(), newPatientDto.getCity());
            patient.setHasFutureAppointment(true);
            patient.setAppointmentDate(dto.getDate());
            patient = patientRepo.save(patient);
        }
        else {
            throw new IllegalArgumentException("Patient ID or new Patient details must be provided");
        }
        Appointments  appointment = new Appointments(dto.getDate(), dto.getStatus(), patient);
        Appointments saved = appointmentsRepo.save(appointment);
        return appointmentMappper.toResponseDTO(saved);
    }

    public List<AppointmentResponseDTO> getAllAppointments() {
        return appointmentsRepo.findAll().stream()
                .map(appointmentMappper :: toResponseDTO)
                .collect(Collectors.toList());

    }

}
