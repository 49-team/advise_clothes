package com.advise_clothes.backend.controller;

import com.advise_clothes.backend.domain.entity.Session;
import com.advise_clothes.backend.service.SessionService;
import com.advise_clothes.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/session")
//@CrossOrigin(origins = {"http://52.79.195.60:80", "http://ec2-52-79-195-60.ap-northeast-2.compute.amazonaws.com:80"})
public class SessionController {

    private final SessionService sessionService;
    private final UserService userService;

    @GetMapping("/{sessionKey}")
    public ResponseEntity<Session> getSession(@PathVariable String sessionKey) {
        Session sessionToFind = Session.builder()
                .sessionKey(URLDecoder.decode(sessionKey, StandardCharsets.UTF_8))
                .build();

        return sessionService.findBySessionKey(sessionToFind).map(value ->
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

//        return userService.findByUser(session.getUser()).map(user -> {
//            sessionService.findBySessionKey(session).map()
//        })
    }

    @DeleteMapping("/{sessionKey}")
    public ResponseEntity<Session> deleteSession(@PathVariable String sessionKey) {
        Session sessionToDelete = Session.builder()
                .sessionKey(URLDecoder.decode(sessionKey, StandardCharsets.UTF_8))
                .build();

        return sessionService.findBySessionKey(sessionToDelete).map(value ->
            ResponseEntity.status(HttpStatus.OK).body(sessionService.delete(value))
        ).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Session()));
    }
}