package com.carrerconnect.job_service.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="job")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Job {
//	Job Title,Location, Description,
//	Experience, Salary, Notice Period, ContactEmail, Status etc.

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jobId;
	
	@Column(nullable = false)
	private int employerId;
	
	private String jobTitle;
	private String location;
	private String description;
	private int experience;
	private double salary;
	private int noticePeriod;
	private String skillsRequired;
	private String company;
	
	@Column(unique = true, nullable = false)
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String contactEmail;
	
	private String status;
	
	
	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Application> applications;
}
