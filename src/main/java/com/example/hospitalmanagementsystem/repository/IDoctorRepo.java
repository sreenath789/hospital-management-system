package com.example.hospitalmanagementsystem.repository;

import com.example.hospitalmanagementsystem.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDoctorRepo extends JpaRepository<Doctor,Integer> {
}
