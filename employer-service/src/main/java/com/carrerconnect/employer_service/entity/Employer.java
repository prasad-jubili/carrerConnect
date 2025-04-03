package com.carrerconnect.employer_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Employer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employer { 
	//ID,organizationName,address,contactNumber,email

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String organizationName;
	private String address;

	@Column(unique = true, nullable = false)
	@NotBlank(message = "Contact number is required")
	@Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid contact number format")
	private String contactNumber;

	@Column(unique = true, nullable = false)
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

}
