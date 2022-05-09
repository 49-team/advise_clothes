package com.advise_clothes.project_advise_clothes.repository;

import com.advise_clothes.project_advise_clothes.entity.User;
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
    Optional<User> findByIdAndPasswordAndDeletedReason(Long id, String password, int deletedReason);
    Optional<User> findByAccountAndDeletedReason(String account, int deletedReason);
    Optional<User> findByPhoneNumberAndDeletedReason(String phoneNumber, int deletedReason);
    Optional<User> findByEmailAndDeletedReason(String email, int deletedReason);
    Optional<User> findByAccountAndPasswordAndDeletedReason(String account, String password, int deletedReason);
    Optional<User> findByPhoneNumberAndPasswordAndDeletedReason(String phoneNumber, String password, int deletedReason);
    Optional<User> findByEmailAndPasswordAndDeletedReason(String email, String password, int deletedReason);
}
