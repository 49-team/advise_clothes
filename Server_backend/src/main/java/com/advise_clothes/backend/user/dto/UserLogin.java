package com.advise_clothes.backend.user.dto;

import com.advise_clothes.backend.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLogin {

    private String account;
    private String password;

    public User toEntity() {
        return User.builder()
                .account(account)
                .password(password)
                .build();
    }

}
