package com.advise_clothes.backend.repository;

import com.advise_clothes.backend.ServerBackendApplicationTests;
import com.advise_clothes.backend.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class SessionRepositoryTest extends ServerBackendApplicationTests {

    @Autowired
    private SessionRepository sessionRepository;

    @Test
    public void findAllByUserTest() {
        System.out.println(sessionRepository.findAllByUser(User.builder().id(1L).build()).isEmpty());
    }

    @Test
    public void findBySessionKeyTest() {
        String session = URLDecoder.decode("%7Bbcrypt%7D%242a%2410%24aR3ysioH2qOYupQcsmVKw.TEinA9J29Ig8etuMXUZiihykkJ3AH.6" , StandardCharsets.UTF_8);
        System.out.println(sessionRepository.findBySessionKey(session));
    }
}