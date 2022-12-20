package com.advise_clothes.backend.repository;

import com.advise_clothes.backend.ServerBackendApplicationTests;
import com.advise_clothes.backend.domain.entity.Clothes;
import com.advise_clothes.backend.domain.entity.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static com.advise_clothes.backend.domain.entity.Clothes.ClothesPartEnum.TOP;

class ClothesRepositoryTest extends ServerBackendApplicationTests {

    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    @Transactional
    void create() {
        Company company = companyRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Not found company"));

        Clothes clothes = Clothes.builder()
                .name("반팔")
                .company(company)
                .createdBy("ImRieul")
                .part(TOP)
                .build();

        clothesRepository.save(clothes);
    }

}