package com.carrerconnect.job_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {
    
    private int employerId;
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

