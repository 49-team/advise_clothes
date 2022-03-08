package com.advise_clothes.project_advise_clothes.entity.config;

import com.advise_clothes.project_advise_clothes.ProjectAdviseClothesApplicationTests;
import com.advise_clothes.project_advise_clothes.entity.User;
import com.advise_clothes.project_advise_clothes.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;

public class EntityToolsTest extends ProjectAdviseClothesApplicationTests {

    @Autowired
    private UserRepository userRepository;

//    private final User suser = userRepository.findById(1L).orElse(new User());

    @Test
    public void toMapTest() throws Exception {
        User user = userRepository.findById(1L).orElse(new User());
        System.out.println(EntityTools.toMap(user));
    }

    @Test
    public void isParamsTest() throws Exception {
        User user = userRepository.findById(1L).orElse(new User());
        System.out.println(EntityTools.isParams(user, new String[]{"account"}));
    }
}
