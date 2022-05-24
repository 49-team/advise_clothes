package com.advise_clothes.project_advise_clothes.entity;

import com.advise_clothes.project_advise_clothes.entity.User;
import com.advise_clothes.project_advise_clothes.entity.config.SessionType;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@ToString
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sessionKey;

    @Enumerated(EnumType.STRING)
    private SessionType platform;

    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne
    @ToString.Exclude
    private User user;
}

