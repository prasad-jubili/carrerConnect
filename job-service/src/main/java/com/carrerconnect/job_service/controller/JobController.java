package com.carrerconnect.job_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrerconnect.job_service.DTO.JobApplicationDTO;
import com.carrerconnect.job_service.DTO.JobDTO;
import com.carrerconnect.job_service.model.Application;
import com.carrerconnect.job_service.model.Job;
import com.carrerconnect.job_service.service.JobService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RestController
@RequestMapping("/job")
public class JobController {

	@Autowired
	JobService jobService;

	@PostMapping("/jobPost")
	public ResponseEntity<String> postJob(@RequestBody JobDTO jobDTO) {
		log.info("Inside the postJob in JobController");
		log.info("posting the job");
		return ResponseEntity.ok(jobService.postJob(jobDTO));
	}

	@PutMapping("/editJob/{jobId}")
	public ResponseEntity<Job> editJob(@PathVariable("jobId") int jobId, @RequestBody Job job) {
		log.info("Inside the editJob in JobController");
		log.info("editing the posted job");
		return ResponseEntity.ok(jobService.editJob(jobId, job));
	}

	@DeleteMapping("/deleteJob/{jobId}")
	public ResponseEntity<String> deleteJob(@PathVariable("jobId") int jobId) {
		log.info("Inside the deleteJob in JobController");
		log.info("deleting the posted job");
		return ResponseEntity.ok(jobService.deleteJob(jobId));
	}

	@GetMapping("/allJobsByEmp/{empId}")
	public ResponseEntity<List<Job>> viewAllJobsByEmp(@PathVariable("empId") int empId) {
		log.info("Inside the viewAllJobsByEmp in JobController");
		log.info("viewing all the jobs posted by specific employee");
		return ResponseEntity.ok(jobService.viewAllJobsByEmp(empId));
	}

	@GetMapping("/allJobs")
	public ResponseEntity<List<Job>> viewAllJobs() {
		log.info("Inside the viewAllJobs in JobController");
		log.info("viewing all the posted job");
		return ResponseEntity.ok(jobService.viewAllJobs());
	}

	@GetMapping("/Job/{jobId}")
	public ResponseEntity<JobDTO> viewJob(@PathVariable("jobId") int jobId) {
		log.info("Inside the viewJob method in JobController");
		log.info("viewing the job posted by jobID");
		Job job = jobService.viewJob(jobId);
		JobDTO jobdto = new JobDTO(
				job.getEmployerId(),
				job.getJobTitle(),
				job.getLocation(),
				job.getDescription(),
				job.getExperience(),
				job.getSalary(),
				job.getNoticePeriod(),
				job.getSkillsRequired(),
				job.getCompany(),
				job.getContactEmail(),
				job.getStatus());

		return ResponseEntity.ok(jobdto);
	}

	@PostMapping("/{jobId}/apply")
	public ResponseEntity<String> applyJob(@PathVariable("jobId") int jobId,
			@RequestBody JobApplicationDTO applicationDTO) {
		log.info("Inside the applyJob method in JobController");
		log.info("applying the posted job");
		return ResponseEntity.ok(jobService.applyJob(jobId, applicationDTO));
	}

	@GetMapping("/search")
	public ResponseEntity<List<JobDTO>> searchJobs(@RequestParam(required = false) String skills,
			@RequestParam(required = false) String company,
			@RequestParam(required = false) String location) {
		log.info("Inside the searchJob in JobController");
		log.info("searching the posted job based on skills, company, location");
		try {
			List<JobDTO> jobDTOs = jobService.searchJobs(skills, company, location)
					.stream()
					.map(job -> new JobDTO(
							job.getEmployerId(),
							job.getJobTitle(),
							job.getLocation(),
							job.getDescription(),
							job.getExperience(),
							job.getSalary(),
							job.getNoticePeriod(),
							job.getSkillsRequired(),
							job.getCompany(),
							job.getContactEmail(),
							job.getStatus()))
					.toList();
			return ResponseEntity.ok(jobDTOs);
		} catch (Exception e) {
			log.error("Error searching jobs: ", e);
			throw e;
		}
		// return ResponseEntity.ok(jobs);
	}

	@PutMapping("/updateStatus/{applicationId}")
	public ResponseEntity<Application> updateStatus(@PathVariable("applicationId") int applicationId,
			@RequestParam("status") String status) {
		log.info("Inside the updateStatus in JobController");
		log.info("updating status of a application of the posted job");
		return ResponseEntity.ok(jobService.updateStatus(applicationId, status));
	}

}
