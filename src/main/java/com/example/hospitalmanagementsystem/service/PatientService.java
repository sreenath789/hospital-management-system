package com.example.hospitalmanagementsystem.service;

import com.example.hospitalmanagementsystem.model.AuthenticationToken;
import com.example.hospitalmanagementsystem.model.Doctor;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.model.dto.SignInInput;
import com.example.hospitalmanagementsystem.model.dto.SignUpOutput;
import com.example.hospitalmanagementsystem.repository.IAuthenticationRepo;
import com.example.hospitalmanagementsystem.repository.IPatientRepo;
import com.example.hospitalmanagementsystem.service.utility.Email.EmailHandler;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    IPatientRepo iPatientRepo;

    @Autowired
    IAuthenticationRepo iAuthenticationRepo;

    public SignUpOutput signUpPatient(Patient patient) {

        boolean signUpStatus = true;
        String signUpStatusMessage=null;

        String newEmail = patient.getEmail();

        Patient existingPatient = iPatientRepo.findFirstByPatientEmail(newEmail);

        if(existingPatient!=null){
            signUpStatusMessage="Email already Registered!!!";
            signUpStatus=false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        try{
            iPatientRepo.save(patient);
            return new SignUpOutput(signUpStatus,"Patient Registered Successfully!!!");
        }
        catch (Exception e){
            signUpStatus=false;
            signUpStatusMessage="Internal error occurred during sign up";
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }


    }

    public String signInPatient(SignInInput signInInput) {
        String signInStatusMessage = null;
        String signInEmail = signInInput.getEmail();

        if(signInEmail==null){
            signInStatusMessage = "Invalid Email";
            return signInStatusMessage;
        }

        Patient existingPatient = iPatientRepo.findFirstByPatientEmail(signInEmail);

        if(existingPatient == null){
            signInStatusMessage = "Email not registered";
            return signInStatusMessage;
        }

        //Match Passwords

        try{

            if(existingPatient.getPassword().equals(signInInput.getPassword())){

                //create a session for authentication
                AuthenticationToken authenticationToken = new AuthenticationToken(existingPatient);
                iAuthenticationRepo.save(authenticationToken);

                EmailHandler.sendEmail(signInEmail,"Authentication Email",authenticationToken.getTokenValue());

                return "Authentication Token sent to your mail";


            }
            else{
                signInStatusMessage = "Invalid credentials";
                return signInStatusMessage;
            }


        }
        catch(Exception e){
            signInStatusMessage = "Internal error occurred";
            return signInStatusMessage;
        }

    }



}



