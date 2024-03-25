package com.advise_clothes.backend.session.controller;

import com.advise_clothes.backend.session.entity.Session;
import com.advise_clothes.backend.session.service.impl.SessionServiceImpl;
import com.advise_clothes.backend.user.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;

import static java.nio.charset.StandardCharsets.UTF_8;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/session")
public class SessionController {

    private final SessionServiceImpl sessionService;
    private final UserServiceImpl userService;

    @GetMapping("/{sessionKey}")
    public ResponseEntity<Session> getSession(@PathVariable String sessionKey) {
        Session sessionToFind = Session.builder()
                .sessionKey(URLDecoder.decode(sessionKey, UTF_8))
                .build();

        return sessionService.findByUser(sessionToFind).map(value ->
            ResponseEntity.status(HttpStatus.OK).body(value))
        .orElseGet(() ->
            ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Session()));
    }

    @PostMapping("")
    public ResponseEntity<Session> createSession(@RequestBody Session session) {
        return userService.findByUser(session.getUser()).map(user ->
                ResponseEntity.status(HttpStatus.OK).body(sessionService.create(session))
        ).orElseGet(() ->
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Session())
        );
    }

    @DeleteMapping("/{sessionKey}")
    public ResponseEntity<Session> deleteSession(@PathVariable String sessionKey) {
        Session sessionToDelete = Session.builder()
                .sessionKey(URLDecoder.decode(sessionKey, UTF_8))
                .build();

        return sessionService.findByUser(sessionToDelete).map(value ->
            ResponseEntity.status(HttpStatus.OK).body(sessionService.delete(value))
        ).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Session()));
    }
}
