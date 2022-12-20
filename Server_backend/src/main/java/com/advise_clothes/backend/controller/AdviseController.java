package com.advise_clothes.backend.controller;

import com.advise_clothes.backend.domain.entity.Clothes;
import com.advise_clothes.backend.domain.entity.Company;
import com.advise_clothes.backend.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.advise_clothes.backend.domain.entity.Clothes.ClothesPartEnum.BOTTOM;
import static com.advise_clothes.backend.domain.entity.Clothes.ClothesPartEnum.TOP;

@RestController
@RequestMapping("/api/advise")
@RequiredArgsConstructor
public class AdviseController {

    private final CompanyRepository companyRepository;

    @GetMapping("")
    public List<Clothes> testAdvise(@RequestParam String temperature, @RequestParam String weather) {
        Company company = companyRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("Not found company"));

        Clothes top = Clothes.builder()
                .name("니트티")
                .company(company)
                .part(TOP)
                .createdBy("admin")
                .build();

        Clothes bottom = Clothes.builder()
                .name("슬랙스")
                .company(company)
                .part(BOTTOM)
                .createdBy("admin")
                .build();

        Clothes shoes = Clothes.builder()
                .name("운동화")
                .company(company)
                .part(BOTTOM)
                .createdBy("admin")
                .build();

        Clothes outer = Clothes.builder()
                .name("롱패딩")
                .company(company)
                .part(BOTTOM)
                .createdBy("admin")
                .build();

        return List.of(top, bottom, shoes, outer);
    }
}
