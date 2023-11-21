package com.virtuallotto.virtuallottosimulator.repository;

import com.virtuallotto.virtuallottosimulator.domain.User;
import com.virtuallotto.virtuallottosimulator.repository.UserRepository;
import com.virtuallotto.virtuallottosimulator.service.SHA256;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("유저 id 저장 테스트")
    @Transactional
    public void 유저_id_테스트() {
        //give
        SHA256 sha256 = new SHA256();
        String hashedPassword = sha256.encrypt("abcdedf123");

        User user = new User();
        user.setId("abcde");
        user.setPassword(hashedPassword);

        //when
        String savedId = userRepository.save(user);
        User findUser = userRepository.find(savedId);

        // then
        Assertions.assertThat(findUser.getId()).isEqualTo(user.getId());

    }

    @Test
    @DisplayName("유저 패스워드 저장 테스트")
    @Transactional
    public void 유저_패스워드_테스트() {
        //give
        SHA256 sha256 = new SHA256();
        String hashedPassword = sha256.encrypt("abcdedf123");

        User user = new User();
        user.setId("abcde");
        user.setPassword(hashedPassword);

        //when
        String savedId = userRepository.save(user);
        User findUser = userRepository.find(savedId);

        // then
        Assertions.assertThat(findUser.getPassword()).isEqualTo(user.getPassword());

    }
}