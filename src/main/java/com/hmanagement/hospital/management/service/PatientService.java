package com.hmanagement.hospital.management.service;

import com.hmanagement.hospital.management.dto.PatientDto;

import com.hmanagement.hospital.management.entity.Patient;
import com.hmanagement.hospital.management.exceptions.PatientNotFoundException;
import com.hmanagement.hospital.management.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public Patient registerPatient(PatientDto PatientDto) {

        Patient patient=new Patient();
        BeanUtils.copyProperties(PatientDto, patient);

        return patientRepository.save(patient);
    }


    public List<PatientDto> getAllPatients() {
        List<Patient> activePatients = patientRepository.findAllActivePatients();
        List<PatientDto> patientDtoList = new ArrayList<>();

        activePatients.forEach(patient -> {
            PatientDto patientDto = new PatientDto();
            BeanUtils.copyProperties(patient, patientDto);
            patientDtoList.add(patientDto);
        });

        return patientDtoList;
    }


    public PatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));


        if (Boolean.TRUE.equals(patient.getIsDeleted())) {
            throw new PatientNotFoundException("Patient with ID " + id + " has been deleted.");
        }

        PatientDto patientDto = new PatientDto();
        BeanUtils.copyProperties(patient, patientDto);
        return patientDto;
    }


    public PatientDto updatePatientById(Long id, PatientDto updatedPatientDto) {
        Patient oldPatientDetails = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));

        if(oldPatientDetails.getIsDeleted()) { // account is deleted
            throw new PatientNotFoundException("Account is Deleted, please contact reception office for more details");
        }

        BeanUtils.copyProperties(updatedPatientDto, oldPatientDetails);

        Patient updatedPatientDetails = patientRepository.save(oldPatientDetails);

        PatientDto updatedPatientDetailsDto = new PatientDto();
        BeanUtils.copyProperties(updatedPatientDto, updatedPatientDetailsDto);

        return updatedPatientDetailsDto;
    }
    public void deletePatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));

        patient.setIsDeleted(true);
        patientRepository.save(patient);
    }
}