package com.example.hospitalmanagementsystem.controller;

import com.example.hospitalmanagementsystem.model.Doctor;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patient")
public class PatientController {


    @Autowired
    PatientService patientService;

    @PostMapping("patient")
    public ResponseEntity<String> addPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient);
    }

    @DeleteMapping("patient/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable int id){
        return patientService.deletePatient(id);
    }

    @GetMapping("patients")
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @GetMapping("patient/{id}")
    public ResponseEntity<String> getPatientById(@PathVariable int id){
        return patientService.getPatientById(id);
    }

}
