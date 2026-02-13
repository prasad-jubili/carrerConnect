package com.carrerconnect.job_service.service;

import com.carrerconnect.job_service.dto.JobApplicationDTO;
import com.carrerconnect.job_service.dto.JobDTO;

import java.util.List;

public interface JobService {

    String postJob(JobDTO jobDTO);

    JobDTO editJob(int jobId, JobDTO updatedJob);

    String deleteJob(int jobId);

    List<JobDTO> viewAllJobsByEmp(int empId);

    List<JobDTO> viewAllJobs();

    String applyJob(int jobId, JobApplicationDTO applicationDTO);

    List<JobDTO> searchJobs(String skill, String company, String location);

    JobApplicationDTO updateStatus(int applicationId, String status);

    JobDTO viewJob(int jobId);




}
