package com.advise_clothes.backend.user.entity;

import com.advise_clothes.backend.config.BaseEntity;
import com.advise_clothes.backend.session.entity.Session;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
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

    private Integer deletedReason;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @ToString.Exclude
//    @JsonBackReference
    @JsonIgnore
//    @Builder.Default
    private List<Session> sessionList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(account, user.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account);
    }

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
