package com.hmanagement.hospital.management.entity.pharmacy;

import java.time.LocalDate;
import java.util.UUID;

public class Medicine {
    private UUID id;
    private String name;
    private String batchNumber;
    private String manufacturer;
    private String category;
    private LocalDate expiryDate;
    private double purchasePrice;
    private double sellingPrice;
}
