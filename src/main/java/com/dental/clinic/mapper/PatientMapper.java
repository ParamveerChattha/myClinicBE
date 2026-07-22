package com.dental.clinic.mapper;

import com.dental.clinic.DTO.PatientCreateRequest;
import com.dental.clinic.DTO.PatientResponseDTO;
import com.dental.clinic.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    // Map Request DTO to DB Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "visitCount", constant = "0")
    @Mapping(target = "caseComplexity", constant = "low")
    @Mapping(target = "appointment", ignore = true)
    Patient toEntity(PatientCreateRequest request);

    // Maps DB Entity to response DTO
    PatientResponseDTO toResponseDTO(Patient patient);

    List<PatientResponseDTO> toResponseDTOList(List<Patient> patients);

}
