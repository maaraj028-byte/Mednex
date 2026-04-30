package com.project.mednex.dto;

import com.project.mednex.model.User.Role;

public class RegisterRequest {

    private String username;
    private String password;
    private String email;
    private String fullName;
    private Role role;
    private String specialization;
    private String department;
    private String phone;

    public RegisterRequest() {
    }

    // ===== Getters =====
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public Role getRole() {
        return role;
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

    // ===== Setters =====
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setRole(Role role) {
        this.role = role;
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
}