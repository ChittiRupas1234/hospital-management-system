package com.hmanagement.hospital.management.service;

import com.hmanagement.hospital.management.dto.AppointmentDto;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface AppointmentService {
    ResponseEntity<AppointmentDto> scheduleAppointment(AppointmentDto appointmentDetailsDto);
    ResponseEntity<AppointmentDto> cancelAppointment(UUID appointmentId);
}
