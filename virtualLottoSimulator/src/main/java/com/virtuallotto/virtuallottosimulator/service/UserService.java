package com.virtuallotto.virtuallottosimulator.service;

import com.virtuallotto.virtuallottosimulator.domain.Order;
import com.virtuallotto.virtuallottosimulator.domain.User;
import com.virtuallotto.virtuallottosimulator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public User findUser(String id) {
        return userRepository.findOne(id);
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

    @Transactional(readOnly = true)
    public List<Order> findOrderListFromLottoRound(String id, int lottoRound) {
        User user = userRepository.findOne(id);
        List<Order> orderList = user.getOrderList();
        return orderList.stream()
                .filter(order -> order.getLottoRound() == lottoRound)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<Order> findAllOrder(String id) {
        User user = userRepository.findOne(id);
        return user.getOrderList();
    }
}
