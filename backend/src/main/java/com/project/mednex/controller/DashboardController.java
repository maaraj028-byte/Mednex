package com.project.mednex.controller;

import com.project.mednex.model.Appointment.AppointmentStatus;
import com.project.mednex.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    
    public DashboardController(PatientRepository patientRepository,
            DoctorRepository doctorRepository,
            AppointmentRepository appointmentRepository) {
this.patientRepository = patientRepository;
this.doctorRepository = doctorRepository;
this.appointmentRepository = appointmentRepository;
}

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        return Map.of(
            "totalPatients", patientRepository.countActivePatients(),
            "totalDoctors", doctorRepository.count(),
            "scheduledAppointments", appointmentRepository
                .countByStatus(AppointmentStatus.SCHEDULED),
            "completedToday", appointmentRepository
                .countByStatus(AppointmentStatus.COMPLETED)
        );
    }
}