package com.carrerconnect.employer_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrerconnect.employer_service.DTO.JobDTO;
import com.carrerconnect.employer_service.entity.Employer;
import com.carrerconnect.employer_service.service.EmployerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployerController {
	
	@Autowired
	EmployerService employerService;
	
	@PostMapping("/registration")
	public ResponseEntity<String> registration(@RequestBody Employer employer){
		log.info("Inside the registration method of EmController");
		log.info("registering employer details");
		return ResponseEntity.ok(employerService.registration(employer));
	}
	
	@PostMapping("/jobPost")
	public ResponseEntity<String> postJob(@RequestBody JobDTO job){
		log.info("Inside the postJob method of EmController");
		log.info("Adding the Job");
		return ResponseEntity.ok(employerService.postJob(job));
	}
	
	@PutMapping("/editJob/{jobId}")
	public ResponseEntity<JobDTO> editJob(@PathVariable("jobId") int jobId, @RequestBody JobDTO job){
		log.info("Inside the editJob method of EmController");
		log.info("editing a job post");
		return ResponseEntity.ok(employerService.editPost(jobId,job));
	}
	
	@DeleteMapping("/deleteJob/{jobId}")
	public ResponseEntity<String> deleteJob(@PathVariable("jobId") int jobId){
		log.info("inside the deleteJob method of EmController");
		log.info("deleting a job post");
		return ResponseEntity.ok(employerService.deleteJob(jobId));
	}
	
}
