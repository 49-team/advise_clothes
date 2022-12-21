package com.advise_clothes.backend.repository;

import com.advise_clothes.backend.ServerBackendApplicationTests;
import com.advise_clothes.backend.domain.entity.Clothes;
import com.advise_clothes.backend.domain.entity.Company;
import com.advise_clothes.backend.exception.CompanyNotFound;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.advise_clothes.backend.domain.entity.Clothes.ClothesPartEnum.*;

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

    @Test
    @Transactional
    void inputData() {
        Company company = companyRepository.findById(1L)
                .orElseThrow(CompanyNotFound::new);

        List<Clothes> clothesList = new ArrayList<>();

        Stream.of( "운동화", "스니커즈", "슬리퍼", "구두", "로퍼")
                        .forEach(clothes -> clothesList.add(Clothes.builder()
                                .name(clothes)
                                .company(company)
                                .createdBy("ImRieul")
                                .part(SHOES)
                                .build()));


        clothesRepository.saveAll(clothesList);
    }

    @Test
    void read() {
        System.out.println(clothesRepository.findAll());
    }
}