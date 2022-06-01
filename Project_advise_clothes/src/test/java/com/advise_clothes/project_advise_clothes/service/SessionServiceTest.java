package com.advise_clothes.project_advise_clothes.service;

import com.advise_clothes.project_advise_clothes.ProjectAdviseClothesApplicationTests;
import com.advise_clothes.project_advise_clothes.entity.Session;
import com.advise_clothes.project_advise_clothes.entity.User;
import com.advise_clothes.project_advise_clothes.entity.config.SessionType;
import com.advise_clothes.project_advise_clothes.repository.SessionRepository;
<<<<<<< HEAD
=======
import com.advise_clothes.project_advise_clothes.service.implement.SessionService;
>>>>>>> base/backend
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionServiceTest extends ProjectAdviseClothesApplicationTests {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private SessionRepository sessionRepository;

<<<<<<< HEAD
    private final Session sessionToFind = Session.builder().sessionKey("{bcrypt}$2a$10$N7K08RcrIg6UQnJnGfEJOOrd1AelyhH7UqqkcVNoCR6.KzZAumbIq").build();
    private final Session sessionEmpty = Session.builder().sessionKey("hello advise_clothes").build();

    @Test
    public void getSessionTest() {
        if (true) {
            System.out.println("SessionServiceTest : 이 함수는 세션의 정보를 반환할 것입니다");
            System.out.println("SessionServiceTest : " + sessionService.getSession(sessionToFind).toString());
        }
        else {
            System.out.println("SessionServiceTest : 이 함수는 빈 Optional을 반환할 것입니다");
            System.out.println("SessionServiceTest : " + sessionService.getSession(sessionToFind).toString());
        }
    }

    @Test
    public void isExistTest() {
        if (false) {
            System.out.println("SessionServiceTest : 이 함수는 true를 반환할 것입니다");
            System.out.println("SessionServiceTest : " + sessionService.isExist(sessionToFind));
        }
        else {
            System.out.println("SessionServiceTest : 이 함수는 false을 반환할 것입니다");
            System.out.println("SessionServiceTest : " + sessionService.isExist(sessionEmpty));
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

=======
    private final String message = "SessionServiceTest : ";
    private final Session sessionInSessionKey = Session.builder().sessionKey("{bcrypt}$2a$10$N7K08RcrIg6UQnJnGfEJOOrd1AelyhH7UqqkcVNoCR6.KzZAumbIq").build();
    private final Session sessionInEmptySessionKey = Session.builder().sessionKey("hello advise_clothes").build();
    private final Session sessionInUser1 = Session.builder().user(User.builder().id(1L).build()).platform(SessionType.BROWSER).build();        // 존재하는 유저
    private final Session sessionInUser2 = Session.builder().user(User.builder().id(-1L).build()).platform(SessionType.BROWSER).build();       // 존재하지 않는 유저

    @Test
    public void findBySessionKeyTest() {
        if (false) {
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
>>>>>>> base/backend
    }
}