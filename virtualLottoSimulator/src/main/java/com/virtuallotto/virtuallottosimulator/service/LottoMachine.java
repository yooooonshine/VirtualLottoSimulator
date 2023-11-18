package com.virtuallotto.virtuallottosimulator.service;


import com.virtuallotto.virtuallottosimulator.constants.GameNumberConstants;
import com.virtuallotto.virtuallottosimulator.model.Lotto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class LottoMachine {

    public List<Lotto> generateTickets(int payment) {
        int ticketAmount = payment / GameNumberConstants.LOTTO_PRICE.getValue();
        return generateLottoNumberRepeatNTimes(ticketAmount);
    }

    private List<Lotto> generateLottoNumberRepeatNTimes(int repeatNumber) {
        List<Lotto> lottoTickets = new ArrayList<>();
        for (int i = 0; i < repeatNumber; i++) {
            lottoTickets.add(new Lotto(generateLottoNumber()));
        }
        return lottoTickets;
    }

    private List<Integer> generateLottoNumber() {
        List<Integer> lottoNumbers =pickUniqueNumbersInRange(1, 45, 6);
        return lottoNumbers.stream().sorted().collect(Collectors.toList());
    }

    private List<Integer> pickUniqueNumbersInRange(
            final int startInclusive,
            final int endInclusive,
            final int count
    ) {
        validateRange(startInclusive, endInclusive);
        validateCount(startInclusive, endInclusive, count);
        final List<Integer> numbers = new ArrayList<>();
        for (int i = startInclusive; i <= endInclusive; i++) {
            numbers.add(i);
        }
        return shuffle(numbers).subList(0, count);
    }

    private void validateRange(final int startInclusive, final int endInclusive) {
        if (startInclusive > endInclusive) {
            throw new IllegalArgumentException("startInclusive cannot be greater than endInclusive.");
        }
        if (endInclusive == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("endInclusive cannot be greater than Integer.MAX_VALUE.");
        }
        if (endInclusive - startInclusive >= Integer.MAX_VALUE) {
            throw new IllegalArgumentException("the input range is too large.");
        }
    }

    private void validateCount(final int startInclusive, final int endInclusive, final int count) {
        if (count < 0) {
            throw new IllegalArgumentException("count cannot be less than zero.");
        }
        if (endInclusive - startInclusive + 1 < count) {
            throw new IllegalArgumentException("count cannot be greater than the input range.");
        }
    }

    private  <T> List<T> shuffle(final List<T> list) {
        final List<T> result = new ArrayList<>(list);
        Collections.shuffle(result);
        return result;
    }
}
