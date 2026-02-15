package com.carrerconnect.job_service.controller;

import java.util.List;

import com.carrerconnect.job_service.service.JobService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrerconnect.job_service.dto.JobApplicationDTO;
import com.carrerconnect.job_service.dto.JobDTO;

import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Job APIs", description = "Operations related to job postings")
@RestController
@RequestMapping("/jobs")
public class JobController {
	
//	@Autowired
//	JobServiceImpl jobServiceImpl;

	private final JobService jobService;

	public JobController(JobService jobService){

        this.jobService = jobService;
    }

	private static final Logger log = LoggerFactory.getLogger(JobController.class);

	@Operation(summary = "Post a new job",
			description = "Creates a new job posting in the system")
	@PostMapping
	public ResponseEntity<String> postJob(
			@Valid @RequestBody JobDTO jobDTO){

		log.info("Request received to post job with title: {}", jobDTO.getJobTitle());

		String response = jobService.postJob(jobDTO);

		log.info("Job posted successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@Operation(summary = "Edit a existing job",
			description = "Updating a job posting in the system")
	@PutMapping("/{jobId}")
	public ResponseEntity<JobDTO> editJob(@PathVariable int jobId,
										  @Valid @RequestBody JobDTO jobDto){
		log.info("Request received to edit job with jobId : {}", jobId);
		JobDTO dto = jobService.editJob(jobId,jobDto);
		log.info("Job post updated successfully");
		return ResponseEntity.ok(dto);
	}

	@Operation(summary = "Delete a job",
			description = "Deleting a job posting in the system")
	@DeleteMapping("/{jobId}")
	public ResponseEntity<String> deleteJob(@PathVariable int jobId){
		log.warn("Deleting job with jobId : {}", jobId);
		String response = jobService.deleteJob(jobId);
		log.info("Job Post deleted successfully");
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Fetching Jobs under Employee profile",
			description = "Fetching all the jobs applied by an employee in the system")
	@GetMapping("/jobs?employerId=10")
	public ResponseEntity<List<JobDTO>> viewAllJobsByEmp(@PathVariable int employerId){
		log.debug("Fetching jobs with emp Id: {}",
				employerId);

		List<JobDTO> dtos = jobService.viewAllJobsByEmp(employerId);
		log.info("Job fetched successfully for emp Id: {}", employerId);
		return ResponseEntity.ok(dtos);
	}

	@Operation(summary = "Listing all jobs in DB using Paging",
			description = "Listing all the jobs with given paging details")
	@GetMapping
	public ResponseEntity<Page<JobDTO>> viewAllJobs(
			@PageableDefault(size = 5,sort = "jobId")Pageable pageable ){
		log.debug("Fetching jobs with pagination - page: {}, size: {}",
				pageable.getPageNumber(),
				pageable.getPageSize());

		Page<JobDTO> dtos = jobService.viewAllJobs(pageable);
		log.info("Jobs fetched successfully with pagination");
		return ResponseEntity.ok(dtos);
	}

	@Operation(summary = "Fetching a specific job",
			description = "Fetching a specific job posting with Id in the system")
	@GetMapping("/{jobId}")
	public ResponseEntity<JobDTO> viewJob(@PathVariable int jobId){
		log.debug("Fetching Job with Id: {}",jobId);
		JobDTO jobdto = jobService.viewJob(jobId);
		log.info("Job fetched successfully for Id: {}", jobId);
		return ResponseEntity.ok(jobdto);
	}


	@PostMapping("/{jobId}/applications")
	public ResponseEntity<String> applyJob
	(@PathVariable int jobId,
	 @Valid @RequestBody JobApplicationDTO applicationDTO) {
		log.info("Applying for a Job with jobId: {}",jobId);
		String response = jobService.applyJob(jobId, applicationDTO);
		log.info("Applied for posted job");
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Searching jobs with pagination")
	@GetMapping("/search")
	public ResponseEntity<Page<JobDTO>> searchJobs(
			@RequestParam(required=false) String skills,
			@RequestParam(required = false) String company,
			@RequestParam(required = false) String location,
			@PageableDefault(size = 5) Pageable pageable) {
		log.debug("Searching jobs with filters and pagination - page: {}, size: {}",
				pageable.getPageNumber(),
				pageable.getPageSize());
		Page<JobDTO> jobDTOs = jobService.searchJobs(skills, company, location,pageable);
		log.info("Successfully fetched the posted job based on skills, company, location");

		return ResponseEntity.ok(jobDTOs);
	}
	
	@PutMapping("/applications/{applicationId}/status")
	public ResponseEntity<JobApplicationDTO> updateStatus(@PathVariable int applicationId, @RequestParam("status") String status){
		log.info("Updating status for application id: {}",applicationId);
		JobApplicationDTO jobApplicationDTO = jobService.updateStatus(applicationId, status);
		log.info("updated status successfully for application id: {}" , applicationId);
		return ResponseEntity.ok(jobApplicationDTO);
	}
	
}