package com.project.mednex.service;

import com.project.mednex.dto.*;
import com.project.mednex.model.*;
import com.project.mednex.repository.*;
import com.project.mednex.security.JwtUtil;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    
    public AuthService(UserRepository userRepository,
                       DoctorRepository doctorRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()));

        User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() ->
                new UsernameNotFoundException("User not found"));

        String token = jwtUtil.generateToken(user.getUsername());
        return new LoginResponse(token, user.getUsername(),user.getFullName(), user.getRole().name(), user.getId());
    }

    @Transactional
    public String register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("Username already taken");

        if (userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("Email already registered");

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setRole(request.getRole());
        user.setActive(true);

        userRepository.save(user);

        if (request.getRole() == User.Role.DOCTOR) {
            Doctor doctor = new Doctor();
            doctor.setUser(user);
            doctor.setFirstName(request.getFullName());
            doctor.setSpecialization(request.getSpecialization());
            doctor.setDepartment(request.getDepartment());
            doctor.setPhone(request.getPhone());
            doctor.setAvailable(true);

            doctorRepository.save(doctor);
        }

        return "User registered successfully";
    }
}