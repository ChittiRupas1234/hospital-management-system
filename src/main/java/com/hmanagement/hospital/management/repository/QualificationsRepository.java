package com.hmanagement.hospital.management.repository;

import com.hmanagement.hospital.management.entity.Qualifications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QualificationsRepository extends JpaRepository<Qualifications, UUID> {
}
