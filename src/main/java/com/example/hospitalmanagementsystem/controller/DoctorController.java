package com.example.hospitalmanagementsystem.controller;

import com.example.hospitalmanagementsystem.model.Doctor;
import com.example.hospitalmanagementsystem.service.DoctorService;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("doctor")
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor){
        return doctorService.addDoctor(doctor);
    }

    @DeleteMapping("doctor/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable int id){
        return doctorService.deleteDoctor(id);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("doctors")
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @GetMapping("doctor/{id}")
    public ResponseEntity<String> getDoctorById(@PathVariable int id){
        return doctorService.getDoctorById(id);
    }
}
