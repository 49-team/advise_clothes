package com.advise_clothes.backend.domain.business;

import com.advise_clothes.backend.domain.entity.Clothes;
import com.advise_clothes.backend.domain.entity.Clothes.ClothesPartEnum;
import com.advise_clothes.backend.domain.entity.Company;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ClothesEditor {

    private final String name;
    private final Company company;
    private final ClothesPartEnum part;
    private final String updatedBy;

    public ClothesEditor(String name, Company company, ClothesPartEnum part, String updatedBy) {
        this.name = name;
        this.company = company;
        this.part = part;
        this.updatedBy = updatedBy;
    }

    public static ClothesEditorBuilder builder() {
        return new ClothesEditorBuilder();
    }

    public static class ClothesEditorBuilder {
        private String name;
        private Company company;
        private ClothesPartEnum part;
        private String updatedBy;

        ClothesEditorBuilder() {
        }

        public ClothesEditorBuilder name(final String name) {
            if (name != null) {
                this.name = name;
            }
            return this;
        }

        public ClothesEditorBuilder company(final Company company) {
            if (company != null) {
                this.company = company;
            }
            return this;
        }

        public ClothesEditorBuilder part(final ClothesPartEnum part) {
            if (part != null) {
                this.part = part;
            }
            return this;
        }

        public ClothesEditorBuilder updatedBy(final String updatedBy) {
            if (updatedBy != null) {
                this.updatedBy = updatedBy;
            }
            else {
                this.updatedBy = this.company.getName();
            }
            return this;
        }

        public ClothesEditor build() {
            return new ClothesEditor(this.name, this.company, this.part, this.updatedBy);
        }

        public String toString() {
            return "ClothesEditor.ClothesEditorBuilder(name=" + this.name + ", company=" + this.company + ", part=" + this.part + ", updatedBy=" + this.updatedBy + ")";
        }
    }
}
