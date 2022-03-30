package com.advise_clothes.project_advise_clothes.entity;

import com.advise_clothes.project_advise_clothes.entity.User;
import com.advise_clothes.project_advise_clothes.entity.config.AuditingEntity;
import com.advise_clothes.project_advise_clothes.entity.config.SessionType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@ToString
public class Session extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sessionKey;

    @Enumerated(EnumType.STRING)
    private SessionType platform;

    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;        // 필요한진 모르겠으나, AuditingEntity를 상속받아 구현해야 한다.

    @ManyToOne
    private User user;
}

