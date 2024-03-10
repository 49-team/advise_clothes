package com.advise_clothes.backend.company.repository;

import com.advise_clothes.backend.ServerBackendApplicationTests;
import com.advise_clothes.backend.company.entity.Company;
import com.advise_clothes.backend.company.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

class CompanyRepositoryTest extends ServerBackendApplicationTests {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    @Transactional
    void create() {
        Company company = Company.builder()
                .name("testCompany")
                .createdBy("임리을")
                .build();

        companyRepository.save(company);
    }

    @Test
    @Transactional
    void read() {
        Company company = companyRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Not found Company"));

        System.out.println(company);
    }
}