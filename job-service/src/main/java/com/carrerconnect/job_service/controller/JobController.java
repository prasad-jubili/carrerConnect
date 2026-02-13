package com.carrerconnect.job_service.controller;

import java.util.List;

import com.carrerconnect.job_service.service.JobService;
import jakarta.validation.Valid;
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


@RestController
@RequestMapping("/job")
public class JobController {
	
//	@Autowired
//	JobServiceImpl jobServiceImpl;

	private final JobService jobService;

	public JobController(JobService jobService){

        this.jobService = jobService;
    }

	private static final Logger log = LoggerFactory.getLogger(JobController.class);
	
	@PostMapping("/jobPost")
	public ResponseEntity<String> postJob(
			@Valid @RequestBody JobDTO jobDTO){

		log.info("Request received to post job with title: {}", jobDTO.getJobTitle());

		String response = jobService.postJob(jobDTO);

		log.info("Job posted successfully");
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/editJob/{jobId}")
	public ResponseEntity<JobDTO> editJob(@PathVariable int jobId,
										  @Valid @RequestBody JobDTO jobDto){
		log.info("Request received to edit job with jobId : {}", jobId);
		JobDTO dto = jobService.editJob(jobId,jobDto);
		log.info("Job post updated successfully");
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping("/deleteJob/{jobId}")
	public ResponseEntity<String> deleteJob(@PathVariable int jobId){
		log.warn("Deleting job with jobId : {}", jobId);
		String response = jobService.deleteJob(jobId);
		log.info("Job Post deleted successfully");
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/allJobsByEmp/{empId}")
	public ResponseEntity<List<JobDTO>> viewAllJobsByEmp(@PathVariable int empId){
		log.debug("Fetching jobs with emp Id: {}",
				empId);

		List<JobDTO> dtos = jobService.viewAllJobsByEmp(empId);
		log.info("Job fetched successfully for emp Id: {}", empId);
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/allJobs")
	public ResponseEntity<List<JobDTO>> viewAllJobs(){
		log.debug("Fetching all the jobs posted");

		List<JobDTO> dtos = jobService.viewAllJobs();
		log.info("Jobs fetched successfully");
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/Job/{jobId}")
	public ResponseEntity<JobDTO> viewJob(@PathVariable int jobId){
		log.debug("Fetching Job with Id: {}",jobId);
		JobDTO jobdto = jobService.viewJob(jobId);
		log.info("Job fetched successfully for Id: {}", jobId);
		return ResponseEntity.ok(jobdto);
	}

	@PostMapping("/{jobId}/apply")
	public ResponseEntity<String> applyJob
	(@PathVariable int jobId,
	 @Valid @RequestBody JobApplicationDTO applicationDTO) {
		log.info("Applying for a Job with jobId: {}",jobId);
		String response = jobService.applyJob(jobId, applicationDTO);
		log.info("Applied for posted job");
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<JobDTO>> searchJobs(@RequestParam(required=false) String skills,
			@RequestParam(required = false) String company,
			@RequestParam(required = false) String location) {
		log.debug("Fetching jobs for given params");
		List<JobDTO> jobDTOs = jobService.searchJobs(skills, company, location);
		log.info("Successfully fetched the posted job based on skills, company, location");

		return ResponseEntity.ok(jobDTOs);
	}
	
	@PutMapping("/updateStatus/{applicationId}")
	public ResponseEntity<JobApplicationDTO> updateStatus(@PathVariable int applicationId, @RequestParam("status") String status){
		log.info("Updating status for application id: {}",applicationId);
		JobApplicationDTO jobApplicationDTO = jobService.updateStatus(applicationId, status);
		log.info("updated status successfully for application id: {}" , applicationId);
		return ResponseEntity.ok(jobApplicationDTO);
	}
	
}