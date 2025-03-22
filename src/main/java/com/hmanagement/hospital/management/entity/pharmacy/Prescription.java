package com.hmanagement.hospital.management.entity.pharmacy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Prescription {
    private UUID prescriptionId;
    private UUID patientId;
    private UUID doctorId;
    private LocalDateTime prescriptionDateTime;
}
