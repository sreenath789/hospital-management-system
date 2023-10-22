package com.example.hospitalmanagementsystem.repository;

import com.example.hospitalmanagementsystem.model.AuthenticationToken;
import com.example.hospitalmanagementsystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findFirstByTokenValue(String token);

    AuthenticationToken findFirstByPatient(Patient patient);
}
