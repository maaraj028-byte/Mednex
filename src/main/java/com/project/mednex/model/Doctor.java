package com.project.mednex.model;

import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private String firstName;
    private String lastName;
    private String specialization;
    private String department;
    private String phone;
    private boolean available = true;

    // Empty constructor - Hibernate needs this
    public Doctor() {
    }

    // ========== GETTERS ==========

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getDepartment() {
        return department;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    // ========== SETTERS ==========

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // ========== BUILDER ==========

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Doctor doctor = new Doctor();

        public Builder user(User user) {
            doctor.user = user;
            return this;
        }

        public Builder firstName(String firstName) {
            doctor.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            doctor.lastName = lastName;
            return this;
        }

        public Builder specialization(String specialization) {
            doctor.specialization = specialization;
            return this;
        }

        public Builder department(String department) {
            doctor.department = department;
            return this;
        }

        public Builder phone(String phone) {
            doctor.phone = phone;
            return this;
        }

        public Builder available(boolean available) {
            doctor.available = available;
            return this;
        }

        public Doctor build() {
            return doctor;
        }
    }
}