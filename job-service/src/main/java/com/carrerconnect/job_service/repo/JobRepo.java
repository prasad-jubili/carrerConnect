package com.carrerconnect.job_service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carrerconnect.job_service.model.Job;

@Repository
public interface JobRepo extends JpaRepository<Job, Integer>{

//	List<Job> findAllById(int empId);

    // Search by skill, company, and location (Optional Filters)
    @Query("SELECT j FROM Job j WHERE "
            + "(:skill IS NULL OR LOWER(j.skillsRequired) LIKE LOWER(CONCAT('%', :skill, '%'))) AND "
            + "(:company IS NULL OR LOWER(j.company) LIKE LOWER(CONCAT('%', :company, '%'))) AND "
            + "(:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%')))")
	List<Job> searchJobs(@Param("skill") String skills, 
			@Param("company")String company,
			@Param("location")String location);

	boolean existsByEmployerId(int employerId);

	List<Job> findAllByEmployerId(int employerId);

}
