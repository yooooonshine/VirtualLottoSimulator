package com.virtuallotto.virtuallottosimulator.controller;

import com.virtuallotto.virtuallottosimulator.domain.Order;
import com.virtuallotto.virtuallottosimulator.domain.User;
import com.virtuallotto.virtuallottosimulator.service.LottoService;
import com.virtuallotto.virtuallottosimulator.service.OrderService;
import com.virtuallotto.virtuallottosimulator.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/purchaseLotto")
@RequiredArgsConstructor
public class PurchaseLottoController {

    private final OrderService orderService;
    private final UserService userService;
    private final LottoService lottoService;

    @PostMapping("/recentLottoRound")
    @ResponseBody
    public Object giveRecentLottoRound() {

        // 이 부분 크롤링을 이용해 작성

        int recentLottoRound = 1;
        HashMap<String, Object> map = new HashMap<>();
        map.put("recentLottoRound", recentLottoRound);
        return map;
    }

    @PostMapping("/purchaseAmount")
    @ResponseBody
    public Object order(
            OrderDTO orderDTO)
    {
        String userId = orderDTO.userId();
        Long purchaseAmount = Long.valueOf(orderDTO.purchaseAmount());
        Long lottoRound = Long.valueOf(orderDTO.lottoRound());

        User user = userService.findUser(userId);

        List<String> lottoList = null;
        if (user != null) {
            Long orderId = orderService.order(
                    user,
                    purchaseAmount,
                    lottoRound);
            Optional<Order> order =  orderService.findOne(orderId);
            if (order.isPresent()) {
                lottoList = lottoService.findAllFromOrder(order.get()).stream()
                        .map(lotto -> lotto.getLottoNumber())
                        .collect(Collectors.toList());
            }
        }

        HashMap<String, List<String>> map = new HashMap<>();
        map.put("purchasedLottoList", lottoList);
        return map;
    }

    public <T> T readUserInput(Supplier<T> read) {
        try {
            return read.get();
        } catch (IllegalArgumentException e) {
            e.getMessage();
            return readUserInput(read);
        }
    }
}
