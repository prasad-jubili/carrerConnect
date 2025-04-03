package com.carrerconnect.jobseeker_service.servcie;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrerconnect.jobseeker_service.clientService.JobClient;
import com.carrerconnect.jobseeker_service.jobSeekerDTO.BasketDTO;
import com.carrerconnect.jobseeker_service.jobSeekerDTO.JobApplicationDTO;
import com.carrerconnect.jobseeker_service.jobSeekerDTO.JobDTO;
import com.carrerconnect.jobseeker_service.model.Basket;
import com.carrerconnect.jobseeker_service.model.JobSeeker;
import com.carrerconnect.jobseeker_service.repo.BasketRepo;
import com.carrerconnect.jobseeker_service.repo.JobSeekerRepo;

@Service
public class JobSeekerService {
	
	@Autowired
	JobSeekerRepo jobSeekerRepo;
	
	@Autowired
	BasketRepo basketRepo;
	
	@Autowired
	JobClient jobClient;
	
	

	public String registration(JobSeeker jobSeeker) {
		jobSeekerRepo.save(jobSeeker);
		return "Registered as a JobSeeker User";
	}

	public List<JobDTO> searchJobs(String skills, String company, String location) {
		return jobClient.searchJobs(skills, company, location);
	}

	public String applyJob(int jobId, JobApplicationDTO applicationDTO) {
		return jobClient.applyJob(jobId, applicationDTO);
	}

	public String addJobToBasket(int jobId, int jobSeekerId) {
		JobDTO job = jobClient.viewJob(jobId).getBody();
		
		Basket basket = new Basket(0, jobSeekerId, jobId, job.getJobTitle(), job.getLocation(),
	            job.getDescription(), job.getExperience(), job.getSalary(), job.getNoticePeriod(),
	            job.getSkillsRequired(),job.getCompany(),
	            job.getContactEmail(), job.getStatus());
		
		basketRepo.save(basket);
		
		return "Job is saved in basket";
		
	}

	public List<JobDTO> viewFavJobs(int jobSeekerId) {
		List<Basket> basketJobs= basketRepo.findAllByJobSeekerId(jobSeekerId);
		
		List<Integer> jobIds = basketJobs.stream()
				.map(Basket::getJobId)
				.collect(Collectors.toList());
		
		List<JobDTO> jobs = jobIds.stream()
				.map(jobId -> jobClient.viewJob(jobId).getBody())
				.collect(Collectors.toList());
		return jobs;
	}

	public String deleteJobFromBasket(int jobId, int jobSeekerId) {
		List<Basket> basketJobs = basketRepo.findAllByJobSeekerId(jobSeekerId);
		
	Optional<Basket> basketJob = basketJobs.stream()
				.filter(basket -> basket.getJobId() == jobId)
				.findFirst();
	if(basketJob.isPresent()) {
		basketRepo.delete(basketJob.get());
		return "Job is removed from basket";
	}
	else {
		return "Job not found in basket.";	
		}
	}

	public String applyJobFromBasket(int jobId, int jobSeekerId, JobApplicationDTO jobAppDTO) {
		Basket basket = basketRepo.findByJobSeekerIdAndJobId(jobSeekerId, jobId)
	            .orElseThrow(() -> new RuntimeException("Job not found in basket"));

	    // Apply for job using JobService API
	    jobClient.applyJob(jobId, jobAppDTO);
		return "Job application Submitted!";
	}

}
