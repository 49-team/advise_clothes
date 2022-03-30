package com.advise_clothes.project_advise_clothes.service;

import com.advise_clothes.project_advise_clothes.entity.Session;
import com.advise_clothes.project_advise_clothes.repository.SessionRepository;
import com.advise_clothes.project_advise_clothes.service.security.Encryption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final Encryption encryption;

    public Session createSession(Session session) {
        // 1. 재로그인 경우 기존 세션 지우기(id and platform 동일)
        // 2. 동일한 sessionKey 있는지 검사
        return sessionRepository.findByUserAndPlatform(session.getUser(), session.getPlatform()).map(value -> new Session())
                .orElseGet(() -> {
                session.setSessionKey(encryption.encode(Long.toString(System.currentTimeMillis()) + session.getUser().getId()));
                return sessionRepository.save(session);
            });
    }

    public Session deleteSession(Session session) {
        return sessionRepository.findBySessionKey(session.getSessionKey()).map(value -> {
            sessionRepository.delete(value);
            // 나중에 Log 기록 등 처리
            return new Session();
        }).orElseGet(Session::new);
    }

}
