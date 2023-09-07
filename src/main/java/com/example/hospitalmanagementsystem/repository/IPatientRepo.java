package com.example.hospitalmanagementsystem.repository;

import com.example.hospitalmanagementsystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepo extends JpaRepository<Patient,Integer> {

}
