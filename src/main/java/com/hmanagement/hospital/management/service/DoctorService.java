package com.hmanagement.hospital.management.service;

import com.hmanagement.hospital.management.dto.DoctorDto;
import com.hmanagement.hospital.management.dto.PatientDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface DoctorService {
    ResponseEntity<DoctorDto> saveProfileDetails(DoctorDto doctorDto);
    ResponseEntity<DoctorDto> getDoctor(UUID id);
    ResponseEntity<List<PatientDTO>> getAppointments(UUID doctorId);
    ResponseEntity<List<PatientDTO>> getAppointments(UUID doctorId, LocalDate date);
    ResponseEntity<List<PatientDTO>> getAllAppointments(UUID doctorId);
}
