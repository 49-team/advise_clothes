package com.advise_clothes.backend.service;

import com.advise_clothes.backend.domain.business.ClothesEditor;
import com.advise_clothes.backend.domain.entity.Clothes;
import com.advise_clothes.backend.exception.ClothesNotFound;
import com.advise_clothes.backend.repository.ClothesRepository;
import com.advise_clothes.backend.request.ClothesCreate;
import com.advise_clothes.backend.request.ClothesEdit;
import com.advise_clothes.backend.response.ClothesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ClothesService {

    private final ClothesRepository clothesRepository;

    public void create(ClothesCreate clothesCreate) {
        clothesCreate.validate();
        clothesRepository.save(Clothes.builder()
                        .name(clothesCreate.getName())
                        .company(clothesCreate.getCompany())
                        .part(clothesCreate.getPart())
                        .createdBy(clothesCreate.getCreatedBy() == null ? clothesCreate.getCompany().getName() : clothesCreate.getCreatedBy())
                        .build()
        );
    }

    public ClothesResponse get(Long id) {
        Clothes clothes = clothesRepository.findById(id)
                .orElseThrow(ClothesNotFound::new);
        return new ClothesResponse(clothes);
    }

    @Transactional
    public void edit(Long id, ClothesEdit clothesEdit) {
        Clothes clothes = clothesRepository.findById(id)
                .orElseThrow(ClothesNotFound::new);

        ClothesEditor.ClothesEditorBuilder editorBuilder = clothes.toEditor();

        ClothesEditor clothesEditor = editorBuilder
                .name(clothesEdit.getName())
                .company(clothesEdit.getCompany())
                .part(clothesEdit.getPart())
                .updatedBy(clothesEdit.getUpdatedBy())
                .build();

        clothes.edit(clothesEditor);
    }

    @Transactional
    public void delete(Long id) {
        Clothes clothes = clothesRepository.findById(id)
                .orElseThrow(ClothesNotFound::new);
        clothesRepository.delete(clothes);
    }
}
