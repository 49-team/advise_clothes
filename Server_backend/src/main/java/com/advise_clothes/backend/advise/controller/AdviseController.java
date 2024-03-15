package com.advise_clothes.backend.advise.controller;

import com.advise_clothes.backend.advise.dto.AdviseRequest;
import com.advise_clothes.backend.company.repository.CompanyRepository;
import com.advise_clothes.backend.clothes.dto.ClothesResponse;
import com.advise_clothes.backend.clothes.service.impl.ClothesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/advise")
@RequiredArgsConstructor
public class AdviseController {

    private final CompanyRepository companyRepository;
    private final ClothesServiceImpl clothesService;

    @GetMapping("")
    public List<ClothesResponse> testAdvise(@ModelAttribute AdviseRequest adviseRequest) {
        return clothesService.advice(adviseRequest);
    }
}
