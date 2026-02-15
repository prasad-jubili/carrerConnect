package com.carrerconnect.employer_service.controller;

import com.carrerconnect.employer_service.DTO.EmployerDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.carrerconnect.employer_service.DTO.JobDTO;
import com.carrerconnect.employer_service.entity.Employer;
import com.carrerconnect.employer_service.service.EmployerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/employers")
public class EmployerController {
	
//	@Autowired
//	EmployerService employerService;

	private final EmployerService employerService;

	public EmployerController(EmployerService employerService){
		this.employerService = employerService;
	}
	
	@PostMapping
	public ResponseEntity<EmployerDTO> registration(@Valid @RequestBody EmployerDTO dto){
		log.info("Inside the registration method of EmController");
		log.info("registering employer details");

		EmployerDTO saved = employerService.registration(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployerDTO> getEmployer(@PathVariable int id){
		log.info("Fetching employer details using Id: {}",id);
		EmployerDTO dto = employerService.getEmployer(id);
		log.info("Successfully Fetched employer details using Id: {}",dto.getId());
		return ResponseEntity.status(HttpStatus.FOUND).body(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployerDTO> updateEmployer(@PathVariable int id,
													  @Valid @RequestBody EmployerDTO dto){
		log.info("Requested to update employer details with id :{}",id);
		EmployerDTO updated = employerService.updateEmployer(id, dto);
		log.info("Details updated Successfully for employer id: {}",id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployer(@PathVariable int id) {
		employerService.deleteEmployer(id);
		return ResponseEntity.noContent().build();
	}
	
}


//@PostMapping("/jobPost")
// public ResponseEntity<String> postJob(@RequestBody JobDTO job){
//	log.info("Inside the postJob method of EmController");
//	log.info("Adding the Job");
//	return ResponseEntity.ok(employerService.postJob(job));
//}
//
//@PutMapping("/Jobs/{jobId}")
//public ResponseEntity<JobDTO> editJob(@PathVariable("jobId") int jobId, @RequestBody JobDTO job){
//	log.info("Inside the editJob method of EmController");
//	log.info("editing a job post");
//	return ResponseEntity.ok(employerService.editPost(jobId,job));
//}
//
//@DeleteMapping("/Jobs/{jobId}")
//public ResponseEntity<String> deleteJob(@PathVariable("jobId") int jobId){
//	log.info("inside the deleteJob method of EmController");
//	log.info("deleting a job post");
//	return ResponseEntity.ok(employerService.deleteJob(jobId));
//}
