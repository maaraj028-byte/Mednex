package com.project.mednex.repository;

import com.project.mednex.model.Appointment;
import com.project.mednex.model.Appointment.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate date);
    List<Appointment> findByPatientId(Long patientId);
    long countByStatus(AppointmentStatus status);
    List<Appointment> findByAppointmentDateOrderByAppointmentTimeAsc(LocalDate date);

    @Query("SELECT COUNT(a) > 0 FROM Appointment a " +
           "WHERE a.doctor.id = :doctorId " +
           "AND a.appointmentDate = :date " +
           "AND a.appointmentTime = :time " +
           "AND a.status = 'SCHEDULED'")
    boolean existsConflict(
        @Param("doctorId") Long doctorId,
        @Param("date") LocalDate date,
        @Param("time") LocalTime time
    );
}