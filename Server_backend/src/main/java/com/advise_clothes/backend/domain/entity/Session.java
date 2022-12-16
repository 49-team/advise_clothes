package com.advise_clothes.backend.domain.entity;

import com.advise_clothes.backend.config.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Session extends BaseEntity {

    private String sessionKey;

    @Enumerated(EnumType.STRING)
    private SessionType platform;

    @ManyToOne
    @ToString.Exclude
    private User user;

    @Builder
    public Session(String sessionKey, SessionType platform, User user) {
        this.sessionKey = sessionKey;
        this.platform = platform;
        this.user = user;
    }

    public enum SessionType {
        BROWSER,
        MOBILE,
        DESKTOP;
    }
}

