package com.hmanagement.hospital.management.repository;


import com.hmanagement.hospital.management.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE p.id = :id AND p.isDeleted = false")
    Optional<Patient> findActivePatientById(@Param("id") Long id);

    @Query("SELECT p FROM Patient p WHERE p.isDeleted = false OR p.isDeleted IS NULL")
    List<Patient> findAllActivePatients();
}