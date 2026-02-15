package com.carrerconnect.job_service.mapper;

import com.carrerconnect.job_service.dto.JobApplicationDTO;
import com.carrerconnect.job_service.dto.JobDTO;
import com.carrerconnect.job_service.entity.Application;
import com.carrerconnect.job_service.entity.Job;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class JobMapper {

    public Job toEntity(JobDTO dto){
        Job job = new Job();
        job.setJobTitle(dto.getJobTitle());
        job.setLocation(dto.getLocation());
        job.setDescription(dto.getDescription());
        job.setExperience(dto.getExperience());
        job.setSalary(dto.getSalary());
        job.setNoticePeriod(dto.getNoticePeriod());
        job.setContactEmail(dto.getContactEmail());
        job.setStatus(dto.getStatus());
        job.setApplications(Collections.emptyList());
        return job;
    }

    public JobDTO toDTO(Job job){
        JobDTO  dto = new JobDTO();
        dto.setJobId(job.getJobId());
        dto.setJobTitle(job.getJobTitle());
        dto.setLocation(job.getLocation());
        dto.setDescription(job.getDescription());
        dto.setExperience(job.getExperience());
        dto.setSalary(job.getSalary());
        dto.setNoticePeriod(job.getNoticePeriod());
        dto.setContactEmail(job.getContactEmail());
        dto.setStatus(job.getStatus());
        return dto;
    }

    public Application toApplicationEntity(JobApplicationDTO dto, Job job) {
        Application application = new Application();
        application.setApplicantEmail(dto.getApplicantEmail());
        application.setApplicantName(dto.getApplicantName());
        application.setJob(job);
        return application;
    }

    public JobApplicationDTO toApplicationDTO(Application application) {
        JobApplicationDTO dto = new JobApplicationDTO();
        dto.setApplicantName(application.getApplicantName());
        dto.setApplicantEmail(application.getApplicantEmail());
        return dto;
    }

    public void updateEntity(Job job, JobDTO dto) {
        job.setJobTitle(dto.getJobTitle());
        job.setLocation(dto.getLocation());
        job.setDescription(dto.getDescription());
        job.setExperience(dto.getExperience());
        job.setSalary(dto.getSalary());
        job.setNoticePeriod(dto.getNoticePeriod());
        job.setContactEmail(dto.getContactEmail());
        job.setStatus(dto.getStatus());
    }


}
