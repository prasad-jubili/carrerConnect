package com.carrerconnect.job_service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carrerconnect.job_service.model.Job;

@Repository
public interface JobRepo extends JpaRepository<Job, Integer> {

    // List<Job> findAllById(int empId);

    // Search by skill, company, and location (Optional Filters)
    @Query(value = "SELECT * FROM job j WHERE "
            + "(:skill IS NULL OR j.skills_required ILIKE CONCAT('%', :skill, '%')) AND "
            + "(:company IS NULL OR j.company ILIKE CONCAT('%', :company, '%')) AND "
            + "(:location IS NULL OR j.location ILIKE CONCAT('%', :location, '%'))", nativeQuery = true)
    List<Job> searchJobs(@Param("skill") String skills,
            @Param("company") String company,
            @Param("location") String location);

    boolean existsByEmployerId(int employerId);

    List<Job> findAllByEmployerId(int employerId);

}
