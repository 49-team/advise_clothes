package com.advise_clothes.backend.user.service.impl;

import com.advise_clothes.backend.user.entity.User;
import com.advise_clothes.backend.user.repository.UserRepository;
import com.advise_clothes.backend.security.Encryption;
import com.advise_clothes.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Encryption encryption;
    private final int NO_DELETE = 0;

    /**
     * 회원가입
     * @param user
     * @return
     */
    public User create(User user) {
        // 비밀번호 소문자, 숫자, 특수문자 들어갔는지 체크
        user.setPassword(encryptPassword(user.getPassword()));
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
     * 이걸 public으로 하는 게 맞을까..?
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
        User userToFind = User.builder()
                .account(user.getAccount())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .build();
        return findByUser(userToFind).filter(value -> value.getDeletedReason() == NO_DELETE);
    }

    /**
     * 유저 정보 변경
     * @param user
     * @return
     */
    public User update(User user) {
        return userRepository.findByIdAndDeletedReason(user.getId(), NO_DELETE).map(value -> {
                value.setPassword(encryptPassword(user.getPassword()));
                return userRepository.save(value);
            }).orElseGet(User::new);
    }

    public User delete(User user) {
        return userRepository.findByIdAndDeletedReason(user.getId(), NO_DELETE).map(value -> {
            value.setDeletedReason(1);
            return userRepository.save(value);
        }).orElseGet(User::new);
    }

    private String encryptPassword(String password) {
        return encryption.encode(password);
    }
}
