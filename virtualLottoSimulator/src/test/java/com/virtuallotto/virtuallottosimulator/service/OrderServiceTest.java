package com.virtuallotto.virtuallottosimulator.service;

import com.virtuallotto.virtuallottosimulator.domain.Order;
import com.virtuallotto.virtuallottosimulator.domain.User;
import com.virtuallotto.virtuallottosimulator.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserService userService;

    @Test
    @DisplayName("주문을 하였을 떄 정보를 저장했는 지 테스트한다.")
    public void 주문_테스트() {
        //give
        String userId = "1234";
        String userPassword = "1234";
        userService.join(userId, userPassword);


        int purchaseAMount = 10000;
        int lottoRound = 10;
        boolean expectedResult = true;

        //when
        Long savedOrderId = orderService.order(userService.findUser(userId), purchaseAMount, lottoRound);

        List<Order> orderList = userService.findOrderListFromLottoRound(userId, lottoRound);
        boolean result = orderList.stream()
                .anyMatch(order -> order.getId().equals(savedOrderId));
        // then
        Assertions.assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @DisplayName("구입금액에 음수나 0을 넣었을 때 예외를 던지는 지 테스트한다.")
    @ValueSource(ints = {-1, 0})
    void 입력_음수테스트(int purchaseAmount) {
        //give
        String userId = "1234";
        String userPassword = "1234";
        User user = User.createUser(userId, userPassword);
        int lottoRound = 10;

        //when, then
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> orderService.order(user, purchaseAmount, lottoRound))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("로또금액 단위에 안 맞으면 예외를 던지는 지 테스트한다.")
    @ValueSource(ints = {100, 1100})
    void 입력_단위테스트(int purchaseAmount) {
        //give
        String userId = "1234";
        String userPassword = "1234";
        User user = User.createUser(userId, userPassword);
        int lottoRound = 10;

        //when, then
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> orderService.order(user, purchaseAmount, lottoRound))
                .isInstanceOf(IllegalArgumentException.class);
    }

}