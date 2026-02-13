package com.carrerconnect.job_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationDTO {

    @NotBlank(message = "Applicant name is required")
    private String applicantName;

    @NotBlank(message = "Applicant email is required")
    @Email(message = "Invalid Email format")
    private String applicantEmail;

}

