package com.carrerconnect.employer_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrerconnect.employer_service.DTO.JobDTO;
import com.carrerconnect.employer_service.clientService.JobClient;
import com.carrerconnect.employer_service.entity.Employer;
import com.carrerconnect.employer_service.repo.EmployerRepo;

@Service
public class EmployerService {
	
	@Autowired
	EmployerRepo employerRepo;

	@Autowired
	JobClient jobClient;
	
	public String registration(Employer employer) {
		 employerRepo.save(employer);
		 return "Employer is register";
	}

	public String postJob(JobDTO job) {
		jobClient.postJob(job);
		return "Job is posted";
	}

	public JobDTO editPost(int jobId, JobDTO job) {
		jobClient.editJob(jobId,job);
		return job;
	}

	public String deleteJob(int jobId) {
		jobClient.deleteJob(jobId);
		return "Job post is deleted";
	}

}
