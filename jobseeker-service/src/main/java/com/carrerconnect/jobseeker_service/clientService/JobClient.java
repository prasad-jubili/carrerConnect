package com.carrerconnect.jobseeker_service.clientService;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.carrerconnect.jobseeker_service.jobSeekerDTO.JobApplicationDTO;
import com.carrerconnect.jobseeker_service.jobSeekerDTO.JobDTO;

@FeignClient("JOB-SERVICE")
public interface JobClient {
	
	@GetMapping("/job/search")
	public List<JobDTO> searchJobs(@RequestParam(required=false) String skills,
			@RequestParam(required = false) String company,
			@RequestParam(required = false) String location);
	
	@GetMapping("/job/Job/{jobId}")
	public ResponseEntity<JobDTO> viewJob(@PathVariable("jobId") int jobId);

	@PostMapping("/job/{jobId}/apply")
	public String applyJob
	(@PathVariable("jobId") int jobId,
			JobApplicationDTO applicationDTO);
	
	
}

