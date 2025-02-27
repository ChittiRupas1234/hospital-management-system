package com.hmanagement.hospital.management.controller;

import com.hmanagement.hospital.management.dto.QualificationDto;
import com.hmanagement.hospital.management.service.QualificationService;
import com.hmanagement.hospital.management.service.implementation.QualificationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController @RequestMapping("/doctor-qualifications")
public class QualificationController {
    private final QualificationService qualificationService;

    public QualificationController(QualificationServiceImpl qualificationService) {
        this.qualificationService = qualificationService;
    }

    @PutMapping("/qualifications/{doctorId}")
    public ResponseEntity<Boolean> addQualification(@PathVariable("doctorId") UUID doctorId, Set<QualificationDto> qualificationDtoSet) {
        return qualificationService.saveQualifications(doctorId, qualificationDtoSet);
    }
}
