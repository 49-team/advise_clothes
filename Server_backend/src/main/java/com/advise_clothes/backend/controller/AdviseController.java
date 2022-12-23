package com.advise_clothes.backend.controller;

import com.advise_clothes.backend.domain.entity.Clothes;
import com.advise_clothes.backend.domain.entity.Company;
import com.advise_clothes.backend.exception.CompanyNotFound;
import com.advise_clothes.backend.repository.CompanyRepository;
import com.advise_clothes.backend.request.AdviseRequest;
import com.advise_clothes.backend.response.ClothesResponse;
import com.advise_clothes.backend.service.ClothesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.advise_clothes.backend.domain.entity.Clothes.ClothesPartEnum.*;

@RestController
@RequestMapping("/api/advise")
@RequiredArgsConstructor
public class AdviseController {

    private final CompanyRepository companyRepository;
    private final ClothesService clothesService;

    @GetMapping("")
    public List<ClothesResponse> testAdvise(@ModelAttribute AdviseRequest adviseRequest) {
        return clothesService.advice(adviseRequest);
    }
}
