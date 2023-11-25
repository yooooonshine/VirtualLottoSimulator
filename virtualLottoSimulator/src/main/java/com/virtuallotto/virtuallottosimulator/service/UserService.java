package com.virtuallotto.virtuallottosimulator.service;

import com.virtuallotto.virtuallottosimulator.domain.User;
import com.virtuallotto.virtuallottosimulator.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final String ID_IS_ALREADY_EXISTS = "이미 존재하는 ID 입니다.";

    private final UserRepository userRepository;
    private final SHA256 sha256;


    @Transactional
    @Synchronized
    public String join(String id, String password) {
        userDuplicationValidator(id);

        String encryptedPassword = sha256.encrypt(password);

        User user = User.createUser(id, encryptedPassword);
        userRepository.save(user);
        return user.getId();
    }

    private void userDuplicationValidator(String id) {
        if (userRepository.findOne(id) != null) {
            throw new IllegalStateException();
        }
    }

    @Transactional(readOnly = true)
    public boolean checkLogin(String id, String password) {
        User user = userRepository.findOne(id);
        if (user == null) {
            return false;
        }
        String encryptedPassword = sha256.encrypt(password);
        return user.getPassword().equals(encryptedPassword);
    }


}
