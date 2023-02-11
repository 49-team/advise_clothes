package com.advise_clothes.backend.domain.entity;

import com.advise_clothes.backend.config.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@ToString
public class User extends BaseEntity {

    private String account;
    private String password;
    private String nickname;
    private String email;
    private String phoneNumber;
    private Integer gender;
    private String area;
    private Integer height;
    private Integer weight;

    private String createdBy;
    private String updatedBy;
    private Integer deletedReason;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @ToString.Exclude
//    @JsonBackReference
    @JsonIgnore
    @Builder.Default
    private List<Session> sessionList = new ArrayList<>();

    @Builder
    public User(String account, String password, String nickname, String email, String phoneNumber, Integer gender, String area, Integer height, Integer weight, String createdBy, String updatedBy, Integer deletedReason, List<Session> sessionList) {
        this.account = account;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.area = area;
        this.height = height;
        this.weight = weight;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedReason = deletedReason;
        this.sessionList = sessionList;
    }
}
