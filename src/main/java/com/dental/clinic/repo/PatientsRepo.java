package com.dental.clinic.repo;


import com.dental.clinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientsRepo extends JpaRepository<Patient, UUID> {

    List<Patient>  findByPhoneNumber(String phoneNumber);

    List<Patient> findByFirstName(String firstName);

    List<Patient> findByPhoneNumberAndFirstName(String phoneNumber, String firstName);
}
