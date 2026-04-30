package com.project.mednex.controller;

import com.project.mednex.model.Doctor;
import com.project.mednex.repository.DoctorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @GetMapping
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getOne(@PathVariable Long id) {
        return doctorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Doctor create(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(
            @PathVariable Long id,
            @RequestBody Doctor updated) {

        return doctorRepository.findById(id).map(d -> {
            d.setFirstName(updated.getFirstName());
            d.setLastName(updated.getLastName());
            d.setSpecialization(updated.getSpecialization());
            d.setDepartment(updated.getDepartment());
            d.setPhone(updated.getPhone());
            return ResponseEntity.ok(doctorRepository.save(d));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return ResponseEntity.ok("Doctor deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}