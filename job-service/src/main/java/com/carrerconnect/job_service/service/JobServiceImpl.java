package com.carrerconnect.job_service.service;

import java.util.List;


import com.carrerconnect.job_service.client.EmployerClient;
import com.carrerconnect.job_service.exception.ResourceNotFoundException;
import com.carrerconnect.job_service.mapper.JobMapper;
import com.carrerconnect.job_service.entity.Application;
import com.carrerconnect.job_service.entity.Job;
import com.carrerconnect.job_service.repo.ApplicationRepo;
import com.carrerconnect.job_service.repo.JobRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.carrerconnect.job_service.dto.JobApplicationDTO;
import com.carrerconnect.job_service.dto.JobDTO;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JobServiceImpl implements JobService {

//	@Autowired         --never use field injection in production level code
//	JobRepo jobRepo;
//	@Autowired
//	ApplicationRepo applicationRepo;

	private final JobRepo jobRepo;
	private final ApplicationRepo applicationRepo;
	private final JobMapper jobMapper;
	private final EmployerClient employerClient;


	public JobServiceImpl(JobRepo jobRepo,
						  ApplicationRepo applicationRepo,
						  JobMapper jobMapper,
						  EmployerClient employerClient) {
		this.jobRepo = jobRepo;
		this.applicationRepo = applicationRepo;
		this.jobMapper = jobMapper;
		this.employerClient = employerClient;
	}

	private static final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);


	public String postJob(JobDTO jobDTO) {

		// Validate employer existence
		try {
			employerClient.getEmployerById(jobDTO.getEmployerId());
		} catch (Exception ex) {
			throw new ResourceNotFoundException(
					"Employer not found with id: " + jobDTO.getEmployerId()
			);
		}

		Job job = jobMapper.toEntity(jobDTO);
		jobRepo.save(job);

		return "Job is posted";
	}


	@Override
	public JobDTO editJob(int jobId, JobDTO updatedJob) {

		log.info("updating job with Id: {}",jobId);

		Job job = jobRepo.findById(jobId)
				.orElseThrow(() -> {log.error("Job not found with id: {}", jobId);
					return new ResourceNotFoundException("Job not found with Id "+ jobId);
				});

		jobMapper.updateEntity(job, updatedJob);

		Job saved = jobRepo.save(job);

		return jobMapper.toDTO(saved);
	}

	@Override
	public String deleteJob(int jobId) {
		log.warn("Deleting job with Id : {}",jobId);
		Job job = jobRepo.findById(jobId)
				.orElseThrow(() -> {
					log.error("Job not found with Id : {}",jobId);
					return new ResourceNotFoundException("Job not found with id "+jobId);
				});

		jobRepo.delete(job);

		log.info("Job deleted successfully");
		return "Job deleted successfully";
	}

	@Override
	public List<JobDTO> viewAllJobsByEmp(int empId) {
		log.debug("Fetching all the jobs applied by employee with empId: {}",empId);
		List<Job> jobs = jobRepo.findAllByEmployerId(empId);


		if(jobs.isEmpty()) {
			log.info("no jobs found for employer with empID: {}",empId);
			throw new ResourceNotFoundException("No jobs found for employer " + empId);
		}

		List<JobDTO> dto = jobs.stream().map(jobMapper::toDTO).toList();
		log.info("jobs are fetched");
		return dto;
	}


	@Override
	public Page<JobDTO> viewAllJobs(Pageable pageable) {
		Page<Job> jobPage = jobRepo.findAll(pageable);
		return jobPage.map(jobMapper::toDTO);
	}

	@Override
	public String applyJob(int jobId, JobApplicationDTO applicationDTO) {
		Job job = jobRepo.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("No job found for jobId : " +jobId));
		Application application = jobMapper.toApplicationEntity(applicationDTO, job);
		applicationRepo.save(application);
		return "Application submitted successfully";
	}

	@Override
	public Page<JobDTO> searchJobs(String skills, String company, String location, Pageable pageable) {
		Page<Job> jobs= jobRepo.searchJobs(skills, company, location, pageable);
		 return jobs.map(jobMapper::toDTO);
	}

	@Override
	public JobApplicationDTO updateStatus(int applicationId, String status) {
		if(!status.equalsIgnoreCase("rejected")&& !status.equalsIgnoreCase("approved")) {
			throw new IllegalArgumentException("Incorrect Status provided. Allowed: Rejected, Approved");
		}
		Application application = applicationRepo.findById(applicationId)
				.orElseThrow(()-> new ResourceNotFoundException("No applicent found with applicationId : "+applicationId));
		application.setStatus(status);
//		applicationRepo.save(application);
		Application saved = applicationRepo.save(application);
		return jobMapper.toApplicationDTO(saved);
	}

	@Override
	public JobDTO viewJob(int jobId) {
		Job job = jobRepo.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("No job found with jobId : "+jobId));
		return jobMapper.toDTO(job);
	}


}
