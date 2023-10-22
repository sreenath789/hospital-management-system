package com.example.hospitalmanagementsystem.controller;

import com.example.hospitalmanagementsystem.model.Doctor;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.model.dto.SignInInput;
import com.example.hospitalmanagementsystem.model.dto.SignUpOutput;
import com.example.hospitalmanagementsystem.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patient")
public class PatientController {


    @Autowired
    PatientService patientService;

    @PostMapping("patient/signup")
    public SignUpOutput signUpPatient(@RequestBody @Valid Patient patient)
    {
        return patientService.signUpPatient(patient);
    }

    @PostMapping("patient/signin")
    public String signInPatient(@RequestBody @Valid SignInInput signInInput)
    {
        return patientService.signInPatient(signInInput);
    }


}
