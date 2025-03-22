package com.hmanagement.hospital.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data @NoArgsConstructor @AllArgsConstructor
public class AppointmentDto extends BaseDto {
    private UUID appointmentId;
    private UUID doctorId;
    private Long patientId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
}
