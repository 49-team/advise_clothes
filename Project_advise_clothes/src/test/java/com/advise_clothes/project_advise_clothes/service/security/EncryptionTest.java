package com.advise_clothes.project_advise_clothes.service.security;

import com.advise_clothes.project_advise_clothes.ProjectAdviseClothesApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncryptionTest extends ProjectAdviseClothesApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void encodeTest() {
        String str = "hello world";
        System.out.println(passwordEncoder.encode(str));
    }

    @Test
    public void matchesTest() {
        String str = "hello world";
        String encrypt = passwordEncoder.encode(str);
        Long miltime = System.currentTimeMillis();

        System.out.println(str);
        System.out.println(encrypt);
        System.out.println(miltime);
        System.out.println(passwordEncoder.matches(str, encrypt));
        System.out.println(passwordEncoder.matches(str, "{bcrypt}$2a$10$EGyreZ/OihkOt1v/zswHluZ6n0N4qwzyCcdIA.jMWIhrB3Gd1drtS"));
    }
}