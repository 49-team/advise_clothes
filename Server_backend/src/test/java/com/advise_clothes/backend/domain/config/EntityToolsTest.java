package com.advise_clothes.backend.domain.config;

import com.advise_clothes.backend.ServerBackendApplicationTests;
import com.advise_clothes.backend.domain.User;
import com.advise_clothes.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EntityToolsTest extends ServerBackendApplicationTests {

    @Autowired
    private UserRepository userRepository;

//    private final User suser = userRepository.findById(1L).orElse(new User());

    @Test
    public void toMapTest() throws Exception {
        User user = userRepository.findById(1L).orElse(new User());
//        System.out.println(EntityTools.toMap(user));
    }

    @Test
    public void isParamsTest() throws Exception {
        User user = userRepository.findById(1L).orElse(new User());
//        System.out.println(EntityTools.isParams(user, new String[]{"account"}));
    }
}
