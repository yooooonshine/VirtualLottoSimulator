package com.virtuallotto.virtuallottosimulator.service;


import com.virtuallotto.virtuallottosimulator.constants.NumberConstants;
import com.virtuallotto.virtuallottosimulator.domain.Lotto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.virtuallotto.virtuallottosimulator.constants.NumberConstants.*;

@Service
@RequiredArgsConstructor
public class LottoMachine {

    private final LottoService lottoService;

    public List<Lotto> generateTickets(Long purchaseAmount) {
        int ticketAmount = purchaseAmount.intValue() / NumberConstants.LOTTO_PRICE.getValue();
        return generateLottoNumberRepeatNTimes(ticketAmount);
    }

    private List<Lotto> generateLottoNumberRepeatNTimes(int repeatNumber) {
        List<Lotto> lottoTickets = new ArrayList<>();
        for (int i = 0; i < repeatNumber; i++) {
            Lotto lotto = Lotto.createLotto(ConvertService.makeIntListToString(generateLottoNumber(NUMBER_OF_NUMBERS_TO_MATCH.getValue())));
            lottoTickets.add(lottoService.saveLotto(lotto));
        }
        return lottoTickets;
    }

    public List<Integer> generateLottoNumber(int numberOfNumbersToMatch) {
        List<Integer> lottoNumbers =pickUniqueNumbersInRange(MIN_LOTTO_NUMBER.getValue(), MAX_LOTTO_NUMBER.getValue(), numberOfNumbersToMatch);
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
