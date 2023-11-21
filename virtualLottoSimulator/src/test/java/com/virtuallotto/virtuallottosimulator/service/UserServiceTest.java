package com.virtuallotto.virtuallottosimulator.service;

import com.virtuallotto.virtuallottosimulator.domain.User;
import com.virtuallotto.virtuallottosimulator.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Test
    @DisplayName("회원가입 테스트")
    @Transactional
    public void 회원가입_테스트() {
        //give
        String id  = "abcd";
        String password = "abcdedf123";



        //when
        String savedId = userService.join(id, password);
        User findUser = userRepository.findOne(savedId);

        // then
        Assertions.assertThat(findUser.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("이미 존재하는 id로 회원가입하려고 하면 안된다.")
    @Transactional
    public void 회원가입_중복_테스트() {
        //give
        String id1  = "abcd";
        String password1 = "abcdedf123";
        String id2 = "abcd";
        String password2 = "abcdedf1234";

        //when
        String savedId = userService.join(id1, password1);

        //when, then
        Assertions.assertThatThrownBy(()-> userService.join(id2, password2))
                .isInstanceOf(IllegalStateException.class);


        // then
    }

    @Test
    @DisplayName("로그인 테스트")
    @Transactional
    public void 로그인_테스트() {
        //give
        String id = "abcd";
        String password = "abcd1234";

        //when
        userService.join(id, password);

        // then
        assertEquals(true, userService.checkLogin(id, password));
    }
}