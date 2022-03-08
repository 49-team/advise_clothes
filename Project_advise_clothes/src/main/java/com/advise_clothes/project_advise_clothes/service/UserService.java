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
    private final String[] NOTCHANGE_PARAM = new String[] { "account", "password", "createdAt", "createdBy" };

    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * 회원가입
     * @param user
     * @return
     */
    public User createUser(User user) {
        user.setCreatedBy(user.getAccount());
        user.setDeletedReason(0);
        return userRepository.save(user);
    }

    /**
     * 로그인
     */
    public Optional<User> findUserByIdAndPassword(User user) {
        return userRepository.findByAccountAndPasswordAndDeletedReason(user.getAccount(), user.getPassword(), NO_DELETE);
    }

    /**
     * 사용중인(탈퇴하지 않은) 유저 찾기
     * @param user 'id' or 'account' or 'phoneNumber' or 'email' 중 하나가 들어있는 User 객체
     * @return success : 찾은 유저, fail : new User
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
     * 유저 정보 변경
     * @param user
     * @return
     */
    public User updateUser(User user) {
        return userRepository.findByIdAndDeletedReason(user.getId(), NO_DELETE).map(userRepository::save).orElseGet(User::new);
    }
}
