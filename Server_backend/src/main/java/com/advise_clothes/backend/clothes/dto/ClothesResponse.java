package com.advise_clothes.backend.clothes.dto;

import com.advise_clothes.backend.clothes.entity.Clothes;
import com.advise_clothes.backend.clothes.entity.Clothes.ClothesPartEnum;
import com.advise_clothes.backend.company.entity.Company;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClothesResponse {

    private final String name;
    private final Company company;
    private final ClothesPartEnum part;

    @Builder
    public ClothesResponse(String name, Company company, ClothesPartEnum part) {
        this.name = name;
        this.company = company;
        this.part = part;
    }

    public ClothesResponse(Clothes clothes) {
        this.name = clothes.getName();
        this.company = clothes.getCompany();
        this.part = clothes.getPart();
    }

}
