package com.hmanagement.hospital.management.converter;

import com.hmanagement.hospital.management.dto.QualificationDto;
import com.hmanagement.hospital.management.entity.Qualifications;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class QualificationConverstions {
    public Qualifications toQualifications(QualificationDto qualificationDto) {
        Qualifications qualifications = new Qualifications();
        BeanUtils.copyProperties(qualificationDto, qualifications);
        return qualifications;
    }

    public QualificationDto toQualificationDto(Qualifications qualifications) {
        QualificationDto qualificationDto = new QualificationDto();
        BeanUtils.copyProperties(qualifications, qualificationDto);
        return qualificationDto;
    }

    public Set<QualificationDto> toQualificationDtoSet(Set<Qualifications> qualifications) {
        Set<QualificationDto> set = new HashSet<>();
        for(Qualifications qualification : qualifications) {
            set.add(toQualificationDto(qualification));
        }
        return set;
    }

    public Set<Qualifications> toQualificationSet(Set<QualificationDto> qualificationDtos) {
        Set<Qualifications> set = new HashSet<>();
        for(QualificationDto qualificationDto : qualificationDtos) {
            set.add(toQualifications(qualificationDto));
        }
        return set;
    }
}
