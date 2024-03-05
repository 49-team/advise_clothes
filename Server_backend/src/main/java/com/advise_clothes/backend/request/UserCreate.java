package com.advise_clothes.backend.request;


import com.advise_clothes.backend.domain.entity.User;
import lombok.Getter;

@Getter
public class UserCreate {

    private String account;
    private String password;
    private String nickname;
    private String email;
    private String phoneNumber;
    private Integer gender;
    private String area;
    private Integer height;
    private Integer weight;

    public boolean validate() {
        return account != null && password != null && nickname != null && email != null && phoneNumber != null;
    }

    public User toEntity() {
        return User.builder()
                .account(account)
                .password(password)
                .nickname(nickname)
                .email(email)
                .phoneNumber(phoneNumber)
                .gender(gender)
                .area(area)
                .height(height)
                .weight(weight)
                .build();
    }
}
