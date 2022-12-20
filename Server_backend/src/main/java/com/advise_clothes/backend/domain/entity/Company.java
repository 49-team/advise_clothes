package com.advise_clothes.backend.domain.entity;

import com.advise_clothes.backend.config.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Company extends BaseEntity {
    private String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private List<Clothes> clothesList = new ArrayList<>();

    @Builder
    public Company(String name, String createdBy, String updatedBy) {
        this.name = name;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
