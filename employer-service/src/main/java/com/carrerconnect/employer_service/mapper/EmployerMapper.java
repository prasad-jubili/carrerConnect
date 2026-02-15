package com.carrerconnect.employer_service.mapper;

import com.carrerconnect.employer_service.DTO.EmployerDTO;
import com.carrerconnect.employer_service.entity.Employer;
import org.springframework.stereotype.Component;

@Component
public class EmployerMapper {

    public Employer toEntity(EmployerDTO dto){
        Employer employer = new Employer();
        employer.setId(dto.getId());
        employer.setOrganizationName(dto.getOrganizationName());
        employer.setAddress(dto.getAddress());
        employer.setContactNumber(dto.getContactNumber());
        employer.setEmail(dto.getEmail());
        return employer;
    }

    public EmployerDTO toDTO(Employer employer) {
        return new EmployerDTO(
                employer.getId(),
                employer.getOrganizationName(),
                employer.getAddress(),
                employer.getContactNumber(),
                employer.getEmail()
        );
    }

    public void updateEntity(Employer employer, EmployerDTO dto) {
        employer.setOrganizationName(dto.getOrganizationName());
        employer.setAddress(dto.getAddress());
        employer.setContactNumber(dto.getContactNumber());
        employer.setEmail(dto.getEmail());
    }
}
