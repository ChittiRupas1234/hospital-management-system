package com.hmanagement.hospital.management.service.implementation;

import com.hmanagement.hospital.management.constants.HMSConstants;
import com.hmanagement.hospital.management.converter.AppointmentConverstions;
import com.hmanagement.hospital.management.dto.AppointmentDto;
import com.hmanagement.hospital.management.entity.Appointment;
import com.hmanagement.hospital.management.enums.AppointmentStatus;
import com.hmanagement.hospital.management.repository.AppointmentRepository;
import com.hmanagement.hospital.management.repository.DoctorRepository;
import com.hmanagement.hospital.management.repository.PatientRepository;
import com.hmanagement.hospital.management.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentConverstions appointmentConverstions;

    @Autowired
    public AppointmentServiceImpl(PatientRepository patientRepository,
                                  DoctorRepository doctorRepository,
                                  AppointmentConverstions appointmentConverstions,
                                  AppointmentRepository appointmentRepository
    ) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentConverstions = appointmentConverstions;
    }

    public ResponseEntity<AppointmentDto> scheduleAppointment(AppointmentDto appointmentDetailsDto) {
        try {
            patientRepository.findById(appointmentDetailsDto.getPatientId())
                    .orElseThrow(() -> new RuntimeException(HMSConstants.PatientNotFound));

            doctorRepository.findById(appointmentDetailsDto.getDoctorId())
                    .orElseThrow(() -> new RuntimeException(HMSConstants.DoctorNotFound));

            if(appointmentDetailsDto.getAppointmentDate().isBefore(LocalDate.now()) || appointmentDetailsDto.getAppointmentTime().isBefore(LocalTime.now())) {
                throw new RuntimeException(HMSConstants.AppointmentMustBeFeature);
            }

            Appointment appointmentDetails = appointmentConverstions.toAppointment(appointmentDetailsDto);
            Appointment newAppointment = appointmentRepository.save(appointmentDetails);
            AppointmentDto newAppointmentDto = appointmentConverstions.toAppointmentDto(newAppointment);
            return ResponseEntity.ok(newAppointmentDto);
        }
        catch (Exception e) {
            AppointmentDto error = new AppointmentDto();
            error.setStatus(400);
            error.setMessage(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

    public ResponseEntity<AppointmentDto> cancelAppointment(UUID appointmentId) {
        try {
            Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
            appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
            Appointment cancelledAppointment = appointmentRepository.save(appointment);
            AppointmentDto cancelledAppointmentDto = appointmentConverstions.toAppointmentDto(cancelledAppointment);
            return ResponseEntity.ok(cancelledAppointmentDto);
        } catch(Exception e) {
            AppointmentDto error = new AppointmentDto();
            error.setStatus(400);
            error.setMessage(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }
}
