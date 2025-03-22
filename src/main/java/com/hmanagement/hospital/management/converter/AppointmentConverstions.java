package com.hmanagement.hospital.management.converter;

import com.hmanagement.hospital.management.constants.HMSConstants;
import com.hmanagement.hospital.management.dto.AppointmentDto;
import com.hmanagement.hospital.management.entity.Appointment;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class AppointmentConverstions {
    public Appointment toAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        if(ObjectUtils.isEmpty(appointmentDto))
            throw new RuntimeException(HMSConstants.AppointmentDetailsEmpty);
        BeanUtils.copyProperties(appointmentDto, appointment);
        return appointment;
    }

    public AppointmentDto toAppointmentDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        if(ObjectUtils.isArray(appointment)) throw new RuntimeException(HMSConstants.AppointmentDetailsEmpty);
        BeanUtils.copyProperties(appointment, appointmentDto);
        return appointmentDto;
    }
}
