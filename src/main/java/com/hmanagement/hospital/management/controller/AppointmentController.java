package com.hmanagement.hospital.management.controller;

import com.hmanagement.hospital.management.dto.AppointmentDto;
import com.hmanagement.hospital.management.service.AppointmentService;
import com.hmanagement.hospital.management.service.implementation.AppointmentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/schedule")
    public ResponseEntity<AppointmentDto> scheduleAppointment(@RequestBody  AppointmentDto newAppointment) {
        return appointmentService.scheduleAppointment(newAppointment);
    }

    @PutMapping("/cancel-schedule/{appointmentId}")
    public ResponseEntity<AppointmentDto> cancelAppointment(@PathVariable("appointmentId")UUID appointmentId) {
        return appointmentService.cancelAppointment(appointmentId);
    }
}
