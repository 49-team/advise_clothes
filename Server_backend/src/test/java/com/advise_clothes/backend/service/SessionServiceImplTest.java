package com.advise_clothes.backend.service;

import com.advise_clothes.backend.ServerBackendApplicationTests;
import com.advise_clothes.backend.session.entity.Session;
import com.advise_clothes.backend.user.entity.User;
import com.advise_clothes.backend.session.repository.SessionRepository;
import com.advise_clothes.backend.user.repository.UserRepository;
import com.advise_clothes.backend.session.service.impl.SessionServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

class SessionServiceImplTest extends ServerBackendApplicationTests {

    @Autowired
    private SessionServiceImpl sessionService;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void createUser() {
        userRepository.save(User.builder()
                .account("testCreateUser")
                .password("testPassword")
                .nickname("ABCDEFG")
                .createdBy("JUnit5")
                .email("rieul.im@gmail.com")
                .deletedReason(0)
                .phoneNumber("888-8888-8888")
                .build()
        );
    }

    @AfterEach
    void deleteUser() {
        userRepository.findByAccount("testCreateUser")
                .ifPresent(userRepository::delete);
    }

    /**
     * 작동하지 않음
     */
//    @Test
//    @DisplayName("세션 생성, 작동 안 함")
//    @Transactional
//    void create() {
//        // given
//        Session session = sessionService.create(Session.builder()
//                .platform(BROWSER)
//                .user(userRepository.findByAccount("testCreateUser").get())
//                .build()
//        );
//
//        // when
//        Session createSession = sessionService.create(session);
//
//        // then
//        assertEquals(BROWSER, createSession.getPlatform());
//        assertEquals("testCreateUser", createSession.getUser().getAccount());
//    }

    @Test
    @DisplayName("세션 읽기")
    @Transactional
    void read() {
        // given
        String sessionKey = sessionRepository.findById(25L).get().getSessionKey();

        // when
        Session session = sessionService.findBySessionKey(Session.builder()
                .sessionKey(sessionKey)
                .build()
        ).orElseThrow(() -> new RuntimeException("세션을 찾지 못했습니다"));

        // then
        assertEquals(sessionKey, session.getSessionKey());
    }

    @Test
    @DisplayName("세션이 있는지 확인")
    @Transactional
    void isExist() {
        // given
        Session session = sessionRepository.findById(25L).get();

        // when
        boolean sessionExist = sessionService.isExist(session);
        boolean sessionNotExist = sessionService.isExist(Session.builder()
                .sessionKey("hello world")
                .build()
        );

        // then
        assertTrue(sessionExist);
        assertFalse(sessionNotExist);
    }

    @Test
    @DisplayName("세선 삭제")
    @Transactional
    void delete() {
        // given
        Session session = sessionRepository.findById(25L).get();

        // when
        Session deleteSession = sessionService.delete(session);

        // then
        assertEquals(session.getSessionKey(), deleteSession.getSessionKey());
    }
}