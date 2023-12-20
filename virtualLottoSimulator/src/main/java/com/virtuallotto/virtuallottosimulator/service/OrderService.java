package com.virtuallotto.virtuallottosimulator.service;

import com.virtuallotto.virtuallottosimulator.domain.Lotto;
import com.virtuallotto.virtuallottosimulator.domain.Order;
import com.virtuallotto.virtuallottosimulator.domain.User;
import com.virtuallotto.virtuallottosimulator.repository.LottoRepository;
import com.virtuallotto.virtuallottosimulator.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final LottoMachine lottoMachine;
    private final LottoService lottoService;
    private final LottoRepository lottoRepository;
    private final OrderRepository orderRepository;

    public Long order(User user, Long purchaseAmount, Long lottoRound) {
        Order order = Order.createOrder(user, purchaseAmount, lottoRound);

        List<Lotto> lottoList = lottoMachine.generateTickets(purchaseAmount);
        lottoList.stream()
                .forEach(lotto -> order.addLotto(lotto));

        orderRepository.save(order);
        return order.getId();
    }

    public Optional<Order> findOne(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
//    //주문 취소
//    public void cancelOrder() {
//
//    }
}
