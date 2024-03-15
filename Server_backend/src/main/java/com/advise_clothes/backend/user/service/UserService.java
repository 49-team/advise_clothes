package com.advise_clothes.backend.user.service;

import com.advise_clothes.backend.user.entity.User;

import java.util.Optional;

public interface UserService {

    User create(User user);

    Optional<User> findByAccountAndPassword(User user);

    Optional<User> findByUser(User user);

    Optional<User> findByUserForNotDelete(User user);

    User update(User user);

    User delete(User user);

}
