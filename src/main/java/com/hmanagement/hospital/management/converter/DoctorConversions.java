package com.hmanagement.hospital.management.converter;

import com.hmanagement.hospital.management.dto.DoctorDto;
import com.hmanagement.hospital.management.dto.QualificationDto;
import com.hmanagement.hospital.management.entity.Doctor;
import jakarta.persistence.SecondaryTable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DoctorConversions {
    private final QualificationConverstions qualificationConverstions;

    @Autowired
    public DoctorConversions(QualificationConverstions qualificationConverstions) {
        this.qualificationConverstions = qualificationConverstions;
    }

    public DoctorDto toDoctorDto(Doctor doctor) {
        DoctorDto doctorDto = new DoctorDto();
        BeanUtils.copyProperties(doctor, doctorDto);
        doctorDto.setQualifications(qualificationConverstions.toQualificationDtoSet(doctor.getQualifications()));
        return doctorDto;
    }
    public Doctor toDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorDto, doctor);
        doctor.setQualifications(qualificationConverstions.toQualificationSet(doctorDto.getQualifications()));
        return doctor;
    }
}
