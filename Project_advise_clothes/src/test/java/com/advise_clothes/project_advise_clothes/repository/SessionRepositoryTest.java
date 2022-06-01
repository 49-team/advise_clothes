package com.advise_clothes.project_advise_clothes.repository;

import com.advise_clothes.project_advise_clothes.ProjectAdviseClothesApplicationTests;
<<<<<<< HEAD
=======
import com.advise_clothes.project_advise_clothes.entity.User;
>>>>>>> base/backend
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class SessionRepositoryTest extends ProjectAdviseClothesApplicationTests {

    @Autowired
    private SessionRepository sessionRepository;

    @Test
<<<<<<< HEAD
=======
    public void findAllByUserTest() {
        System.out.println(sessionRepository.findAllByUser(User.builder().id(1L).build()).isEmpty());
    }

    @Test
>>>>>>> base/backend
    public void findBySessionKeyTest() {
        String session = URLDecoder.decode("%7Bbcrypt%7D%242a%2410%24aR3ysioH2qOYupQcsmVKw.TEinA9J29Ig8etuMXUZiihykkJ3AH.6" , StandardCharsets.UTF_8);
        System.out.println(sessionRepository.findBySessionKey(session));
    }
}