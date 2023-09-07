package com.example.hospitalmanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctorId;
    @Size(min=3)
    private String name;
    private String city;
    @Email
    private String email;
    @Length(min=10,max=10,message = "Enter a valid phone number")
    private String phoneNumber;
    private String speciality;

}
