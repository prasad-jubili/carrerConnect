package com.carrerconnect.job_service.dto;

import lombok.Data;

@Data
public class EmployerResponseDTO {
    private int id;
    private String organizationName;
    private String address;
    private String contactNumber;
    private String email;
}
