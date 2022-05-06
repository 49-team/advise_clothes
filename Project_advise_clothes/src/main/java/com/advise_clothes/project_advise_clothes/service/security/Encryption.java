package com.advise_clothes.project_advise_clothes.service.security;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Encryption implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(rawPassword, encodedPassword);
    }
}
