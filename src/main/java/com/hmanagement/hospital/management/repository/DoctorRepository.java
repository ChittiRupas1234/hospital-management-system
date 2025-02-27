package com.hmanagement.hospital.management.repository;

import com.hmanagement.hospital.management.entity.Doctor;
import com.hmanagement.hospital.management.entity.Qualifications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
}
