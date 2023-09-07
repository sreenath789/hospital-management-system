package com.example.hospitalmanagementsystem.service;

import com.example.hospitalmanagementsystem.model.Doctor;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.repository.IPatientRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    IPatientRepo iPatientRepo;

    public ResponseEntity<String> addPatient(Patient patient) {
        if (valid(patient).isEmpty()) {
            iPatientRepo.save(patient);
            return new ResponseEntity<>("Patient profile created successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(valid(patient).toString(), HttpStatus.BAD_REQUEST);
        }
    }

    private static JSONObject valid(Patient patient) {
        JSONObject errors = new JSONObject();
        String city = patient.getCity().toLowerCase();
        if (!(city.equals("noida") || city.equals("delhi") || city.equals("faridabad"))) {
            errors.put(city, "We are still waiting to expand to your location");
        }
        String symptom = patient.getSymptom().toLowerCase();
        if (!(symptom.equals("arthritis") || symptom.equals("back pain") || symptom.equals("tissue injuries")
                || symptom.equals("dysmenorrhea ") || symptom.equals("skin infection") || symptom.equals("skin burn")
                || symptom.equals("ear pain"))) {
            errors.put(symptom, "There isn’t any doctor present at your location for your symptom");
        }

        return errors;
    }

    public ResponseEntity<String> deletePatient(int id) {
        if (!iPatientRepo.existsById(id)) {
            return new ResponseEntity<>("Patient id not found", HttpStatus.NOT_FOUND);
        }
        iPatientRepo.deleteById(id);
        return new ResponseEntity<>("Patient deleted successfully", HttpStatus.OK);
    }

    public List<Patient> getAllPatients() {
        return iPatientRepo.findAll();
    }

    public ResponseEntity<String> getPatientById(int id) {
        if (iPatientRepo.existsById(id)) {
            return new ResponseEntity<>(iPatientRepo.findById(id).toString(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Patient id not found", HttpStatus.NOT_FOUND);
        }
    }

}



