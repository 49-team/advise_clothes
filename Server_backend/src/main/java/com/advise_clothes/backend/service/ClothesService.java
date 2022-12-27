package com.advise_clothes.backend.service;

import com.advise_clothes.backend.domain.business.ClothesEditor;
import com.advise_clothes.backend.domain.entity.Clothes;
import com.advise_clothes.backend.exception.ClothesNotFound;
import com.advise_clothes.backend.repository.ClothesRepository;
import com.advise_clothes.backend.request.AdviseRequest;
import com.advise_clothes.backend.request.ClothesCreate;
import com.advise_clothes.backend.request.ClothesEdit;
import com.advise_clothes.backend.response.ClothesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.advise_clothes.backend.domain.entity.Clothes.ClothesPartEnum.*;

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

    public List<ClothesResponse> advice(AdviseRequest adviseRequest) {
        List<ClothesResponse> result = new ArrayList<>();
        List<String> topFilter;
        List<String> bottomFilter;
        List<String> outerFilter;
//        List<String> shoesFilter = new ArrayList<>();
        
        List<ClothesResponse> top = new ArrayList<>();
        List<ClothesResponse> bottom = new ArrayList<>();
        List<ClothesResponse> outer = new ArrayList<>();
//        List<ClothesResponse> shoes = new ArrayList<>();

        if (adviseRequest.getTemperature() >= 28) {
            topFilter = List.of("민소매", "반팔티", "린넨셔츠");
            bottomFilter = List.of("반바지", "짧은치마");
            outerFilter = new ArrayList<>();
        } else if (adviseRequest.getTemperature() >= 23 ) {
            topFilter = List.of("반팔티", "얇은셔츠");
            bottomFilter = List.of("반바지", "면바지");
            outerFilter = new ArrayList<>();
        } else if (adviseRequest.getTemperature() >= 20) {
            topFilter = List.of("블라우스", "긴팔티");
            bottomFilter = List.of("면바지", "슬랙스");
            outerFilter = new ArrayList<>();
        } else if (adviseRequest.getTemperature() >= 17) {
            topFilter = List.of("니트티", "맨투맨", "후드");
            bottomFilter = List.of("긴바지");
            outerFilter = List.of("얇은가디건");
        } else if (adviseRequest.getTemperature() >= 12) {
            topFilter = List.of("니트티", "맨투맨", "후드");
            bottomFilter = List.of("청바지");
            outerFilter = List.of("자켓", "가디건", "청자켓");
        } else if (adviseRequest.getTemperature() >= 9) {
            topFilter = List.of("니트티", "맨투맨", "후드");
            bottomFilter = List.of("기모바지");
            outerFilter = List.of("트렌치코트", "야상", "점퍼");
        } else if (adviseRequest.getTemperature() >= 5) {
            topFilter = List.of("니트티", "맨투맨", "후드", "기모티");
            bottomFilter = List.of("기모바지");
            outerFilter = List.of("울코트");
        } else {
            topFilter = List.of("니트티", "맨투맨", "후드", "기모티");
            bottomFilter = List.of("기모바지");
            outerFilter = List.of("패딩", "두꺼운코트");
        }

        if (topFilter.size() != 0) {
            top = clothesRepository.findByPart(TOP)
                    .stream().filter(x -> topFilter.contains(x.getName()))
                    .map(ClothesResponse::new)
                    .collect(Collectors.toList());
        }
        
        if (bottomFilter.size() != 0) {
            bottom = clothesRepository.findByPart(BOTTOM)
                    .stream().filter(x -> bottomFilter.contains(x.getName()))
                    .map(ClothesResponse::new)
                    .collect(Collectors.toList());
        }
        
        if (outerFilter.size() != 0) {
            outer = clothesRepository.findByPart(OUTER)
                    .stream().filter(x -> outerFilter.contains(x.getName()))
                    .map(ClothesResponse::new)
                    .collect(Collectors.toList());
        }
        
//        if (shoesFilter.size() != 0) {
//            shoes = clothesRepository.findByPart(SHOES)
//                    .stream().filter(x -> shoesFilter.contains(x.getName()))
//                    .map(ClothesResponse::new)
//                    .collect(Collectors.toList());
//        }

        result.addAll(top);
        result.addAll(bottom);
        result.addAll(outer);
//        result.addAll(shoes);

        return result;
    }

}
