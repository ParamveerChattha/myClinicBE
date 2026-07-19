package com.dental.clinic.mapper;

import com.dental.clinic.DTO.AppointmentResponseDTO;
import com.dental.clinic.model.Appointments;
import com.dental.clinic.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @Mapping(target = "patientId", source = "patient.id")
    @Mapping(target = "patientName", source = "patient", qualifiedByName = "mapPatientName")
    AppointmentResponseDTO toResponseDTO(Appointments appointment);

    @Named("mapPatientName")
    default String mapPatientName(Patient patient){
        if(patient == null) return null;

        String firstName = patient.getFirstName() !=null? patient.getFirstName(): "";
        String lastNameName = patient.getLastName() !=null? patient.getLastName(): "";

        return (firstName + " "+ lastNameName).trim();
    }

}
