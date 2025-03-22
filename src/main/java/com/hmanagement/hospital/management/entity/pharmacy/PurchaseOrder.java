package com.hmanagement.hospital.management.entity.pharmacy;

import java.time.LocalDate;
import java.util.UUID;

public class PurchaseOrder {
    private UUID orderId;
    private UUID supplierId;
    private LocalDate orderDate;
    private double totalAmount;
}
