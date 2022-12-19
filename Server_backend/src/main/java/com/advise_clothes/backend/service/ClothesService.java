package com.advise_clothes.backend.service;

import com.advise_clothes.backend.response.ClothesResponse;
import com.advise_clothes.backend.request.ClothesCreate;
import com.advise_clothes.backend.domain.entity.Clothes;
import com.advise_clothes.backend.repository.ClothesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClothesService {

    private final ClothesRepository clothesRepository;

    public void create(ClothesCreate clothesCreate) {
        Clothes clothes = clothesCreate.toClothes();
        clothesRepository.save(clothes);
    }

    public ClothesResponse get(Long id) {
        Clothes clothes = clothesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("옷을 찾을 수 없습니다"));
        return new ClothesResponse(clothes);
    }
}
