package com.example.hospitalmanagementsystem.service;

import com.example.hospitalmanagementsystem.model.Doctor;
import com.example.hospitalmanagementsystem.repository.IDoctorRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    IDoctorRepo iDoctorRepo;

    public ResponseEntity<String> addDoctor(Doctor doctor) {
        if(valid(doctor).isEmpty()){
            iDoctorRepo.save(doctor);
            return new ResponseEntity<>("Doctor added successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(valid(doctor).toString(), HttpStatus.BAD_REQUEST);
        }
    }

    private static JSONObject valid(Doctor doctor){
        JSONObject errors = new JSONObject();
        String city = doctor.getCity().toLowerCase();
        if(!(city.equals("noida") || city.equals("delhi") || city.equals("faridabad"))){
            errors.put(city,"Doctor Location should be in NCR");
        }
        String speciality = doctor.getSpeciality().toLowerCase();
        if(!(speciality.equals("orthopedic") || speciality.equals("gynecology") || speciality.equals("dermatology") ||
                speciality.equals("ent"))){
            errors.put(speciality,"Doctor Speciality should be in Orthopedic, Gynecology, Dermatology, ENT");
        }
        return errors;
    }

    public ResponseEntity<String> deleteDoctor(int id) {
        if(!iDoctorRepo.existsById(id)){
            return new ResponseEntity<>("Doctor id not found", HttpStatus.NOT_FOUND);
        }
        iDoctorRepo.deleteById(id);
        return new ResponseEntity<>("Doctor deleted successfully", HttpStatus.OK);
    }

    public List<Doctor> getAllDoctors() {
        return iDoctorRepo.findAll();
    }

    public ResponseEntity<String> getDoctorById(int id) {
        if (iDoctorRepo.existsById(id)){
            return new ResponseEntity<>(iDoctorRepo.findById(id).toString(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Doctor id not found", HttpStatus.NOT_FOUND);
        }
    }
}
