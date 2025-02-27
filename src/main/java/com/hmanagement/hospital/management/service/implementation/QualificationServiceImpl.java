package com.hmanagement.hospital.management.service.implementation;

import com.hmanagement.hospital.management.constants.HMSConstants;
import com.hmanagement.hospital.management.converter.QualificationConverstions;
import com.hmanagement.hospital.management.dto.QualificationDto;
import com.hmanagement.hospital.management.entity.Doctor;
import com.hmanagement.hospital.management.entity.Qualifications;
import com.hmanagement.hospital.management.repository.DoctorRepository;
import com.hmanagement.hospital.management.repository.QualificationsRepository;
import com.hmanagement.hospital.management.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class QualificationServiceImpl implements QualificationService {
    private final QualificationConverstions qualificationConverstions;
    private final DoctorRepository doctorRepository;
    private final QualificationsRepository qualificationsRepository;

    @Autowired
    public QualificationServiceImpl(QualificationConverstions qualificationConverstions, DoctorRepository doctorRepository, QualificationsRepository qualificationsRepository) {
        this.qualificationsRepository = qualificationsRepository;
        this.doctorRepository = doctorRepository;
        this.qualificationConverstions = qualificationConverstions;
    }

    public ResponseEntity<Boolean> saveQualifications(UUID doctorId, Set<QualificationDto> qualificationDtoSet) {
        try {
            Set<Qualifications> qualifications = qualificationConverstions.toQualificationSet(qualificationDtoSet);
            Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException(HMSConstants.DoctorNotFound));
            Set<Qualifications> availableQualifications = doctor.getQualifications();
            availableQualifications.addAll(qualifications);
            doctorRepository.save(doctor);
            return ResponseEntity.ok(Boolean.TRUE);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Boolean.FALSE);
        }
    }
}
