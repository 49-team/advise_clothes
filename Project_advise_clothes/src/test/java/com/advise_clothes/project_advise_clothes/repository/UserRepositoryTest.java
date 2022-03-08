package com.advise_clothes.project_advise_clothes.repository;

import com.advise_clothes.project_advise_clothes.ProjectAdviseClothesApplicationTests;
import com.advise_clothes.project_advise_clothes.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserRepositoryTest extends ProjectAdviseClothesApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void read() {
        User user = userRepository.findByIdAndDeletedReason(1L, 0).orElse(new User());
        System.out.println(user);
    }

    @Test
    public void create() {
        String count;
        List<User> userList = new ArrayList<>();

        for (int i=1; i<100; i++) {
            count = ( i < 10 )? "00" + i : "0" + i;

            User user = User.builder()
                            .account("testAccount" + count)
                            .password("p" + count)
                            .nickname("테스트계정" + count)
                            .email("test" + count + "@test.test")
                            .phoneNumber(count + "-0000-0000")
                            .gender( (int) Math.round(Math.random()) +1 )
                            .height( (int) Math.round(Math.random() * 25) + 160 )
                            .weight( (int) Math.round(Math.random() * 60) + 45 )
                            .createdBy("system")
                            .deletedReason(0)
                            .build();

            userList.add(user);
        }

        userRepository.saveAll(userList);
    }

    @Test
    public void delete() {
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            userRepository.delete(user);
            System.out.println(user.getAccount() + " 를 지웠습니다.");
        }
    }

    @Test
    @Transactional
    public void add() {
        User user = User.builder()
                        .account("ju02")
                        .password("juang")
                        .nickname("주앵")
                        .email("ju02@naver.com")
                        .phoneNumber("010-0202-0202")
                        .gender(2)
                        .height(181)
                        .weight(69)
                        .createdBy("system")
                        .build();

        User newUser = userRepository.save(user);
        System.out.println(newUser);
    }

    @Test
    public void getUser() {
        int NO_DELETE = 0;

        User user = User.builder().account("testAccount005").build();
        System.out.println(user.toString());

        Optional<User> findUser = userRepository.findByAccountAndDeletedReason(user.getAccount(), NO_DELETE);
        System.out.println(findUser);
    }

    @Test
    public void findByTest() {
        int NO_DELETE = 0;
        User failUser = User.builder().account("Fail").build();
        User exampleUser = User.builder()
                .id(65L)
                .account("testAccount001")
                .phoneNumber("012-0000-0000")
                .email("test002@test.test").build();

        User userById = userRepository.findByIdAndDeletedReason(exampleUser.getId(), NO_DELETE).orElse(failUser);
        User userByAccount = userRepository.findByAccountAndDeletedReason(exampleUser.getAccount(), NO_DELETE).orElse(failUser);
        User userByPhoneNumber = userRepository.findByPhoneNumberAndDeletedReason(exampleUser.getPhoneNumber(), NO_DELETE).orElse(failUser);
        User userByEmail = userRepository.findByEmailAndDeletedReason(exampleUser.getEmail(), NO_DELETE).orElse(failUser);

        System.out.println(userById.getAccount());
        System.out.println(userByAccount.getAccount());
        System.out.println(userByPhoneNumber.getAccount());
        System.out.println(userByEmail.getAccount());
    }

    @Test
    public void updateTest() {
        User saveUser = User.builder()
                .id(99L)
                .account("testAccount100")
                .phoneNumber("099-0000-0000")
                .email("test099@test.test").build();
        System.out.println(saveUser);

        User user = userRepository.findByIdAndDeletedReason(saveUser.getId(), 0).orElse(new User());
        System.out.println(user);

        userRepository.save(User.builder().id(user.getId())
                .account(saveUser.getAccount())
                .nickname(user.getNickname())
                .email(saveUser.getEmail())
                .phoneNumber(saveUser.getPhoneNumber())
                .area(user.getArea())
                .height(user.getHeight())
                .weight(user.getWeight())
                .createdBy(user.getCreatedBy())
                .createdAt(user.getCreatedAt())
                .password(user.getPassword()).build());
    }

    @Test
    public void objectToMap() throws Exception {
        // Optional.map을 쓰면 어떤 값이 나올까?

    }
}
