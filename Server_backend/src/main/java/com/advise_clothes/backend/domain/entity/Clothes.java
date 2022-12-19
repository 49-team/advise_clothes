package com.advise_clothes.backend.domain.entity;

import com.advise_clothes.backend.config.BaseEntity;
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

    public enum ClothesPartEnum {
        TOP,
        BOTTOM,
        SHOES,
        OUTER,
    }
}
