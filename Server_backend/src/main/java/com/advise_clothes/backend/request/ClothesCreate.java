package com.advise_clothes.backend.request;

import com.advise_clothes.backend.domain.entity.Clothes.ClothesPartEnum;
import com.advise_clothes.backend.domain.entity.Company;
import com.advise_clothes.backend.exception.InvalidRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ClothesCreate {

    @NotBlank(message = "옷 이름을 입력해주세요.")
    private String name;
    private Company company;
    private ClothesPartEnum part;
    private String createdBy;

    @Builder
    public ClothesCreate(String name, Company company, ClothesPartEnum part, String createdBy) {
        this.name = name;
        this.company = company;
        this.part = part;
        this.createdBy = createdBy;
    }

    public void validate() {
        if (this.company == null) {
            throw new InvalidRequest("company", "회사를 입력해주세요.");
        }
        if (this.part == null) {
            throw new InvalidRequest("part", "옷 종류를 입력해주세요.");
        }
    }
}
