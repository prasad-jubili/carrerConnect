package com.carrerconnect.job_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {
    private int jobId;

        @NotBlank(message = "Job title is required")
        private String jobTitle;

        @NotBlank(message = "Location is required")
        private String location;

        @NotBlank(message = "Description cannot be empty")
        private String description;

        @Min(value = 0, message = "Experience cannot be negative")
        private int experience;

        @Min(value = 0, message = "Salary must be positive")
        private double salary;

        @NotBlank(message = "Notice period is required")
        private int noticePeriod;

        @Email(message = "Invalid email format")
        @NotBlank(message = "Contact email is required")
        private String contactEmail;

        @NotBlank(message = "Status is required")
        private String status;
}


