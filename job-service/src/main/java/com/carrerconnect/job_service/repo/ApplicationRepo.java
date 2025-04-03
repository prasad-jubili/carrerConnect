package com.carrerconnect.job_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrerconnect.job_service.model.Application;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Integer>{

}
