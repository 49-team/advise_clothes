package com.advise_clothes.project_advise_clothes.repository;

import com.advise_clothes.project_advise_clothes.ProjectAdviseClothesApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionRepositoryTest extends ProjectAdviseClothesApplicationTests {

    @Autowired
    private SessionRepository sessionRepository;

    @Test
    public void findBySessionKeyTest() {
        System.out.println(sessionRepository.findBySessionKey("{bcrypt}$2a$10$UEJpuw/CNKpyW11P209R6eVn5KnoRfWfmAkQ74Q3QiqYKfFylR8CO"));
    }
}