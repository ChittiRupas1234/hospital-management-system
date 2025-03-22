package com.hmanagement.hospital.management.dto;

import com.hmanagement.hospital.management.enums.Gender;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDto {

//    @NotBlank(message = "Full name is required")
    private String fullName;

//    @Min(value = 0, message = "Age cannot be negative")
    private int age;

//    @NotNull(message = "Gender is required")
    private Gender gender;

//    @NotBlank(message = "Contact number is required")
//    @Pattern(regexp = "^[0-9]{10,15}$", message = "Invalid contact number")
    private String contactNumber;

//    @Email(message = "Invalid email format")
    private String email;

    private String address;

//    @NotBlank(message = "Emergency contact name is required")
    private String emergencyContactName;

//    @NotBlank(message = "Emergency contact phone is required")
//    @Pattern(regexp = "^[0-9]{10,15}$", message = "Invalid emergency contact phone")
    private String emergencyContactPhone;


}
