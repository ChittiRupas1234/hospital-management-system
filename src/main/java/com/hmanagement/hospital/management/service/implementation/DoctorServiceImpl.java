package com.hmanagement.hospital.management.service.implementation;

import com.hmanagement.hospital.management.constants.HMSConstants;
import com.hmanagement.hospital.management.converter.DoctorConversions;
import com.hmanagement.hospital.management.dto.DoctorDto;
import com.hmanagement.hospital.management.entity.Doctor;
import com.hmanagement.hospital.management.repository.DoctorRepository;
import com.hmanagement.hospital.management.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorConversions doctorConversion;
    private final DoctorRepository doctorRepository;
    @Autowired
    public DoctorServiceImpl(DoctorConversions doctorConversion, DoctorRepository doctorRepository) {
            this.doctorConversion = doctorConversion;
            this.doctorRepository = doctorRepository;
    }
    public ResponseEntity<DoctorDto> saveProfileDetails(DoctorDto doctorDto) {
        try {
            if(ObjectUtils.isEmpty(doctorDto)) {
               throw new RuntimeException(HMSConstants.DoctorDetailsEmpty);
            }

            Doctor doctor = doctorConversion.toDoctor(doctorDto);
            DoctorDto savedData = doctorConversion.toDoctorDto(doctorRepository.save(doctor));
            return ResponseEntity.ok(savedData);
        } catch(Exception e) {
            DoctorDto errorDto = new DoctorDto();
            errorDto.setStatus(400);
            errorDto.setMessage(e.getMessage());
            return ResponseEntity.status(errorDto.getStatus()).body(errorDto);
        }
    }

    @Override
    public ResponseEntity<DoctorDto> getDoctor(UUID id) {
        try {
            Doctor doctorDetails = doctorRepository.findById(id).orElseThrow(() -> new RuntimeException(HMSConstants.DoctorNotFound));
            DoctorDto doctorDetailsDto = doctorConversion.toDoctorDto(doctorDetails);
            return ResponseEntity.ok(doctorDetailsDto);
        } catch(Exception e) {
            DoctorDto errorDto = new DoctorDto();
            errorDto.setStatus(400);
            errorDto.setMessage(e.getMessage());
            return ResponseEntity.status(errorDto.getStatus()).body(errorDto);
        }
    }
}
