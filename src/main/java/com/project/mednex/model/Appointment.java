package com.project.mednex.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    private LocalDate appointmentDate;
    private LocalTime appointmentTime;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    private String notes;
    private String reasonForVisit;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private String createdBy;

    // Empty constructor - Hibernate needs this
    public Appointment() {
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = AppointmentStatus.SCHEDULED;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // ========== GETTERS ==========

    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }

    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    // ========== SETTERS ==========

    public void setId(Long id) {
        this.id = id;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setReasonForVisit(String reasonForVisit) {
        this.reasonForVisit = reasonForVisit;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    // ========== BUILDER ==========

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Appointment appointment = new Appointment();

        public Builder patient(Patient patient) {
            appointment.patient = patient;
            return this;
        }

        public Builder doctor(Doctor doctor) {
            appointment.doctor = doctor;
            return this;
        }

        public Builder appointmentDate(LocalDate appointmentDate) {
            appointment.appointmentDate = appointmentDate;
            return this;
        }

        public Builder appointmentTime(LocalTime appointmentTime) {
            appointment.appointmentTime = appointmentTime;
            return this;
        }

        public Builder status(AppointmentStatus status) {
            appointment.status = status;
            return this;
        }

        public Builder notes(String notes) {
            appointment.notes = notes;
            return this;
        }

        public Builder reasonForVisit(String reasonForVisit) {
            appointment.reasonForVisit = reasonForVisit;
            return this;
        }

        public Builder createdBy(String createdBy) {
            appointment.createdBy = createdBy;
            return this;
        }

        public Appointment build() {
            return appointment;
        }
    }

    // ========== STATUS ENUM ==========

    public enum AppointmentStatus {
        SCHEDULED,
        COMPLETED,
        CANCELLED
    }
}