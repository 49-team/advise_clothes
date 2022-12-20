package com.advise_clothes.backend.domain.entity;

import com.advise_clothes.backend.config.BaseEntity;
import com.advise_clothes.backend.domain.business.ClothesEditor;
import com.advise_clothes.backend.request.ClothesEdit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Clothes extends BaseEntity {
    private String name;

    @ManyToOne
    @JsonIgnore
    private Company company;

    @Enumerated(EnumType.STRING)
    private ClothesPartEnum part;

    @Builder
    public Clothes(String name, Company company, String createdBy, String updatedBy, ClothesPartEnum part) {
        this.name = name;
        this.company = company;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.part = part;
    }

    public ClothesEditor.ClothesEditorBuilder toEditor() {
        return ClothesEditor.builder()
                .name(name)
                .company(company)
                .part(part)
                .updatedBy(updatedBy);
    }

    public void edit(ClothesEditor clothesEditor) {
        this.name = clothesEditor.getName();
        this.company = clothesEditor.getCompany();
        this.part = clothesEditor.getPart();
        this.updatedBy = clothesEditor.getUpdatedBy();
    }

    public enum ClothesPartEnum {
        TOP,
        BOTTOM,
        SHOES,
        OUTER,
    }
}
