package com.advise_clothes.backend.user.repository;

import com.advise_clothes.backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccount(String account);
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
    Optional<User> findByIdAndDeletedReason(Long id, int deletedReason);
    Optional<User> findByAccountAndPasswordAndDeletedReason(String account, String password, int deletedReason);
}
