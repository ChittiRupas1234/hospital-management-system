package com.hmanagement.hospital.management.service;

import com.hmanagement.hospital.management.dto.DoctorDto;
import com.hmanagement.hospital.management.dto.PatientDto;
import com.hmanagement.hospital.management.dto.PatientDto;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface DoctorService {
    ResponseEntity<DoctorDto> saveProfileDetails(DoctorDto doctorDto);
    ResponseEntity<DoctorDto> getDoctor(UUID id);
    ResponseEntity<List<PatientDto>> getAppointments(UUID doctorId);
    ResponseEntity<List<PatientDto>> getAppointments(UUID doctorId, LocalDate date);
    ResponseEntity<List<PatientDto>> getAllAppointments(UUID doctorId);
}
