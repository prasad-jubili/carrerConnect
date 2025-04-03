package com.carrerconnect.job_service.service;

import java.util.Collections;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrerconnect.job_service.DTO.JobApplicationDTO;
import com.carrerconnect.job_service.DTO.JobDTO;
import com.carrerconnect.job_service.model.Application;
import com.carrerconnect.job_service.model.Job;
import com.carrerconnect.job_service.repo.ApplicationRepo;
import com.carrerconnect.job_service.repo.JobRepo;

@Service
public class JobService {
	
	@Autowired
	JobRepo jobRepo;
	
	@Autowired
	ApplicationRepo applicationRepo;

	public String postJob(JobDTO jobDTO) {
		Job job = new Job();
	    job.setJobTitle(jobDTO.getJobTitle());
	    job.setLocation(jobDTO.getLocation());
	    job.setDescription(jobDTO.getDescription());
	    job.setExperience(jobDTO.getExperience());
	    job.setSalary(jobDTO.getSalary());
	    job.setNoticePeriod(jobDTO.getNoticePeriod());
	    job.setContactEmail(jobDTO.getContactEmail());
	    job.setStatus(jobDTO.getStatus());
	    job.setApplications(Collections.emptyList());
	    jobRepo.save(job);
		return "Job is posted";
	}

	public Job editJob(int jobId, Job updatedJob) {
		return jobRepo.findById(jobId)
		.map(existingJob -> {
			existingJob.setJobTitle(updatedJob.getJobTitle());
			existingJob.setContactEmail(updatedJob.getContactEmail());
			existingJob.setDescription(updatedJob.getDescription());
			existingJob.setExperience(updatedJob.getExperience());
			existingJob.setLocation(updatedJob.getLocation());
			existingJob.setNoticePeriod(updatedJob.getNoticePeriod());
			existingJob.setSalary(updatedJob.getSalary());
			existingJob.setStatus(updatedJob.getStatus());
			return jobRepo.save(existingJob);
			})
		.orElseThrow(() -> new RuntimeException("No job posting is found with given JobId"));
		
	}

	public String deleteJob(int jobId) {
	    if (!jobRepo.existsById(jobId)) {
	        throw new RuntimeException("No job is found with the given JobId");
	    }

	    jobRepo.deleteById(jobId);
	    return "Job deleted successfully";
	}

	public List<Job> viewAllJobsByEmp(int empId) {
		if(!jobRepo.existsByEmployerId(empId)) {
			throw new RuntimeException("No employee found with given Id");		}
		
		return jobRepo.findAllByEmployerId(empId);	}
	
	public List<Job> viewAllJobs() {
		
		return jobRepo.findAll();	}

	public String applyJob(int jobId, JobApplicationDTO applicationDTO) {
		Job job = jobRepo.findById(jobId)
				.orElseThrow(() -> new RuntimeException("No job found for jobId : " +jobId));
		
		Application application = new Application();
		application.setApplicantEmail(applicationDTO.getApplicantEmail());
		application.setApplicantName(applicationDTO.getApplicantName());
		application.setJob(job);
		applicationRepo.save(application);
		return "Application submitted successfully";
	}

	public List<Job> searchJobs(String skills, String company, String location) {
		return jobRepo.searchJobs(skills,company,location);
	}

	public Application updateStatus(int applicationId, String status) {
		if(!status.equalsIgnoreCase("rejected")&& !status.equalsIgnoreCase("approved")) {
			throw new IllegalArgumentException("Incorrect Status provided. Allowed: Rejected, Approved");
		}
		Application application = applicationRepo.findById(applicationId)
				.orElseThrow(()-> new RuntimeException("No applicent found with applicationId : "+applicationId));
		application.setStatus(status);
//		applicationRepo.save(application);
		return applicationRepo.save(application);
	}

	public Job viewJob(int jobId) {
		Job job = jobRepo.findById(jobId)
				.orElseThrow(() -> new RuntimeException("No job found with jobId : "+jobId));
		return job;
	}


}
