package com.carrerconnect.jobseeker_service.jobSeekerDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketDTO {
    private int jobSeekerId; // To identify the user
    private int jobId; // Reference to Job
    private String jobTitle;
    private String location;
    private String description;
    private int experience;
    private int salary;
    private int noticePeriod;
    private String contactEmail;
    private String status;
}
