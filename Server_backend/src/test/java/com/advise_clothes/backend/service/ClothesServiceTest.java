package com.advise_clothes.backend.service;

import com.advise_clothes.backend.ServerBackendApplicationTests;
import com.advise_clothes.backend.domain.entity.Clothes;
import com.advise_clothes.backend.exception.ClothesNotFound;
import com.advise_clothes.backend.exception.InvalidRequest;
import com.advise_clothes.backend.request.AdviseRequest;
import com.advise_clothes.backend.request.ClothesCreate;
import com.advise_clothes.backend.domain.entity.Company;
import com.advise_clothes.backend.repository.ClothesRepository;
import com.advise_clothes.backend.repository.CompanyRepository;
import com.advise_clothes.backend.request.ClothesEdit;
import com.advise_clothes.backend.response.ClothesResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.advise_clothes.backend.domain.entity.Clothes.ClothesPartEnum.TOP;
import static org.junit.jupiter.api.Assertions.*;

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
        clothesRepository.findByName("반팔반팔티").ifPresent(clothesRepository::delete);
    }

    @Test
    @DisplayName("옷 생성")
    @Transactional
    void create() {
        // given
        ClothesCreate clothesCreate = ClothesCreate.builder()
                .name("반팔반팔티")
                .company(company)
                .part(TOP)
                .build();

        // when
        clothesService.create(clothesCreate);
        Clothes clothes = clothesRepository.findByName("반팔반팔티")
                .orElseThrow(ClothesNotFound::new);

        // then
        assertEquals("반팔반팔티", clothes.getName());
    }

    @Test
    @DisplayName("옷 생성 시 회사 값 필수")
    @Transactional
    void createCompanyNull() {
        // given
        ClothesCreate clothesCreate = ClothesCreate.builder()
                .name("반팔반팔티")
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
                .name("반팔반팔티")
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
                .name("반팔반팔티")
                .company(company)
                .part(TOP)
                .build();

        // when
        clothesService.create(clothesCreate);
        Clothes clothes = clothesRepository.findByName("반팔반팔티")
                .orElseThrow(ClothesNotFound::new);

        // then
        assertEquals("반팔반팔티", clothes.getName());
        assertEquals("AdviseClothes", clothes.getCompany().getName());
        assertEquals("AdviseClothes", clothes.getCreatedBy());
    }



    @Test
    @DisplayName("옷 조회")
    @Transactional
    void read() {
        // given
        Clothes clothes = clothesRepository.save(Clothes.builder()
                .name("반팔반팔티")
                .company(company)
                .createdBy("JUnit5")
                .part(TOP)
                .build()
        );

        // when
        ClothesResponse clothesResponse = clothesService.get(clothes.getId());

        // then
        assertEquals("반팔반팔티", clothesResponse.getName());
        assertEquals(company.getName(), clothesResponse.getCompany().getName());
    }

    @Test
    @DisplayName("옷 이름 수정")
    @Transactional
    void edit() {
        // given
        Clothes clothes = clothesRepository.save(Clothes.builder()
                .name("반팔반팔티")
                .company(company)
                .createdBy("JUnit5")
                .part(TOP)
                .build()
        );

        ClothesEdit clothesEdit = ClothesEdit.builder()
                .name("반팔반팔티2")
                .build();

        // when
        clothesService.edit(clothes.getId(), clothesEdit);
        Clothes editClothes = clothesRepository.findById(clothes.getId())
                .orElseThrow(ClothesNotFound::new);

        // then
        assertEquals("반팔반팔티2", editClothes.getName());
    }

    @Test
    @DisplayName("옷 수정 시 바꾼 사람 입력하지 않으면 회사 이름으로 저장")
    @Transactional
    void editUpdatedByNull() {
        // given
        Clothes clothes = clothesRepository.save(Clothes.builder()
                .name("반팔반팔티")
                .company(company)
                .createdBy("JUnit5")
                .part(TOP)
                .build()
        );

        ClothesEdit clothesEdit = ClothesEdit.builder()
                .name("반팔반팔티2")
                .build();

        // when
        clothesService.edit(clothes.getId(), clothesEdit);
        Clothes editClothes = clothesRepository.findById(clothes.getId())
                .orElseThrow(ClothesNotFound::new);

        // then
        assertEquals("반팔반팔티2", editClothes.getName());
        assertEquals("AdviseClothes", editClothes.getCompany().getName());
        assertEquals("AdviseClothes", editClothes.getUpdatedBy());
    }

    @Test
    @DisplayName("옷 삭제")
    @Transactional
    void delete() {
        // given
        Clothes clothes = clothesRepository.save(Clothes.builder()
                .name("반팔반팔티")
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

    @Test
    @DisplayName("옷 추천, 기온이 28도 이상")
    void adviseTemperature28() {
        // given
        AdviseRequest adviseRequest = AdviseRequest.builder()
                .temperature(28)
                .weather("sun")
                .build();

        // when
        List<ClothesResponse> clothesResponses = clothesService.advice(adviseRequest);


        // then
        List<String> responseWithCheck = clothesResponses.stream()
                .map(ClothesResponse::getName)
                .collect(Collectors.toList());

        assertEquals(5, clothesResponses.size());
        assertTrue(responseWithCheck.contains("민소매"));
        assertTrue(responseWithCheck.contains("반팔티"));
        assertTrue(responseWithCheck.contains("린넨셔츠"));
        assertTrue(responseWithCheck.contains("반바지"));
        assertTrue(responseWithCheck.contains("짧은치마"));
    }

    @Test
    @DisplayName("옷 추천, 기온 23 ~ 27도")
    void adviseTemperature23() {
        // given
        AdviseRequest adviseRequest = AdviseRequest.builder()
                .temperature(23)
                .weather("sun")
                .build();

        // when
        List<ClothesResponse> clothesResponses = clothesService.advice(adviseRequest);


        // then
        List<String> responseWithCheck = clothesResponses.stream()
                .map(ClothesResponse::getName)
                .collect(Collectors.toList());
        System.out.println(responseWithCheck);

        assertEquals(4, clothesResponses.size());
        assertTrue(responseWithCheck.contains("반팔티"));
        assertTrue(responseWithCheck.contains("얇은셔츠"));
        assertTrue(responseWithCheck.contains("반바지"));
        assertTrue(responseWithCheck.contains("면바지"));
    }

    @Test
    @DisplayName("옷 추천, 기온 20 ~ 22도")
    void adviseTemperature20() {
        // given
        AdviseRequest adviseRequest = AdviseRequest.builder()
                .temperature(20)
                .weather("sun")
                .build();

        // when
        List<ClothesResponse> clothesResponses = clothesService.advice(adviseRequest);


        // then
        List<String> responseWithCheck = clothesResponses.stream()
                .map(ClothesResponse::getName)
                .collect(Collectors.toList());

        assertEquals(4, clothesResponses.size());
        assertTrue(responseWithCheck.contains("블라우스"));
        assertTrue(responseWithCheck.contains("긴팔티"));
        assertTrue(responseWithCheck.contains("면바지"));
        assertTrue(responseWithCheck.contains("슬랙스"));
    }

    @Test
    @DisplayName("옷 추천, 기온 17 ~ 19도")
    void adviseTemperature17() {
        // given
        AdviseRequest adviseRequest = AdviseRequest.builder()
                .temperature(17)
                .weather("sun")
                .build();

        // when
        List<ClothesResponse> clothesResponses = clothesService.advice(adviseRequest);



        // then
        List<String> responseWithCheck = clothesResponses.stream()
                .map(ClothesResponse::getName)
                .collect(Collectors.toList());

        List<String> checkList = List.of("니트티", "맨투맨", "후드", "긴바지", "얇은가디건");

        assertEquals(5, clothesResponses.size());
        assertTrue(responseWithCheck.containsAll(checkList));
    }

    @Test
    @DisplayName("옷 추천, 기온 12 ~ 16도")
    void adviseTemperature12() {
        // given
        AdviseRequest adviseRequest = AdviseRequest.builder()
                .temperature(12)
                .weather("sun")
                .build();

        // when
        List<ClothesResponse> clothesResponses = clothesService.advice(adviseRequest);


        // then
        List<String> responseWithCheck = clothesResponses.stream()
                .map(ClothesResponse::getName)
                .collect(Collectors.toList());

        assertEquals(7, clothesResponses.size());
        assertTrue(responseWithCheck.contains("니트티"));
        assertTrue(responseWithCheck.contains("맨투맨"));
        assertTrue(responseWithCheck.contains("후드"));
        assertTrue(responseWithCheck.contains("청바지"));
        assertTrue(responseWithCheck.contains("자켓"));
        assertTrue(responseWithCheck.contains("가디건"));
        assertTrue(responseWithCheck.contains("청자켓"));
    }

    @Test
    @DisplayName("옷 추천, 기온 9 ~ 11도")
    void adviseTemperature9() {
        // given
        AdviseRequest adviseRequest = AdviseRequest.builder()
                .temperature(9)
                .weather("sun")
                .build();

        // when
        List<ClothesResponse> clothesResponses = clothesService.advice(adviseRequest);


        // then
        List<String> responseWithCheck = clothesResponses.stream()
                .map(ClothesResponse::getName)
                .collect(Collectors.toList());

        List<String> checkList = List.of("니트티", "맨투맨", "후드", "기모바지", "트렌치코트", "야상", "점퍼");

        System.out.println(checkList);
        System.out.println(responseWithCheck);

        assertEquals(7, clothesResponses.size());
        assertTrue(responseWithCheck.containsAll(checkList));
    }

    @Test
    @DisplayName("옷 추천, 기온 5 ~ 8도")
    void adviseTemperature5() {
        // given
        AdviseRequest adviseRequest = AdviseRequest.builder()
                .temperature(5)
                .weather("sun")
                .build();

        // when
        List<ClothesResponse> clothesResponses = clothesService.advice(adviseRequest);

        // then
        List<String> responseWithCheck = clothesResponses.stream()
                .map(ClothesResponse::getName)
                .collect(Collectors.toList());

        List<String> checkList = List.of("니트티", "맨투맨", "후드", "기모티", "기모바지", "울코트");

        assertEquals(6, clothesResponses.size());
        assertTrue(responseWithCheck.containsAll(checkList));
    }

    @Test
    @DisplayName("옷 추천, 기온 4도 이하")
    void adviseTemperature4() {
        // given
        AdviseRequest adviseRequest = AdviseRequest.builder()
                .temperature(4)
                .weather("sun")
                .build();

        // when
        List<ClothesResponse> clothesResponses = clothesService.advice(adviseRequest);


        // then
        List<String> responseWithCheck = clothesResponses.stream()
                .map(ClothesResponse::getName)
                .collect(Collectors.toList());

        List<String> checkList = List.of("니트티", "맨투맨", "후드", "기모티", "기모바지", "두꺼운코트", "패딩");

        System.out.println(responseWithCheck);
        System.out.println(checkList);

        assertEquals(7, clothesResponses.size());
        assertTrue(responseWithCheck.containsAll(checkList));
    }



}