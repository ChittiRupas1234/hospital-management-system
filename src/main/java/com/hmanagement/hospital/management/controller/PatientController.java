package com.hmanagement.hospital.management.controller;


import com.hmanagement.hospital.management.dto.PatientDto;
import com.hmanagement.hospital.management.entity.Patient;
import com.hmanagement.hospital.management.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/register")
    public ResponseEntity<Patient> registerPatient(@RequestBody PatientDto PatientDto) {
        return ResponseEntity.ok(patientService.registerPatient(PatientDto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        List<PatientDto> patients = patientService.getAllPatients();

        if (patients.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(patients);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updateByID(@PathVariable Long id, @RequestBody PatientDto patientDetails) {
        return ResponseEntity.ok(patientService.updatePatientById(id, patientDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatientById(id);
        return ResponseEntity.ok("Patient with ID " + id + " has been deleted successfully.");
    }
}