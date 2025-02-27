package com.hmanagement.hospital.management.service;

import com.hmanagement.hospital.management.dto.QualificationDto;
import org.springframework.http.ResponseEntity;

import java.util.Set;
import java.util.UUID;

public interface QualificationService {
    ResponseEntity<Boolean> saveQualifications(UUID doctorId, Set<QualificationDto> qualificationDtoSet);
}
