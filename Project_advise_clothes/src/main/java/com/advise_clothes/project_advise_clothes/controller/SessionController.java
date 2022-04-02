package com.advise_clothes.project_advise_clothes.controller;

import com.advise_clothes.project_advise_clothes.entity.Session;
import com.advise_clothes.project_advise_clothes.entity.User;
import com.advise_clothes.project_advise_clothes.entity.config.SessionType;
import com.advise_clothes.project_advise_clothes.service.SessionService;
import com.advise_clothes.project_advise_clothes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/session")
public class SessionController {

    private final SessionService sessionService;
    private final UserService userService;

    @GetMapping("/{sessionKey}")
    public ResponseEntity<Session> getSession(@RequestParam String sessionKey) {
        Session sessionToFind = Session.builder().sessionKey(sessionKey).build();

        return sessionService.getSession(sessionToFind).map(value ->
            ResponseEntity.status(HttpStatus.OK).body(value))
        .orElseGet(() ->
            ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Session()));
    }

    @PostMapping("")
    public ResponseEntity<Session> createSession(@RequestBody Session session) {
        User userToFind = User.builder().id(session.getUser().getId()).build();

        return userService.findByUser(userToFind).map(value ->
            ResponseEntity.status(HttpStatus.OK).body(sessionService.createSession(Session.builder().user(value).platform(session.getPlatform()).build())))
        .orElseGet(() ->
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Session()));
    }

    @DeleteMapping("/{sessionKey}")
    public ResponseEntity<Session> deleteSession(@PathVariable String sessionKey) {
        Session sessionToDelete = Session.builder().sessionKey(sessionKey).build();

        return sessionService.getSession(sessionToDelete).map(value ->
            ResponseEntity.status(HttpStatus.OK).body(sessionService.deleteSession(value))
        ).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Session()));
    }
}
