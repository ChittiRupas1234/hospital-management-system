package com.hospital.management.control;


import com.hospital.management.dto.PatientDTO;
import com.hospital.management.entity.Patient;
import com.hospital.management.service.PatientService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Patient> registerPatient(@Valid @RequestBody PatientDTO patientDTO) {
        return ResponseEntity.ok(patientService.registerPatient(patientDTO));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updateByID(@PathVariable Long id, @Valid @RequestBody PatientDTO patientDetails) {
        return ResponseEntity.ok(patientService.updatePatientById(id, patientDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatientById(id);
        return ResponseEntity.ok("Patient with ID " + id + " has been deleted successfully.");
    }
}