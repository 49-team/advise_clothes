package com.advise_clothes.backend.service;

import com.advise_clothes.backend.ServerBackendApplicationTests;
import com.advise_clothes.backend.domain.Session;
import com.advise_clothes.backend.domain.User;
import com.advise_clothes.backend.repository.SessionRepository;
import com.advise_clothes.backend.repository.UserRepository;
import com.advise_clothes.backend.service.implement.SessionService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static com.advise_clothes.backend.domain.config.SessionType.BROWSER;
import static org.junit.jupiter.api.Assertions.*;

public class SessionServiceTest extends ServerBackendApplicationTests {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserRepository userRepository;

    private final String message = "SessionServiceTest : ";
    private final Session sessionInSessionKey = Session.builder().sessionKey("{bcrypt}$2a$10$N7K08RcrIg6UQnJnGfEJOOrd1AelyhH7UqqkcVNoCR6.KzZAumbIq").build();
    private final Session sessionInEmptySessionKey = Session.builder().sessionKey("hello advise_clothes").build();
    private final Session sessionInUser1 = Session.builder().user(User.builder().id(1L).build()).platform(BROWSER).build();        // 존재하는 유저
    private final Session sessionInUser2 = Session.builder().user(User.builder().id(-1L).build()).platform(BROWSER).build();       // 존재하지 않는 유저

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
        userRepository.delete(userRepository.findByAccount("testCreateUser")
                .orElseThrow(() -> new RuntimeException("유저가 생성된 적이 없습니다.")));
    }

    @Test
    public void findBySessionKeyTest() {
        System.out.println(sessionRepository.findById(25L));

        if (true) {
            System.out.println(message + "이 함수는 세션의 정보를 반환할 것입니다");
            System.out.println(message + sessionService.findBySessionKey(sessionInSessionKey).toString());
        }
        else {
            System.out.println(message + "이 함수는 빈 Optional을 반환할 것입니다");
            System.out.println(message + sessionService.findBySessionKey(sessionInEmptySessionKey).toString());
        }
    }

    @Test
    public void createTest() {
        String success = message + "세션 생성에 성공했습니다.";
        String fail = message + "세션 생성에 실패했습니다.";

        if (true) {
            System.out.println((sessionService.create(sessionInUser1) != null)? success : fail);
        }
        // User 검사는 Controller에서 하고 있어서 User id가 -1으로도 만들어짐
//        else {
//            System.out.println((sessionService.createSession(sessionInUser2) != null)? success : fail);
//        }
    }

    @Test
    public void deleteTest() {
        String success = message + "세션 제거에 성공했습니다.";
        String fail = message + "세션 제거에 실패했습니다.";

        if(false) {
            System.out.println(success);
            sessionService.create(sessionInUser1);
        }
        else {
            System.out.println(fail);
            sessionService.delete(sessionInUser1);
        }
        System.out.println(message + sessionService.delete(sessionInUser1));
    }

    /**
     * 작동하지 않음
     */
    @Test
    @DisplayName("세션 생성")
    @Transactional
    void create() {
        // given
        Session session = sessionService.create(Session.builder()
                .platform(BROWSER)
                .user(userRepository.findByAccount("testCreateUser").get())
                .build()
        );

        // when
        Session createSession = sessionService.create(session);

        // then
        assertEquals(BROWSER, createSession.getPlatform());
        assertEquals("testCreateUser", createSession.getUser().getAccount());
    }

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