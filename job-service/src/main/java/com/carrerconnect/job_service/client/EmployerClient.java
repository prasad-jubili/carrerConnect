package com.carrerconnect.job_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.carrerconnect.job_service.dto.EmployerResponseDTO;

@FeignClient(name = "EMPLOYER-SERVICE")
public interface EmployerClient {

    @GetMapping("/employers/{id}")
    EmployerResponseDTO getEmployerById(@PathVariable int id);
}
