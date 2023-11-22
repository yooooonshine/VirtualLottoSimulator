package com.virtuallotto.virtuallottosimulator.service;

import com.virtuallotto.virtuallottosimulator.constants.NumberConstants;
import com.virtuallotto.virtuallottosimulator.domain.Lotto;
import com.virtuallotto.virtuallottosimulator.domain.Order;
import com.virtuallotto.virtuallottosimulator.model.Payment;
import com.virtuallotto.virtuallottosimulator.repository.LottoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.virtuallotto.virtuallottosimulator.constants.NumberConstants.*;

@Service
@RequiredArgsConstructor
@Transactional
public class LottoService {

    private final LottoRepository lottoRepository;

    public void generateAndSaveLotto(int lottoRound, Order order) {
        List<Integer> lottoNUmberList = generateLottoNumber();
        String lottoNumber = makeIntListToString(lottoNUmberList);
        Lotto lotto = Lotto.createLotto(lottoRound, lottoNumber, order);
        lottoRepository.save(lotto);

    }

    @Transactional(readOnly = true)
    public List<Lotto> findAll() {
        return lottoRepository.findAll();
    }

    private List<Integer> generateLottoNumber() {
        List<Integer> lottoNumbers = pickUniqueNumbersInRange(MIN_LOTTO_NUMBER.getValue(), MAX_LOTTO_NUMBER.getValue(), NUMBER_OF_NUMBERS_TO_MATCH.getValue());
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

    private <T> List<T> shuffle(final List<T> list) {
        final List<T> result = new ArrayList<>(list);
        Collections.shuffle(result);
        return result;
    }

    private String makeIntListToString(List<Integer> lottoNumberList) {
        return String.join(",", (CharSequence) lottoNumberList);
    }
}
