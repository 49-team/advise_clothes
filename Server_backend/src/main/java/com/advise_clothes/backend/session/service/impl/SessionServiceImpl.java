package com.advise_clothes.backend.session.service.impl;

import com.advise_clothes.backend.security.Encryption;
import com.advise_clothes.backend.session.dto.SessionCreate;
import com.advise_clothes.backend.session.dto.SessionSearch;
import com.advise_clothes.backend.session.entity.Session;
import com.advise_clothes.backend.session.repository.SessionRepository;
import com.advise_clothes.backend.session.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final Encryption encryption;

    /**
     * sesstionKey를 통해 session 정보 가져오기
     * @param session
     * @return
     */
    public Optional<Session> findByUser(SessionSearch sessionSearch) {
        return sessionRepository.findBySessionKey(sessionSearch.getSessionKey());
    }

    /**
     * 세션이 존재하는지 확인
     * @param session
     * @return
     */
    public boolean isExist(Session session) {
        return sessionRepository.findBySessionKey(session.getSessionKey()).isPresent();
    }

    public Session create(SessionCreate sessionCreate) {
        return null;
    }

    /**
     *
     * @param session
     * @return 새로운 Session 생성(생성된 Session), 이미 Session 발급(생성자), 없는 회원(Session.id = -1L)
     */
    public Session create(Session session) {
        // 1. 재로그인 경우 기존 세션 지우기(id and platform 동일)
        // 2. 동일한 sessionKey 있는지 검사 - sessionKey가 같으면 session도 같은 것
        // 3. User 검사는 Controller에서 이루어짐 -> 이 함수에서 검사하는 게 좋을 거 같음
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
        // TODO: Exception 을 핸들링할 게 아니라 Exception 이 일어나지 않게 하고 핸들링.
        } catch (IncorrectResultSizeDataAccessException e) {
            sessionRepository.deleteAll(sessionRepository.findAllByUser(session.getUser()));
            session.setSessionKey(createSessionKey(session));
            return sessionRepository.save(session);
        }
    }

    public Session delete(Session session) {
        return sessionRepository.findBySessionKey(session.getSessionKey()).map(value -> {
            sessionRepository.delete(value);
            // 나중에 Log 기록 등 처리
            return value;
        })
                // TODO: 빈 객체를 반환하지 않고 Exception 처리. -> SessionNotFound
                .orElseGet(Session::new);

    }

    private String createSessionKey(Session session) {
        String sessionValue = Long.toString(System.currentTimeMillis()) + session.getUser().getId();
        String encodeSessionKey = encryption.encode(sessionValue);

        // sessionKey에 '/'가 포함되어 있으면 다시 생성
        return encodeSessionKey.contains("/")? createSessionKey(session) : encodeSessionKey;
    }
}
