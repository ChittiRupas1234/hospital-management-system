package com.hmanagement.hospital.management.service.implementation;

import com.hmanagement.hospital.management.constants.HMSConstants;
import com.hmanagement.hospital.management.converter.DoctorConversions;
import com.hmanagement.hospital.management.dto.DoctorDto;
import com.hmanagement.hospital.management.dto.PatientDTO;
import com.hmanagement.hospital.management.entity.Appointment;
import com.hmanagement.hospital.management.entity.Doctor;
import com.hmanagement.hospital.management.entity.Patient;
import com.hmanagement.hospital.management.enums.AppointmentStatus;
import com.hmanagement.hospital.management.repository.AppointmentRepository;
import com.hmanagement.hospital.management.repository.DoctorRepository;
import com.hmanagement.hospital.management.repository.PatientRepository;
import com.hmanagement.hospital.management.service.DoctorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorConversions doctorConversion;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorServiceHelper helper;
    @Autowired
    public DoctorServiceImpl(DoctorConversions doctorConversion,PatientRepository patientRepository, DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
            this.doctorConversion = doctorConversion;
            this.helper = new DoctorServiceHelper();
            this.patientRepository = patientRepository;
            this.doctorRepository = doctorRepository;
            this.appointmentRepository = appointmentRepository;
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
            if(doctorDetails.getisDeleted()) {
                throw new RuntimeException(HMSConstants.DoctorAccountDeleted);
            }
            DoctorDto doctorDetailsDto = doctorConversion.toDoctorDto(doctorDetails);
            return ResponseEntity.ok(doctorDetailsDto);
        } catch(Exception e) {
            DoctorDto errorDto = new DoctorDto();
            errorDto.setStatus(400);
            errorDto.setMessage(e.getMessage());
            return ResponseEntity.status(errorDto.getStatus()).body(errorDto);
        }
    }

    public ResponseEntity<List<PatientDTO>> getAppointments(UUID doctorId) {
        try {
            List<Appointment> todayAppointments = appointmentRepository.getAppointments(doctorId.toString());
            List<PatientDTO> patientsList = helper.getPatientsList(todayAppointments, patientRepository);
            return ResponseEntity.status(200).body(patientsList);
        } catch(Exception e) {
            return ResponseEntity.status(400).body(new ArrayList<>());
        }
    }

    @Override
    public ResponseEntity<List<PatientDTO>> getAppointments(UUID doctorId, LocalDate date) {
        try {
            List<Appointment> todayAppointments = appointmentRepository.getAppointments(doctorId.toString(), date);
            List<PatientDTO> patientsList = helper.getPatientsList(todayAppointments, patientRepository);
            return ResponseEntity.ok(patientsList);
        } catch(Exception e) {
            return ResponseEntity.status(400).body(new ArrayList<>());
        }
    }

    public ResponseEntity<List<PatientDTO>> getAllAppointments(UUID doctorId) {
        try {
            List<Appointment> allAppointments = appointmentRepository.getAllAppointments(doctorId.toString());
            List<PatientDTO> patientList = helper.getPatientsList(allAppointments, patientRepository);
            return ResponseEntity.ok(patientList);
        } catch(Exception e) {
            return ResponseEntity.status(400).body(new ArrayList<>());
        }
    }
}

class DoctorServiceHelper {
    List<PatientDTO> getPatientsList(List<Appointment> appointments, PatientRepository patientRepository) {
        List<PatientDTO> patientsList = new ArrayList<>();
        for(Appointment appointment : appointments) {
            if(appointment.getAppointmentStatus() == AppointmentStatus.CONFIRMED) {
                Patient patient = patientRepository.findById(appointment.getPatientId()).orElseThrow(() -> new RuntimeException(HMSConstants.PatientNotFound));
                PatientDTO patientDTO = new PatientDTO();
                BeanUtils.copyProperties(patient, patientDTO);
                patientsList.add(patientDTO);
            }
        }
        return patientsList;
    }
}