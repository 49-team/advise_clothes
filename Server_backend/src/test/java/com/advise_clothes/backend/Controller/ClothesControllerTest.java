package com.advise_clothes.backend.Controller;

import com.advise_clothes.backend.ServerBackendApplicationTests;
import com.advise_clothes.backend.domain.entity.Clothes;
import com.advise_clothes.backend.domain.entity.Company;
import com.advise_clothes.backend.exception.ClothesNotFound;
import com.advise_clothes.backend.repository.ClothesRepository;
import com.advise_clothes.backend.repository.CompanyRepository;
import com.advise_clothes.backend.request.ClothesCreate;
import com.advise_clothes.backend.request.ClothesEdit;
import com.advise_clothes.backend.service.ClothesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static com.advise_clothes.backend.domain.entity.Clothes.ClothesPartEnum.TOP;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
class ClothesControllerTest extends ServerBackendApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClothesRepository clothesRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private Company company;

    @BeforeEach
    void getCompany() {
        company = companyRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("회사를 찾을 수 없습니다"));
    }

    @Test
    @DisplayName("/api/clothes POST 요청 시 옷 생성")
    @Transactional
    void create() throws Exception {
        // given
        ClothesCreate request = ClothesCreate.builder()
                .name("반팔티")
                .company(company)
                .part(TOP)
                .build();

        String json = objectMapper.writeValueAsString(request);

        // excepted
        mockMvc.perform(post("/api/clothes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("/api/clothes POST 요청 시 옷 이름 필수")
    @Transactional
    void createNeedName() throws Exception {
        // given
        ClothesCreate request = ClothesCreate.builder()
                .company(company)
                .part(TOP)
                .build();

        String json = objectMapper.writeValueAsString(request);

        // excepted
        mockMvc.perform(post("/api/clothes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.fieldName").value("name"))
                .andExpect(jsonPath("$.validation.message").value("옷 이름을 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("/api/clothes POST 요청 시 옷 만든 회사 필수")
    @Transactional
    void postNeedCompany() throws Exception {
        // given
        ClothesCreate request = ClothesCreate.builder()
                .name("반팔티")
                .part(TOP)
                .build();

        String json = objectMapper.writeValueAsString(request);

        // excepted
        mockMvc.perform(post("/api/clothes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.fieldName").value("company"))
                .andExpect(jsonPath("$.validation.message").value("회사를 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("/api/clothes POST 요청 시 옷 종류 필수")
    @Transactional
    void postNeedPart() throws Exception {
        // given
        ClothesCreate request = ClothesCreate.builder()
                .name("반팔티")
                .company(company)
                .build();

        String json = objectMapper.writeValueAsString(request);

        // excepted
        mockMvc.perform(post("/api/clothes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.fieldName").value("part"))
                .andExpect(jsonPath("$.validation.message").value("옷 종류를 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("/api/clothes/{clothesId} GET 요청 시 ClothesResponse 반환")
    void getClothes() throws Exception {
        // given
        Clothes clothes = clothesRepository.save(Clothes.builder()
                .name("반팔티")
                .company(company)
                .createdBy("JUnit5")
                .part(TOP)
                .build()
        );

        // excepted
        mockMvc.perform(get("/api/clothes/{clothesId}", clothes.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("반팔티"))
                .andExpect(jsonPath("$.part").value("TOP"))
                .andExpect(jsonPath("$.company.id").value(company.getId()))
                .andDo(print());
    }

    @Test
    @DisplayName("/api/clothes/{clothesId} PUT 요청 시 옷 정보 수정")
    @Transactional
    void editClothes() throws Exception {
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
                .company(company)
                .part(TOP)
                .build();

        String json = objectMapper.writeValueAsString(clothesEdit);

        // when
        mockMvc.perform(put("/api/clothes/{clothesId}", clothes.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());

        // then
        Clothes editClothes = clothesRepository.findById(clothes.getId())
                .orElseThrow(ClothesNotFound::new);

        Assertions.assertEquals("반팔티2", editClothes.getName());
    }

    @Test
    @DisplayName("/api/clothes/{clothesId} PUT 요청 시 updatedBy가 없으면 회사 이름 입력")
    @Transactional
    void editClothesWithoutUpdatedBy() throws Exception {
        // given
        Clothes clothes = clothesRepository.save(Clothes.builder()
                .name("반팔티")
                .company(company)
                .createdBy("JUnit5")
                .part(TOP)
                .build()
        );

        ClothesEdit clothesEdit = ClothesEdit.builder()
                .name("반팔티")
                .company(company)
                .part(TOP)
                .build();

        String json = objectMapper.writeValueAsString(clothesEdit);

        // when
        mockMvc.perform(put("/api/clothes/{clothesId}", clothes.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());

        // then
        Clothes editClothes = clothesRepository.findById(clothes.getId())
                .orElseThrow(ClothesNotFound::new);

        Assertions.assertEquals("AdviseClothes", editClothes.getUpdatedBy());
    }

    @Test
    @DisplayName("/api/clothes/{clothesId} DELETE 요청 시 옷 삭제")
    @Transactional
    void deleteClothes() throws Exception {
        // given
        Clothes clothes = clothesRepository.save(Clothes.builder()
                .name("반팔티")
                .company(company)
                .createdBy("JUnit5")
                .part(TOP)
                .build()
        );

        // excepted
        mockMvc.perform(delete("/api/clothes/{clothesId}", clothes.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }
}