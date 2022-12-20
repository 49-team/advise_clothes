package com.advise_clothes.backend.service;

import com.advise_clothes.backend.domain.business.ClothesEditor;
import com.advise_clothes.backend.request.ClothesEdit;
import com.advise_clothes.backend.response.ClothesResponse;
import com.advise_clothes.backend.request.ClothesCreate;
import com.advise_clothes.backend.domain.entity.Clothes;
import com.advise_clothes.backend.repository.ClothesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ClothesService {

    private final ClothesRepository clothesRepository;

    public void create(ClothesCreate clothesCreate) {
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
                .orElseThrow(() -> new RuntimeException("옷을 찾을 수 없습니다"));
        return new ClothesResponse(clothes);
    }

    @Transactional
    public void edit(Long id, ClothesEdit clothesEdit) {
        Clothes clothes = clothesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("옷을 찾을 수 없습니다"));

        ClothesEditor.ClothesEditorBuilder editorBuilder = clothes.toEditor();

        ClothesEditor clothesEditor = editorBuilder
                .name(clothesEdit.getName())
                .company(clothesEdit.getCompany())
                .part(clothesEdit.getPart())
                .updatedBy(clothesEdit.getUpdatedBy())
                .build();

        clothes.edit(clothesEditor);
    }
}
