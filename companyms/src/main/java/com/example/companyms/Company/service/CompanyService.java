package com.example.companyms.Company.service;

import com.example.companyms.Company.entity.Company;


import java.util.List;

public interface CompanyService {

     List<Company> getAllCompanies();

     void createCompany (Company company);
     boolean updateCompany (Long id, Company updatedCompany);
     boolean deleteCompanyById (Long id);
     Company getCompanyById(Long id);
}
