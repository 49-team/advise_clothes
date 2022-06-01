package com.advise_clothes.project_advise_clothes.service;

import com.advise_clothes.project_advise_clothes.ProjectAdviseClothesApplicationTests;
import com.advise_clothes.project_advise_clothes.entity.User;
<<<<<<< HEAD
=======
import com.advise_clothes.project_advise_clothes.repository.UserRepository;
import com.advise_clothes.project_advise_clothes.service.implement.UserService;
>>>>>>> base/backend
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class UserServiceTest extends ProjectAdviseClothesApplicationTests {

    @Autowired
    private UserService userService;
<<<<<<< HEAD
=======
    @Autowired
    private UserRepository userRepository;
    private User user = userRepository.findById(1L).orElseThrow();

>>>>>>> base/backend

    @Test
    public void findAllTest() {
        System.out.println(userService.findAll());
    }

    @Test
    public void findByUserNotDeleteTest() {
        User userInId = User.builder().id(95L).build();
        User userInAccount = User.builder().account("testAccount005").build();
        User userInPhoneNumber = User.builder().phoneNumber("012-0000-0000").build();
        User userInEmail = User.builder().email("test007@test.test").build();
        int NO_DELETE = 0;

        System.out.println(userService.findByUserForNotDelete(userInId));
        System.out.println(userService.findByUserForNotDelete(userInAccount));
        System.out.println(userService.findByUserForNotDelete(userInPhoneNumber));
        System.out.println(userService.findByUserForNotDelete(userInEmail));
    }

    @Test
    @Transactional
<<<<<<< HEAD
    public void createUserTest() {
=======
    public void createTest() {
>>>>>>> base/backend
        int count = 102;
        User newUser = User.builder()
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

<<<<<<< HEAD
        System.out.println(userService.createUser(newUser));
    }

    @Test
    public void updateUserTest() throws Exception {
        User user = userService.findByUserForNotDelete(User.builder().id(99L).build()).get();
        System.out.println(user);
        user.setNickname("테스트계정100");
        System.out.println(userService.updateUser(user));
=======
        System.out.println(userService.create(newUser));
    }

    @Test
    public void updateTest() throws Exception {
        User user = userService.findByUserForNotDelete(User.builder().id(99L).build()).get();
        System.out.println(user);
        user.setNickname("테스트계정100");
        System.out.println(userService.update(user));
>>>>>>> base/backend
    }

    @Test
    public void findByUserTest() {
        System.out.println(userService.findByUser(User.builder().account("young0105").build()));
    }

    @Test
    public void findByUserForNotDelete() {
        System.out.println(userService.findByUserForNotDelete(User.builder().account("testAccount001").build(), "p001"));
    }
}
