package com.carrerconnect.job_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="application")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
//	Job Title,Location, Description, Experience,
//	Salary, Notice Period, ContactEmail, Status etc.

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int applicationId; 
    private String applicantName;
    private String applicantEmail;


    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
    
    private String status ="pending";

}
