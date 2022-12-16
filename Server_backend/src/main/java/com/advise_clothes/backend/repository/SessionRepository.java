package com.advise_clothes.backend.repository;

import com.advise_clothes.backend.domain.entity.Session;
import com.advise_clothes.backend.domain.entity.Session.SessionType;
import com.advise_clothes.backend.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findAllByUser(User user);
    Optional<Session> findBySessionKey(String sessionKey);
    Optional<Session> findByUserAndPlatform(User user, SessionType sessionType);
}
