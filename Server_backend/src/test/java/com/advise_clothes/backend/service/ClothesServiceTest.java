package com.advise_clothes.backend.service;

import com.advise_clothes.backend.ServerBackendApplicationTests;
import com.advise_clothes.backend.domain.entity.Clothes;
import com.advise_clothes.backend.exception.ClothesNotFound;
import com.advise_clothes.backend.exception.InvalidRequest;
import com.advise_clothes.backend.request.ClothesCreate;
import com.advise_clothes.backend.domain.entity.Company;
import com.advise_clothes.backend.repository.ClothesRepository;
import com.advise_clothes.backend.repository.CompanyRepository;
import com.advise_clothes.backend.request.ClothesEdit;
import com.advise_clothes.backend.response.ClothesResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static com.advise_clothes.backend.domain.entity.Clothes.ClothesPartEnum.TOP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClothesServiceTest extends ServerBackendApplicationTests {

    @Autowired
    private ClothesRepository clothesRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ClothesService clothesService;

    private Company company;

    @BeforeEach
    void getCompany() {
        company = companyRepository.findById(1L)
                .orElseThrow(ClothesNotFound::new);
    }

    @AfterEach
    void deleteClothes() {
        clothesRepository.findByName("반팔티").ifPresent(clothesRepository::delete);
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
                .orElseThrow(ClothesNotFound::new);

        // then
        assertEquals("반팔티", clothes.getName());
    }

    @Test
    @DisplayName("옷 생성 시 회사 값 필수")
    @Transactional
    void createCompanyNull() {
        // given
        ClothesCreate clothesCreate = ClothesCreate.builder()
                .name("반팔티")
                .company(null)
                .part(TOP)
                .build();

        // when
        assertThrows(InvalidRequest.class, () -> clothesService.create(clothesCreate));
    }

    @Test
    @DisplayName("옷 생성 시 옷 종류 값 필수")
    @Transactional
    void createPartNull() {
        // given
        ClothesCreate clothesCreate = ClothesCreate.builder()
                .name("반팔티")
                .company(company)
                .part(null)
                .build();

        // when
        assertThrows(InvalidRequest.class, () -> clothesService.create(clothesCreate));
    }

    @Test
    @DisplayName("옷 생성 시 만든 사람 입력하지 않으면 회사 이름으로 저장")
    @Transactional
    void createCreatedByNull() {
        // given
        ClothesCreate clothesCreate = ClothesCreate.builder()
                .name("반팔티")
                .company(company)
                .part(TOP)
                .build();

        // when
        clothesService.create(clothesCreate);
        Clothes clothes = clothesRepository.findByName("반팔티")
                .orElseThrow(ClothesNotFound::new);

        // then
        assertEquals("반팔티", clothes.getName());
        assertEquals("AdviseClothes", clothes.getCompany().getName());
        assertEquals("AdviseClothes", clothes.getCreatedBy());
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

    @Test
    @DisplayName("옷 이름 수정")
    @Transactional
    void edit() {
        // given
        Clothes clothes = clothesRepository.save(Clothes.builder()
                .name("반팔티")
                .company(company)
                .createdBy("JUnit5")
                .part(TOP)
                .build()
        );

        ClothesEdit clothesEdit = ClothesEdit.builder()
                .name("반팔티2")
                .build();

        // when
        clothesService.edit(clothes.getId(), clothesEdit);
        Clothes editClothes = clothesRepository.findById(clothes.getId())
                .orElseThrow(ClothesNotFound::new);

        // then
        assertEquals("반팔티2", editClothes.getName());
    }

    @Test
    @DisplayName("옷 수정 시 바꾼 사람 입력하지 않으면 회사 이름으로 저장")
    @Transactional
    void editUpdatedByNull() {
        // given
        Clothes clothes = clothesRepository.save(Clothes.builder()
                .name("반팔티")
                .company(company)
                .createdBy("JUnit5")
                .part(TOP)
                .build()
        );

        ClothesEdit clothesEdit = ClothesEdit.builder()
                .name("반팔티2")
                .build();

        // when
        clothesService.edit(clothes.getId(), clothesEdit);
        Clothes editClothes = clothesRepository.findById(clothes.getId())
                .orElseThrow(ClothesNotFound::new);

        // then
        assertEquals("반팔티2", editClothes.getName());
        assertEquals("AdviseClothes", editClothes.getCompany().getName());
        assertEquals("AdviseClothes", editClothes.getUpdatedBy());
    }

    @Test
    @DisplayName("옷 삭제")
    @Transactional
    void delete() {
        // given
        Clothes clothes = clothesRepository.save(Clothes.builder()
                .name("반팔티")
                .company(company)
                .createdBy("JUnit5")
                .part(TOP)
                .build()
        );

        // when
        clothesService.delete(clothes.getId());

        // then
        assertThrows(RuntimeException.class, () -> clothesRepository.findById(clothes.getId())
                .orElseThrow(ClothesNotFound::new));
    }
}