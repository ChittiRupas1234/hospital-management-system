package com.hmanagement.hospital.management.service;

import com.hmanagement.hospital.management.dto.DoctorDto;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface DoctorService {
    ResponseEntity<DoctorDto> saveProfileDetails(DoctorDto doctorDto);
    ResponseEntity<DoctorDto> getDoctor(UUID id);
}
