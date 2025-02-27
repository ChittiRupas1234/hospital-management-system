package com.hmanagement.hospital.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class QualificationDto extends BaseDto {
    private UUID id;
    private String degreeName;
    private String specialization;

    public QualificationDto(UUID id, String degreeName, String specialization) {
        this.id = id;
        this.degreeName = degreeName;
        this.specialization = specialization;
    }

    public QualificationDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
