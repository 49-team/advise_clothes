package com.advise_clothes.project_advise_clothes.service;

import com.advise_clothes.project_advise_clothes.entity.Session;
import com.advise_clothes.project_advise_clothes.entity.User;
import com.advise_clothes.project_advise_clothes.repository.SessionRepository;
import com.advise_clothes.project_advise_clothes.repository.UserRepository;
import com.advise_clothes.project_advise_clothes.service.security.Encryption;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final Encryption encryption;

    public Optional<Session> getSession(Session session) {
        return (session.getSessionKey() != null)? sessionRepository.findBySessionKey(session.getSessionKey()) :
                sessionRepository.findByUserAndPlatform(session.getUser(), session.getPlatform());
    }

    public boolean isExist(Session session) {
        return sessionRepository.findBySessionKey(session.getSessionKey()).map(value -> true)
                .orElseGet(() ->
                    sessionRepository.findByUserAndPlatform(User.builder().id(session.getId()).build(), session.getPlatform()).isPresent()
                );
    }

    /**
     *
     * @param session
     * @return 새로운 Session 생성(생성된 Session), 이미 Session 발급(생성자), 없는 회원(Session.id = -1L)
     */
    public Session createSession(Session session) {
        // 1. 재로그인 경우 기존 세션 지우기(id and platform 동일)
        // 2. 동일한 sessionKey 있는지 검사
        try {
            return sessionRepository.findByUserAndPlatform(session.getUser(), session.getPlatform()).map(value -> {
                value.setSessionKey(createdSessionKey(value));
                return sessionRepository.save(value);
            }).orElseGet(() -> {
                session.setSessionKey(createdSessionKey(session));
                return sessionRepository.save(session);
            });

//            return userRepository.findById(session.getUser().getId()).map(user ->
//                sessionRepository.findByUserAndPlatform(user, session.getPlatform()).map(value -> {
//                    value.setSessionKey(createdSessionKey(value));
//                    return sessionRepository.save(value);
//                }).orElseGet(() -> {
//                    session.setSessionKey(createdSessionKey(session));
//                    return sessionRepository.save(session);
//                })
//            ).orElseGet(() -> Session.builder().user(User.builder().id(-1L).build()).build());

        // DB에 2개 이상의 User and platform이 검색됐을 경우
        } catch (IncorrectResultSizeDataAccessException e) {
            sessionRepository.deleteAll(sessionRepository.findAllByUser(session.getUser()));
            session.setSessionKey(createdSessionKey(session));
            return sessionRepository.save(session);
        }

    }

    public Session deleteSession(Session session) {
        return sessionRepository.findBySessionKey(session.getSessionKey()).map(value -> {
            sessionRepository.delete(value);
            // 나중에 Log 기록 등 처리
            return new Session();
        }).orElseGet(Session::new);
    }

    private String createdSessionKey(Session session) {
        return encryption.encode(Long.toString(System.currentTimeMillis()) + session.getUser().getId());
    }
}
