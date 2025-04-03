package com.carrerconnect.jobseeker_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrerconnect.jobseeker_service.model.JobSeeker;

@Repository
public interface JobSeekerRepo extends JpaRepository<JobSeeker, Integer>{

}
