package com.hmanagement.hospital.management.controller;

import com.hmanagement.hospital.management.dto.DoctorDto;
import com.hmanagement.hospital.management.service.DoctorService;
import com.hmanagement.hospital.management.service.implementation.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;


    @Autowired
    public DoctorController(DoctorServiceImpl doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/add-profile-details")
    public ResponseEntity<DoctorDto> addProfileDetails(@RequestBody DoctorDto doctorDto) {
        return doctorService.saveProfileDetails(doctorDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> getDoctor(@PathVariable("id") UUID id) {
        return doctorService.getDoctor(id);
    }
}
