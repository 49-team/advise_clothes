package com.advise_clothes.backend.Controller;

import com.advise_clothes.backend.ServerBackendApplicationTests;
import com.advise_clothes.backend.domain.entity.User;
import com.advise_clothes.backend.repository.UserRepository;
import com.advise_clothes.backend.request.UserLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        String account = "testCreateUser1";
        String password = "testPassword";

        userRepository.save(
                User.builder()
                        .account(account)
                        .password(password)
                        .nickname("ABCDEFG")
                        .email("rieul.im@gmail.com")
                        .deletedReason(0)
                        .phoneNumber("888-8888-8888")
                        .build()
        );

        ObjectMapper objectMapper = new ObjectMapper();
        String params = objectMapper.writeValueAsString(UserLogin.builder()
                .account(account)
                .password(password)
                .build());

        // expected
        mockMvc.perform(get("/api/users")
                        .contentType(APPLICATION_JSON)
                        .content(params))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("/api/users post 요청 시 유저 생성")
    @Transactional
    void createUser() throws Exception {
        // given
        User user = User.builder()
                .account("testCreateUser")
                .password("testPassword")
                .nickname("ABCD")
                .createdBy("김동건")
                .email("rieul.im@gmail.com")
                .deletedReason(0)
                .phoneNumber("888-8888-8888")
                .build();

        String json = objectMapper.writeValueAsString(user);

        // expected
        mockMvc.perform(post("/api/users")
                    .contentType(APPLICATION_JSON)
                    .content(json))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("/api/users/{account} PUT 요청 시 유저 수정")
    @Transactional
    void updateUser() throws Exception {
        // given
        User user = userRepository.save(User.builder()
                .account("testCreateUser")
                .password("testPassword")
                .nickname("ABCD")
                .createdBy("김동건")
                .email("rieul.im@gmail.com")
                .deletedReason(0)
                .phoneNumber("888-8888-8888")
                .build()
        );

        user.setNickname("ABCDEFG");

        String json = objectMapper.writeValueAsString(user);

        // excepted
        mockMvc.perform(put("/api/users/{account}", user.getAccount())
                    .contentType(APPLICATION_JSON)
                    .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nickname").value(user.getNickname()))
                .andDo(print());
    }

    @Test
    @DisplayName("/api/users/{account} Delete 요청 시 유저 삭제(삭제 속성 수정)")
    @Transactional
    void deleteUser() throws Exception {
        // given
        User user = userRepository.save(User.builder()
                .account("testCreateUser")
                .password("testPassword")
                .nickname("ABCD")
                .createdBy("김동건")
                .email("rieul.im@gmail.com")
                .deletedReason(0)
                .phoneNumber("888-8888-8888")
                .build()
        );

        // excepted
        mockMvc.perform(delete("/api/users/{account}", user.getAccount()))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
