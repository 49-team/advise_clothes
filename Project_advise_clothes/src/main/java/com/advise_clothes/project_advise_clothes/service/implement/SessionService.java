package com.advise_clothes.project_advise_clothes.service.implement;

import com.advise_clothes.project_advise_clothes.entity.Session;
import com.advise_clothes.project_advise_clothes.entity.User;
import com.advise_clothes.project_advise_clothes.repository.SessionRepository;
import com.advise_clothes.project_advise_clothes.repository.UserRepository;
import com.advise_clothes.project_advise_clothes.service.interfaces.SessionServiceInterface;
import com.advise_clothes.project_advise_clothes.service.security.Encryption;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SessionService implements SessionServiceInterface {
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final Encryption encryption;

    /**
     * sesstionKey를 통해 session 정보 가져오기
     * @param session
     * @return
     */
    public Optional<Session> findBySessionKey(Session session) {
        return sessionRepository.findBySessionKey(session.getSessionKey());
    }

    /**
     * 세션이 존재하는지 확인
     * @param session
     * @return
     */
    public boolean isExist(Session session) {
        return sessionRepository.findBySessionKey(session.getSessionKey()).isPresent();
    }

    /**
     *
     * @param session
     * @return 새로운 Session 생성(생성된 Session), 이미 Session 발급(생성자), 없는 회원(Session.id = -1L)
     */
    public Session createSession(Session session) {
        // 1. 재로그인 경우 기존 세션 지우기(id and platform 동일)
        // 2. 동일한 sessionKey 있는지 검사 - sessionKey가 같으면 session도 같은 것
        // 3. User 검사는 Controller에서 이루어짐
        try {
            return sessionRepository.findByUserAndPlatform(session.getUser(), session.getPlatform())
                    .map(value -> {
                        sessionRepository.delete(value);
                        session.setSessionKey(createSessionKey(session));
                        return sessionRepository.save(session);
                    })
                    .orElseGet(() -> {
                        session.setSessionKey(createSessionKey(session));
                        return sessionRepository.save(session);
                    });

        // DB에 2개 이상의 User and platform이 검색됐을 경우
        } catch (IncorrectResultSizeDataAccessException e) {
            sessionRepository.deleteAll(sessionRepository.findAllByUser(session.getUser()));
            session.setSessionKey(createSessionKey(session));
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

    private String createSessionKey(Session session) {
        String sessionValue = Long.toString(System.currentTimeMillis()) + session.getUser().getId();
        String encodeSessionKey = encryption.encode(sessionValue);
        return encodeSessionKey.contains("/")? createSessionKey(session) : encodeSessionKey;
    }
}
