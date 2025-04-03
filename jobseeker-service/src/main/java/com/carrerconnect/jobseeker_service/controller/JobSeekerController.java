package com.carrerconnect.jobseeker_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrerconnect.jobseeker_service.jobSeekerDTO.BasketDTO;
import com.carrerconnect.jobseeker_service.jobSeekerDTO.JobApplicationDTO;
import com.carrerconnect.jobseeker_service.jobSeekerDTO.JobDTO;
import com.carrerconnect.jobseeker_service.model.Basket;
import com.carrerconnect.jobseeker_service.model.JobSeeker;
import com.carrerconnect.jobseeker_service.servcie.JobSeekerService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/jobseeker")
public class JobSeekerController {
	
	@Autowired
	JobSeekerService jobSeekerService;
	
	
	@PostMapping("/registration")
	public ResponseEntity<String> registration(@RequestBody JobSeeker jobSeeker){
		log.info("Inside the registration method of JSController");
		log.info("registering  details");
		return ResponseEntity.ok(jobSeekerService.registration(jobSeeker));
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<JobDTO>> searchJobs(@RequestParam(required=false) String skills,
			@RequestParam(required = false) String company,
			@RequestParam(required = false) String location) {
		log.info("Inside the search Jobs method of JSController");
		log.info("Searching jobs based on filters for skills,company,location");
		List<JobDTO> jobs = jobSeekerService.searchJobs(skills, company, location);
		return ResponseEntity.ok(jobs);
	}
	
	@PostMapping("/{jobId}/apply")
	public ResponseEntity<String> applyJob
	(@PathVariable("jobId") int jobId,
		@RequestBody JobApplicationDTO applicationDTO) {
		log.info("Inside the applyJob method of JSController");
		log.info("jobSeeker appling for a job");
		return ResponseEntity.ok(jobSeekerService.applyJob(jobId,applicationDTO));
	}
	
	@PostMapping("/basket/add/{jobId}")
	public ResponseEntity<String> addJobToBasket(@PathVariable("jobId") int jobId, 
			@RequestParam("jobSeekerId") int jobSeekerId) {
		log.info("Inside the addJobToBasket method of JSController");
		log.info("adding the job into jobSeeker Basket");
		return ResponseEntity.ok(jobSeekerService.addJobToBasket(jobId,jobSeekerId));
	}
	
	@GetMapping("/basket/view/{jobSeekerId}")
	public ResponseEntity<List<JobDTO>> viewFavJobs(@PathVariable("jobSeekerId") int jobSeekerId) {
	
		log.info("Inside the viewFavJob method of JSController");
		log.info("viewing the jobs present in jobSeeker basket");
		return ResponseEntity.ok(jobSeekerService.viewFavJobs(jobSeekerId));
	}
	
	@DeleteMapping("/basket/delete/{jobId}")
	public ResponseEntity<String> deleteJobFromBasket(@PathVariable("jobId") int jobId, 
			@RequestParam("jobSeekerId") int jobSeekerId) {
		log.info("Inside the deleteJobFromBasket method of JSController");
		log.info("deleting the job present in jobSeeker basket");
		return ResponseEntity.ok(jobSeekerService.deleteJobFromBasket(jobId,jobSeekerId));
	}
	
	
	@PostMapping("/basket/apply/{jobId}")
	public ResponseEntity<String> applyJobFromBasket(@PathVariable int jobId, 
			@RequestParam int jobSeekerId,@RequestBody JobApplicationDTO jobAppDTO) {
		log.info("Inside the applyJobFromBasket method of JSController");
		log.info("applying the jobs present in jobSeeker basket");
	    return ResponseEntity.ok(jobSeekerService.applyJobFromBasket(jobId,jobSeekerId,jobAppDTO));
	}
	
}
