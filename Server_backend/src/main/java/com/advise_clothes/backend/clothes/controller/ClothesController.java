package com.advise_clothes.backend.clothes.controller;

import com.advise_clothes.backend.clothes.dto.ClothesCreate;
import com.advise_clothes.backend.clothes.dto.ClothesEdit;
import com.advise_clothes.backend.clothes.dto.ClothesResponse;
import com.advise_clothes.backend.clothes.service.impl.ClothesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/clothes")
@RequiredArgsConstructor
public class ClothesController {

    private final ClothesServiceImpl clothesService;

    @PostMapping("")
    public void post(@RequestBody @Valid ClothesCreate request) {
        clothesService.create(request);
    }

    @GetMapping("/{clothesId}")
    public ClothesResponse get(@PathVariable(name = "clothesId") Long id) {
        return clothesService.get(id);
    }

    @PutMapping("/{clothesId}")
    public void edit(@PathVariable(name = "clothesId") Long id,
                     @RequestBody @Valid ClothesEdit clothesEdit) {
        clothesService.edit(id, clothesEdit);
    }

    @DeleteMapping("/{clothesId}")
    public void delete(@PathVariable(name = "clothesId") Long id) {
        clothesService.delete(id);
    }
}
