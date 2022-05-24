package com.advise_clothes.project_advise_clothes.service.interfaces;

import com.advise_clothes.project_advise_clothes.entity.Session;

import java.util.Optional;

public interface SessionServiceInterface {
    public Optional<Session> findBySessionKey(Session session);
    public boolean isExist(Session session);
    public Session createSession(Session session);
    public Session deleteSession(Session session);
}
