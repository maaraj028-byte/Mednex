package com.project.mednex.controller;

import com.project.mednex.dto.AppointmentRequest;
import com.project.mednex.model.Appointment;
import com.project.mednex.model.Doctor;
import com.project.mednex.model.Patient;
import com.project.mednex.repository.AppointmentRepository;
import com.project.mednex.repository.DoctorRepository;
import com.project.mednex.repository.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentController(AppointmentRepository appointmentRepository,
                                 PatientRepository patientRepository,
                                 DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping
    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    @GetMapping("/today")
    public List<Appointment> getToday() {
        return appointmentRepository
                .findByAppointmentDateOrderByAppointmentTimeAsc(LocalDate.now());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AppointmentRequest req) {

        boolean conflict = appointmentRepository
                .existsByDoctorIdAndAppointmentDateAndAppointmentTimeAndStatus(
                        req.getDoctorId(),
                        req.getAppointmentDate(),
                        req.getAppointmentTime(),
                        Appointment.AppointmentStatus.SCHEDULED
                );

        if (conflict) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Doctor already booked at this time!"));
        }

        Patient patient = patientRepository.findById(req.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Doctor doctor = doctorRepository.findById(req.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Appointment appt = new Appointment();
        appt.setPatient(patient);
        appt.setDoctor(doctor);
        appt.setAppointmentDate(req.getAppointmentDate());
        appt.setAppointmentTime(req.getAppointmentTime());
        appt.setReasonForVisit(req.getReasonForVisit());
        appt.setNotes(req.getNotes());
        appt.setStatus(Appointment.AppointmentStatus.SCHEDULED);

        return ResponseEntity.ok(appointmentRepository.save(appt));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Appointment> updateStatus(
            @PathVariable Long id,
            @RequestParam Appointment.AppointmentStatus status) {

        return appointmentRepository.findById(id).map(a -> {
            a.setStatus(status);
            return ResponseEntity.ok(appointmentRepository.save(a));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
            return ResponseEntity.ok("Appointment deleted");
        }
        return ResponseEntity.notFound().build();
    }
}