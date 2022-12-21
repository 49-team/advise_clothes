package com.advise_clothes.backend.repository;

import com.advise_clothes.backend.domain.entity.Clothes;
import com.advise_clothes.backend.domain.entity.Clothes.ClothesPartEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClothesRepository extends JpaRepository<Clothes, Long> {
    Optional<Clothes> findByName(String name);
    List<Clothes> findByPart(ClothesPartEnum part);
}
