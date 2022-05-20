package com.advise_clothes.project_advise_clothes.service;

import com.advise_clothes.project_advise_clothes.ProjectAdviseClothesApplicationTests;
import com.advise_clothes.project_advise_clothes.entity.Session;
import com.advise_clothes.project_advise_clothes.entity.User;
import com.advise_clothes.project_advise_clothes.entity.config.SessionType;
import com.advise_clothes.project_advise_clothes.repository.SessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionServiceTest extends ProjectAdviseClothesApplicationTests {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private SessionRepository sessionRepository;

    @Test
    public void isExistTest() {
        Session sessionToFind = Session.builder().sessionKey("{bcrypt}$2a$10$N7K08RcrIg6UQnJnGfEJOOrd1AelyhH7UqqkcVNoCR6.KzZAumbIq").build();
        if (false) {
            System.out.println("SessionServiceTest : 이 함수는 true를 반환할 것입니다");
            System.out.println("SessionServiceTest : " + sessionService.isExist(sessionToFind));
        }
        else {
            System.out.println("SessionServiceTest : 이 함수는 false을 반환할 것입니다");
            System.out.println("SessionServiceTest : " + sessionService.isExist(Session.builder().sessionKey("hello").build()));
        }


    }

    @Test
    public void createdSessionTest() {
        Session session = Session.builder().user(User.builder().id(24L).build()).platform(SessionType.BROWSER).build();
        sessionService.createSession(session);
    }


    @Test
    public void deleteSessionTest() {
        sessionRepository.findById(7L).map(value -> {
            System.out.println("세션이 삭제되었습니다, SessionKey : " + value.getSessionKey());
            return sessionService.deleteSession(value);
            }).orElseGet(Session::new);

    }
}