package com.carrerconnect.jobseeker_service.jobSeekerDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationDTO {
    private String applicantName;
    private String applicantEmail;
//    private String resumeUrl; 
}

