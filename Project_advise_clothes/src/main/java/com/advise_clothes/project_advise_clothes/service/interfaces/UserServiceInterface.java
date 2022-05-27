package com.advise_clothes.project_advise_clothes.service.interfaces;

import com.advise_clothes.project_advise_clothes.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    public List<User> findAll();
    public Optional<User> findByAccountAndPassword(User user);
    public Optional<User> findByUser(User user);
    public Optional<User> findByUserForNotDelete(User user);
    public Optional<User> findByUserForNotDelete(User user, String password);

    public User create(User user);
    public User update(User user);
    public User delete(User user);
}
