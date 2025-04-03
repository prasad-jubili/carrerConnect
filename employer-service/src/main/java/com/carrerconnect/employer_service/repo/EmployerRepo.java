package com.carrerconnect.employer_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrerconnect.employer_service.entity.Employer;

@Repository
public interface EmployerRepo extends JpaRepository<Employer, Integer>{
	

}
