package com.hmanagement.hospital.management.service;

import com.hmanagement.hospital.management.dto.PatientDTO;
import com.hmanagement.hospital.management.entity.Patient;
import com.hmanagement.hospital.management.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public Patient registerPatient(PatientDTO patientDTO) {
        Patient patient = Patient.builder()
                .fullName(patientDTO.getFullName())
                .age(patientDTO.getAge())
                .gender(patientDTO.getGender())
                .contactNumber(patientDTO.getContactNumber())
                .email(patientDTO.getEmail())
                .address(patientDTO.getAddress())
                .emergencyContactName(patientDTO.getEmergencyContactName())
                .emergencyContactPhone(patientDTO.getEmergencyContactPhone())
                .build();
        return patientRepository.save(patient);
    }

    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(patient -> new PatientDTO(
                        patient.getFullName(),
                        patient.getAge(),
                        patient.getGender(),
                        patient.getContactNumber(),
                        patient.getEmail(),
                        patient.getAddress(),
                        patient.getEmergencyContactName(),
                        patient.getEmergencyContactPhone()))
                .collect(Collectors.toList());
    }

    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + id));

        // check is deleted or not

        return new PatientDTO(
                patient.getFullName(),
                patient.getAge(),
                patient.getGender(),
                patient.getContactNumber(),
                patient.getEmail(),
                patient.getAddress(),
                patient.getEmergencyContactName(),
                patient.getEmergencyContactPhone());
    }

    public PatientDTO updatePatientById(Long id, PatientDTO updatedPatientDto) {
        Patient oldPatientDetails = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + id));

        BeanUtils.copyProperties(updatedPatientDto, oldPatientDetails);

        Patient updatedPatientDetails = patientRepository.save(oldPatientDetails);

        PatientDTO updatedPatientDetailsDto = new PatientDTO();
        BeanUtils.copyProperties(updatedPatientDto, updatedPatientDetailsDto);

        return updatedPatientDetailsDto;

//        patient.setFullName(patientDetails.getFullName());
//        patient.setAge(patientDetails.getAge());
//        patient.setGender(patientDetails.getGender());
//        patient.setContactNumber(patientDetails.getContactNumber());
//        patient.setEmail(patientDetails.getEmail());
//        patient.setAddress(patientDetails.getAddress());
//        patient.setEmergencyContactName(patientDetails.getEmergencyContactName());
//        patient.setEmergencyContactPhone(patientDetails.getEmergencyContactPhone());
//
//
//        Patient updatedPatient = patientRepository.save(patient);
//
//        return new PatientDTO(
//                updatedPatient.getFullName(),
//                updatedPatient.getAge(),
//                updatedPatient.getGender(),
//                updatedPatient.getContactNumber(),
//                updatedPatient.getEmail(),
//                updatedPatient.getAddress(),
//                updatedPatient.getEmergencyContactName(),
//                updatedPatient.getEmergencyContactPhone());
    }
    public void deletePatientById(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient not found with ID: " + id);
        }
        patientRepository.deleteById(id);
    }
}