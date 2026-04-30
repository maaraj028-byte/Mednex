package com.project.mednex.dto;

public class LoginResponse {

    private String token;
    private String username;
    private String fullName;
    private String role;
    private Long userId;

    public LoginResponse(String token, String username,
                         String fullName, String role, Long userId) {
        this.token = token;
        this.username = username;
        this.fullName = fullName;
        this.role = role;
        this.userId = userId;
    }

    public String getToken() { return token; }
    public String getUsername() { return username; }
    public String getFullName() { return fullName; }
    public String getRole() { return role; }
    public Long getUserId() { return userId; }
}