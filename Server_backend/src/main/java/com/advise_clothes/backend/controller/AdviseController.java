package com.advise_clothes.backend.controller;

import com.advise_clothes.backend.domain.entity.Clothes;
import com.advise_clothes.backend.domain.entity.Company;
import com.advise_clothes.backend.exception.CompanyNotFound;
import com.advise_clothes.backend.repository.CompanyRepository;
import com.advise_clothes.backend.request.AdviseCreate;
import com.advise_clothes.backend.response.ClothesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.advise_clothes.backend.domain.entity.Clothes.ClothesPartEnum.*;

@RestController
@RequestMapping("/api/advise")
@RequiredArgsConstructor
public class AdviseController {

    private final CompanyRepository companyRepository;

    @GetMapping("")
    public List<ClothesResponse> testAdvise(@ModelAttribute @Valid AdviseCreate adviseCreate) {
        Company company = companyRepository.findById(1L)
                .orElseThrow(CompanyNotFound::new);

        ClothesResponse top = new ClothesResponse(Clothes.builder()
                .name("니트티")
                .company(company)
                .part(TOP)
                .createdBy("admin")
                .build());

        ClothesResponse bottom = new ClothesResponse(Clothes.builder()
                .name("슬랙스")
                .company(company)
                .part(BOTTOM)
                .createdBy("admin")
                .build());

        ClothesResponse shoes = new ClothesResponse(Clothes.builder()
                .name("운동화")
                .company(company)
                .part(SHOES)
                .createdBy("admin")
                .build());

        ClothesResponse outer = new ClothesResponse(Clothes.builder()
                .name("롱패딩")
                .company(company)
                .part(OUTER)
                .createdBy("admin")
                .build());

        return List.of(top, bottom, shoes, outer);
    }
}
