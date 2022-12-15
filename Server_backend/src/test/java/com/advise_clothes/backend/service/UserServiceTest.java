package com.advise_clothes.backend.service;

import com.advise_clothes.backend.ServerBackendApplicationTests;
import com.advise_clothes.backend.entity.User;
import com.advise_clothes.backend.repository.UserRepository;
import com.advise_clothes.backend.service.implement.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest extends ServerBackendApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 생성")
    @Transactional
    void create() {
        // given
        User user = User.builder()
                .account("testCreateUser")
                .password("testPassword")
                .nickname("ABCDEFG")
                .createdBy("JUnit5")
                .email("rieul.im@gmail.com")
                .deletedReason(0)
                .phoneNumber("888-8888-8888")
                .build();

        // when
        userService.create(user);

        // then
        User createUser = userRepository.findByAccount(user.getAccount())
                        .orElseThrow(() -> new RuntimeException("유저가 생성되지 않았습니다."));

        assertEquals("testCreateUser", createUser.getAccount());
        assertEquals("ABCDEFG", createUser.getNickname());
        assertEquals("JUnit5", createUser.getCreatedBy());
        assertEquals("rieul.im@gmail.com", createUser.getEmail());
        assertEquals(0, createUser.getDeletedReason());
        assertEquals("888-8888-8888", createUser.getPhoneNumber());
    }

    @Test
    @DisplayName("account와 password를 이용해서 로그인")
    @Transactional
    void loginThroughAccountAndPassword() {
        // UserService.findByAccounteAndPassword()는 기능 구현이 안 되어 테스트를 쓰지 않았다. 나중에 지울 것
    }

    @Test
    @DisplayName("가입한 유저인지 확인")
    @Transactional
    void getUser() {
        // given
        User user = userRepository.save(User.builder()
                .account("testCreateUser")
                .password("testPassword")
                .nickname("ABCDEFG")
                .createdBy("JUnit5")
                .email("rieul.im@gmail.com")
                .deletedReason(0)
                .phoneNumber("888-8888-8888")
                .build()
        );

        // when
        User getUser = userService.findByUser(User.builder().account("testCreateUser").build())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        // then
        assertEquals(user.toString(), getUser.toString());
    }

}