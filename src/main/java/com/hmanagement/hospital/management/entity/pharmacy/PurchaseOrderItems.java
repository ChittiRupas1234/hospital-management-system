package com.hmanagement.hospital.management.entity.pharmacy;

import java.util.UUID;

public class PurchaseOrderItems {
    private UUID id;
    private UUID orderId;
    private UUID medicineId;
    private int quantity;
    private double unitPrice;
}
