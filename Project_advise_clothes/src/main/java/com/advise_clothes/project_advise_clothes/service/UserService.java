package com.advise_clothes.project_advise_clothes.service;

import com.advise_clothes.project_advise_clothes.entity.User;
import com.advise_clothes.project_advise_clothes.repository.UserRepository;
import com.advise_clothes.project_advise_clothes.service.security.Encryption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final Encryption encryption;
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
        // 비밀번호 소문자, 숫자, 특수문자 들어갔는지 체크
        user.setPassword(encryptPassword(user.getPassword()));
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
     * 데이터 중복 체크
     * @param user 'account' or 'phoneNumber' or 'email' or 'nickname' 중 하나가 들어있는 User
     * @return 검색 결과
     */
    public Optional<User> findByUser(User user) {
        return user.getId() != null ? userRepository.findById(user.getId()) :
                user.getAccount() != null ? userRepository.findByAccount(user.getAccount()) :
                user.getPhoneNumber() != null ? userRepository.findByPhoneNumber(user.getPhoneNumber()) :
                user.getEmail() != null ? userRepository.findByEmail(user.getEmail()) :
                user.getNickname() != null ? userRepository.findByNickname(user.getNickname()) :
                Optional.empty();
    }

    /**
     * 사용중인(탈퇴하지 않은) 유저 찾기
     * @param user 'id' or 'account' or 'phoneNumber' or 'email' 중 하나가 들어있는 User
     * @return 검색 결과
     * @author 임리을
     */
    public Optional<User> findByUserForNotDelete(User user) {
        // nickname으론 검색 x
        User userToFind = User.builder().id(user.getId()).account(user.getAccount()).phoneNumber(user.getPhoneNumber()).email(user.getEmail()).build();
        return findByUser(userToFind).filter(value -> value.getDeletedReason() == NO_DELETE);

        // 위에 코드가 잘 작동하면 삭제
//        return user.getId() != null ? userRepository.findByIdAndDeletedReason(user.getId(), NO_DELETE) :
//                user.getAccount() != null ? userRepository.findByAccountAndDeletedReason(user.getAccount(), NO_DELETE) :
//                user.getPhoneNumber() != null ? userRepository.findByPhoneNumberAndDeletedReason(user.getPhoneNumber(), NO_DELETE) :
//                user.getEmail() != null ? userRepository.findByEmailAndDeletedReason(user.getEmail(), NO_DELETE) :
//                Optional.empty();
    }

    /**
     * (account or phoneNumber or email) and password로 계정 찾기 -> 로그인
     * @param user 'account' or 'phoneNumber' or 'email' 중 하나가 들어있는 User
     * @param password 비밀번호(필수)
     * @return 검색 결과
     */
    public Optional<User> findByUserForNotDelete(User user, String password) {
        user.setNickname(null);     // nickName으론 검색 X
        return findByUser(user).filter(value -> (encryption.matches(password, value.getPassword()) && value.getDeletedReason() == NO_DELETE));

        // 위에 코드가 잘 작동하면 삭제
//        return findByUser(user).map(value -> userRepository.findByIdAndPasswordAndDeletedReason(value.getId(), encryptPassword(password), NO_DELETE))
//                .orElseGet(Optional::empty);
    }

    /**
     * 유저 정보 변경
     * @param user
     * @return
     */
    public User updateUser(User user) {
        return userRepository.findByIdAndDeletedReason(user.getId(), NO_DELETE).map(value -> {
                value.setPassword(encryptPassword(user.getPassword()));
                return userRepository.save(value);
            }).orElseGet(User::new);
    }

    public User deleteUser(User user) {
        return userRepository.findByIdAndDeletedReason(user.getId(), NO_DELETE).map(value -> {
            value.setDeletedReason(user.getDeletedReason());
            return userRepository.save(value);
        }).orElseGet(User::new);
    }

    private String encryptPassword(String password) {
        return encryption.encode(password);
    }
}
