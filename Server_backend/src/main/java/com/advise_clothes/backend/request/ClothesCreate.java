package com.advise_clothes.backend.request;

import com.advise_clothes.backend.domain.entity.Clothes.ClothesPartEnum;
import com.advise_clothes.backend.domain.entity.Company;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ClothesCreate {

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    @NotBlank(message = "회사를 입력해주세요.")
    private Company company;
    @NotBlank(message = "종류를 입력해주세요.")
    private ClothesPartEnum part;
    private String createdBy;

    @Builder
    public ClothesCreate(String name, Company company, ClothesPartEnum part, String createdBy) {
        this.name = name;
        this.company = company;
        this.part = part;
        this.createdBy = createdBy;
    }
}
