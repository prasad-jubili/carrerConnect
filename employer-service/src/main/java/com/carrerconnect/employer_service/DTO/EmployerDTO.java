package com.carrerconnect.employer_service.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDTO {

    private int id;
    @NotBlank(message = "Organization name is required")
    private String organizationName;
    private String address;


    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid contact number format")
    private String contactNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

}
