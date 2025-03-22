package com.hmanagement.hospital.management.entity.pharmacy;

import java.util.UUID;

public class PrescriptionItems {
    private UUID id;
    private UUID prescriptionId;
    private UUID medicineId;
    private String dosage;
    private int frequency;
    private String frequencyIn;
    private int duration;
    private String durationIn;
}
