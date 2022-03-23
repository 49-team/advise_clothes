package com.advise_clothes.project_advise_clothes.service;

import com.advise_clothes.project_advise_clothes.entity.User;
import com.advise_clothes.project_advise_clothes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final int NO_DELETE = 0;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * 회원가입
     * @param user
     * @return
     */
    public User createUser(User user) {
        user.setCreatedAt(null);
        user.setUpdatedAt(null);
        user.setCreatedBy(user.getAccount());
        user.setDeletedReason(0);
        return userRepository.save(user);
    }

    /**
     * 로그인
     * @param user 'account' and 'password'가 들어있는 User 객체
     * @return
     */
    public Optional<User> findByAccountAndPassword(User user) {
        return userRepository.findByAccountAndPasswordAndDeletedReason(user.getAccount(), user.getPassword(), NO_DELETE);
    }

    /**
     * 사용중인(탈퇴하지 않은) 유저 찾기
     * @param user 'id' or 'account' or 'phoneNumber' or 'email' 중 하나가 들어있는 User
     * @return 검색 결과
     * @author 임리을
     */
    public Optional<User> findByUserForNotDelete(User user) {
        return user.getId() != null ? userRepository.findByIdAndDeletedReason(user.getId(), NO_DELETE) :
                user.getAccount() != null ? userRepository.findByAccountAndDeletedReason(user.getAccount(), NO_DELETE) :
                user.getPhoneNumber() != null ? userRepository.findByPhoneNumberAndDeletedReason(user.getPhoneNumber(), NO_DELETE) :
                user.getEmail() != null ? userRepository.findByEmailAndDeletedReason(user.getEmail(), NO_DELETE) :
                Optional.empty();
    }

    /**
     * 데이터 중복 체크
     * @param user 'account' or 'phoneNumber' or 'email' or 'nickname' 중 하나가 들어있는 User
     * @return 검색 결과
     */
    public Optional<User> findByUser(User user) {
        return user.getAccount() != null ? userRepository.findByAccount(user.getAccount()) :
                user.getPhoneNumber() != null ? userRepository.findByPhoneNumber(user.getPhoneNumber()) :
                user.getEmail() != null ? userRepository.findByEmail(user.getEmail()) :
                user.getNickname() != null ? userRepository.findByNickname(user.getNickname()) :
                Optional.empty();
    }

    /**
     * 비밀번호 찾기
     * @param user 'account' or 'phoneNumber' or 'email' 중 하나가 들어있는 User
     * @param password 비밀번호(필수)
     * @return 검색 결과
     */
    public Optional<User> findByUserForNotDelete(User user, String password) {
        return user.getAccount() != null ? userRepository.findByAccountAndPasswordAndDeletedReason(user.getAccount(), password, NO_DELETE) :
                user.getPhoneNumber() != null ? userRepository.findByPhoneNumberAndPasswordAndDeletedReason(user.getPhoneNumber(), password, NO_DELETE) :
                user.getEmail() != null ? userRepository.findByEmailAndPasswordAndDeletedReason(user.getEmail(), password, NO_DELETE) :
                Optional.empty();
    }

    /**
     * 유저 정보 변경
     * @param user
     * @return
     */
    public User updateUser(User user) {
        return userRepository.findByIdAndDeletedReason(user.getId(), NO_DELETE).map(userRepository::save).orElseGet(User::new);
    }

    public User deleteUser(User user) {
        return userRepository.findByIdAndDeletedReason(user.getId(), NO_DELETE).map(value -> {
            value.setDeletedReason(user.getDeletedReason());
            return userRepository.save(value);
        }).orElseGet(User::new);
    }
}
