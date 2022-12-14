package com.advise_clothes.backend.Controller;

import com.advise_clothes.backend.ServerBackendApplicationTests;
import com.advise_clothes.backend.entity.User;
import com.advise_clothes.backend.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class UserControllerTest extends ServerBackendApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("/api/users Get 요청 시 로그인")
    @Transactional
    void getLogin() throws Exception {
        // given
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("유저가 존재하지 않습니다."));

        // expected
        mockMvc.perform(get("/api/users")
                    .param("account", user.getAccount()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("/api/users/list Get 요청 시 모든 유저 정보를 반환")
    @Transactional
    void getUserList() throws Exception {

        // expected
        mockMvc.perform(get("/api/users/list"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("/api/users post 요청 시 유저 생성")
    @Transactional
    void createUser() throws Exception {

    }

}
