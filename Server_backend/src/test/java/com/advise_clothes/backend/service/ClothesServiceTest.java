package com.advise_clothes.backend.service;

import com.advise_clothes.backend.ServerBackendApplicationTests;
import com.advise_clothes.backend.domain.entity.Clothes;
import com.advise_clothes.backend.request.ClothesCreate;
import com.advise_clothes.backend.domain.entity.Company;
import com.advise_clothes.backend.repository.ClothesRepository;
import com.advise_clothes.backend.repository.CompanyRepository;
import com.advise_clothes.backend.response.ClothesResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static com.advise_clothes.backend.domain.entity.Clothes.ClothesPartEnum.TOP;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClothesServiceTest extends ServerBackendApplicationTests {

    @Autowired
    private ClothesRepository clothesRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ClothesService clothesService;

//    private final Company company = companyRepository.findById(1L)
//            .orElseThrow(() -> new RuntimeException("회사를 찾을 수 없습니다"));

    private Company company;

    @BeforeEach
    void getCompany() {
        company = companyRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("회사를 찾을 수 없습니다"));
    }

    @Test
    @DisplayName("옷 생성")
    @Transactional
    void create() {
        // given
        ClothesCreate clothesCreate = ClothesCreate.builder()
                .name("반팔티")
                .company(company)
                .part(TOP)
                .build();

        // when
        clothesService.create(clothesCreate);
        Clothes clothes = clothesRepository.findByName("반팔티")
                .orElseThrow(() -> new RuntimeException("옷을 찾을 수 없습니다"));

        // then
        assertEquals("반팔티", clothes.getName());
    }

    @Test
    @DisplayName("옷 조회")
    @Transactional
    void read() {
        // given
        Clothes clothes = clothesRepository.save(Clothes.builder()
                .name("반팔티")
                .company(company)
                .createdBy("JUnit5")
                .part(TOP)
                .build()
        );

        // when
        ClothesResponse clothesResponse = clothesService.get(clothes.getId());

        // then
        assertEquals("반팔티", clothesResponse.getName());
        assertEquals(company.getName(), clothesResponse.getCompany().getName());
    }

}