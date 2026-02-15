package com.carrerconnect.employer_service.service;

import com.carrerconnect.employer_service.DTO.EmployerDTO;
import com.carrerconnect.employer_service.exception.ResourceNotFoundException;
import com.carrerconnect.employer_service.mapper.EmployerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.carrerconnect.employer_service.DTO.JobDTO;
//import com.carrerconnect.employer_service.clientService.JobClient;
import com.carrerconnect.employer_service.entity.Employer;
import com.carrerconnect.employer_service.repo.EmployerRepo;

@Service
public class EmployerService {
	
//	@Autowired
//	EmployerRepo employerRepo;
//
//	@Autowired
//	JobClient jobClient;

	private final EmployerRepo employerRepo;
//	private final JobClient jobClient;
	private final EmployerMapper employerMapper;

	public EmployerService(EmployerRepo employerRepo,
						   EmployerMapper employerMapper){
		this.employerRepo = employerRepo;
		this.employerMapper = employerMapper;

	}
	
	public EmployerDTO registration(EmployerDTO dto) {
		Employer employer = employerMapper.toEntity(dto);
		 Employer saved = employerRepo.save(employer);
		 return employerMapper.toDTO(saved);
	}

	public EmployerDTO getEmployer(int id){
		Employer employer = employerRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employer Not Found"));
		return employerMapper.toDTO(employer);
	}

	public EmployerDTO updateEmployer(int id, EmployerDTO dto) {

		Employer employer = employerRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employer Not found"));
		employerMapper.updateEntity(employer, dto);

		Employer updated = employerRepo.save(employer);
		return employerMapper.toDTO(updated);
	}

	public void deleteEmployer(int id){
		Employer employer = employerRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employer not found"));
		employerRepo.delete(employer);
	}





}







//	public String postJob(JobDTO job) {
//		jobClient.postJob(job);
//		return "Job is posted";
//	}
//
//	public JobDTO editPost(int jobId, JobDTO job) {
//		jobClient.editJob(jobId,job);
//		return job;
//	}
//
//	public String deleteJob(int jobId) {
//		jobClient.deleteJob(jobId);
//		return "Job post is deleted";
//	}
