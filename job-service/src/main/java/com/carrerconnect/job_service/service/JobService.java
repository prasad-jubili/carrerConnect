package com.carrerconnect.job_service.service;

import com.carrerconnect.job_service.dto.JobApplicationDTO;
import com.carrerconnect.job_service.dto.JobDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobService {

    String postJob(JobDTO jobDTO);

    JobDTO editJob(int jobId, JobDTO updatedJob);

    String deleteJob(int jobId);

    List<JobDTO> viewAllJobsByEmp(int empId);

    Page<JobDTO> viewAllJobs(Pageable pageable);

    String applyJob(int jobId, JobApplicationDTO applicationDTO);

    Page<JobDTO> searchJobs(String skill, String company, String location, Pageable pageable);

    JobApplicationDTO updateStatus(int applicationId, String status);

    JobDTO viewJob(int jobId);




}
