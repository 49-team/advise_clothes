package com.advise_clothes.project_advise_clothes.repository;

import com.advise_clothes.project_advise_clothes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdAndDeletedReason(Long id, int deletedReason);
    Optional<User> findByAccountAndDeletedReason(String account, int deletedReason);
    Optional<User> findByPhoneNumberAndDeletedReason(String phoneNumber, int deletedReason);
    Optional<User> findByEmailAndDeletedReason(String email, int deletedReason);
    Optional<User> findByAccountAndPasswordAndDeletedReason(String account, String password, int deletedReason);
}
