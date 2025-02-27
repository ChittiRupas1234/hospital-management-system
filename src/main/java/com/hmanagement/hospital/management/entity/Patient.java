package com.hmanagement.hospital.management.entity;

import com.hmanagement.hospital.management.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "patient_registration")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Patient_ID")
    private Long patientId;

    @Column(name = "Full_Name")
//    @NotBlank(message = "Full name is required")
    private String fullName;

    @Column(name = "Age")
//    @Min(value = 0, message = "Age cannot be negative")
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "Gender", nullable = false)
    private Gender gender;

    @Column(name = "Contact_Number")
//    @NotBlank(message = "Contact number is required")
//    @Pattern(regexp = "^[0-9]{10,15}$", message = "Invalid contact number")
    private String contactNumber;

    @Column(name = "Email")
//    @Email(message = "Invalid email format")
    private String email;

    @Column(name = "Address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "Emergency_Contact_Name")
//    @NotBlank(message = "Emergency contact name is required")
    private String emergencyContactName;

    @Column(name = "Emergency_Contact_Phone")
//    @NotBlank(message = "Emergency contact phone is required")
//    @Pattern(regexp = "^[0-9]{10,15}$", message = "Invalid emergency contact phone")
    private String emergencyContactPhone;

    @Column(name = "Created_At", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;

}
