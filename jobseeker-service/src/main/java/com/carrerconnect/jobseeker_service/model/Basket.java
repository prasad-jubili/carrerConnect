package com.carrerconnect.jobseeker_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "basket")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int basketId;

    private int jobSeekerId; // To identify the user

    private int jobId; // Reference to Job
    
    private String jobTitle;
    private String location;
    private String description;
    private int experience;
    private int salary;
    private int noticePeriod;
    private String skillsRequired;
    private String company;
    private String contactEmail;
    private String status;
}

