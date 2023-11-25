package com.virtuallotto.virtuallottosimulator.service;

import com.virtuallotto.virtuallottosimulator.domain.Lotto;
import com.virtuallotto.virtuallottosimulator.domain.Order;
import com.virtuallotto.virtuallottosimulator.repository.LottoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LottoService {

    private final LottoRepository lottoRepository;

    @Transactional
    public Lotto saveLotto(Lotto lotto) {
        lottoRepository.save(lotto);
        return lotto;
    }

    public List<Lotto> findAllFromOrder(Order order) {
        return lottoRepository.findAllFromOrder(order);
    }
}
