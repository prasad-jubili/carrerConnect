package com.carrerconnect.jobseeker_service.model;

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
@Table(name = "jobseeker")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSeeker {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jobSeekerId;
	
//	JobSeeker’sName, Address, ContactNo, Email , Skill Set etc.
	
	private String name;
	private String Address;

	@Column(unique = true, nullable = false)
	@NotBlank(message = "Contact number is required")
	@Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid contact number format")
	private String contactNumber;
	

	@Column(unique = true, nullable = false)
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;
	
	private String skillSet;
	

}
