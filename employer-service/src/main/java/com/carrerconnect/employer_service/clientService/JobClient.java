//package com.carrerconnect.employer_service.clientService;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import com.carrerconnect.employer_service.DTO.JobDTO;
//
//
//@FeignClient("JOB-SERVICE")
//public interface JobClient {
//
//	@PostMapping("/job/jobPost")
//	public ResponseEntity<String> postJob(@RequestBody JobDTO job);
//
//	@PutMapping("/job/editJob/{jobId}")
//	public ResponseEntity<JobDTO> editJob(@PathVariable("jobId") int jobId, @RequestBody JobDTO job);
//
//	@DeleteMapping("/job/deleteJob/{jobId}")
//	public ResponseEntity<String> deleteJob(@PathVariable("jobId") int jobId);
//}
