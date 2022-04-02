package com.advise_clothes.project_advise_clothes.entity;

import com.advise_clothes.project_advise_clothes.entity.config.AuditingEntity;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@ToString(exclude = {"sessionList"})
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    private String password;
    private String nickname;
    private String email;
    private String phoneNumber;
    private Integer gender;
    private String area;
    private Integer height;
    private Integer weight;

    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;
    private Integer deletedReason;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Session> sessionList = new ArrayList<>();
}
