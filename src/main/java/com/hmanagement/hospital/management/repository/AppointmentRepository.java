package com.hmanagement.hospital.management.repository;

import com.hmanagement.hospital.management.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    @Query(nativeQuery = true, value = "select * from appointments where doctor_id = :doctorId and appointment_date = curdate()")
    public List<Appointment> getAppointments(@Param("doctorId") String doctorId);

    @Query(nativeQuery = true, value = "select * from appointments where doctor_id = :doctorId and appointment_date = :date")
    public List<Appointment> getAppointments(@Param("doctorId") String doctorId, @Param("date")LocalDate date);

    @Query(nativeQuery = true, value = "select * from appointments where doctor_id = :doctorId")
    List<Appointment> getAllAppointments(@Param("doctorId") String doctorId);
}
