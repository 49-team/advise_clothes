package com.advise_clothes.backend.clothes.repository;

import com.advise_clothes.backend.ServerBackendApplicationTests;
import com.advise_clothes.backend.clothes.entity.Clothes;
import com.advise_clothes.backend.clothes.repository.ClothesRepository;
import com.advise_clothes.backend.company.entity.Company;
import com.advise_clothes.backend.company.repository.CompanyRepository;
import com.advise_clothes.backend.exception.CompanyNotFound;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static com.advise_clothes.backend.clothes.entity.Clothes.ClothesPartEnum.TOP;

class ClothesRepositoryTest extends ServerBackendApplicationTests {

    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    @Transactional
    void create() {
        Company company = companyRepository.findById(1L)
                .orElseThrow(CompanyNotFound::new);

        Clothes clothes = Clothes.builder()
                .name("반팔")
                .company(company)
                .createdBy("ImRieul")
                .part(TOP)
                .build();

        clothesRepository.save(clothes);
    }
}