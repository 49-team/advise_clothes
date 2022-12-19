package com.advise_clothes.backend.repository;

import com.advise_clothes.backend.ServerBackendApplicationTests;
import com.advise_clothes.backend.domain.entity.Company;
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
                .name("AdviseClothes")
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