package com.carrerconnect.jobseeker_service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrerconnect.jobseeker_service.model.Basket;

@Repository
public interface BasketRepo extends JpaRepository<Basket, Integer>{


	List<Basket> findAllByJobSeekerId(int jobSeekerId);

	Optional<Basket> findByJobSeekerIdAndJobId(int jobSeekerId, int jobId);

}
