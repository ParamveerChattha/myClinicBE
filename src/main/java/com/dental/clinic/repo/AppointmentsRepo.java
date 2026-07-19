package com.dental.clinic.repo;

import com.dental.clinic.model.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppointmentsRepo extends JpaRepository<Appointments, UUID> {

    Optional<Appointments> findByPatientId(UUID patientId);
}
