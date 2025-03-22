package com.hmanagement.hospital.management.entity.pharmacy;

import java.time.LocalDate;
import java.util.UUID;

public class Sales {
    private UUID saleId;
    private UUID patientId;
    private LocalDate saleDate;
    private double totalAmount;
}
