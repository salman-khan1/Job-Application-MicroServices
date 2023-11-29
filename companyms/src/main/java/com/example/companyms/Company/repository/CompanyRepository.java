package com.example.companyms.Company.repository;

import com.example.companyms.Company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
