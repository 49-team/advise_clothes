package com.advise_clothes.backend.request;

import com.advise_clothes.backend.domain.entity.Clothes;
import com.advise_clothes.backend.domain.entity.Clothes.ClothesPartEnum;
import com.advise_clothes.backend.domain.entity.Company;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class ClothesCreate {

    private String name;
    private Company company;
    private String createdBy;
    private ClothesPartEnum part;

    public ClothesCreate(String name, Company company, String createdBy, ClothesPartEnum part) {
        this.name = name;
        this.company = company;
        this.createdBy = createdBy;
        this.part = part;
    }

    public Clothes toClothes() {
        return Clothes.builder()
                .name(this.name)
                .company(this.company)
                .createdBy(this.createdBy)
                .part(this.part)
                .build();
    }
}
