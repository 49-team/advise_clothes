package com.advise_clothes.backend.clothes.dto;


import com.advise_clothes.backend.clothes.entity.Clothes.ClothesPartEnum;
import com.advise_clothes.backend.company.entity.Company;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClothesEdit {
    private String name;
    private Company company;
    private ClothesPartEnum part;
    private String updatedBy;

    public ClothesEdit(String name, Company company, ClothesPartEnum part, String updatedBy) {
        this.name = name;
        this.company = company;
        this.part = part;
        this.updatedBy = updatedBy;
    }
}
