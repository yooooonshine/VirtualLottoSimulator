package com.virtuallotto.virtuallottosimulator.model;


import com.virtuallotto.virtuallottosimulator.validator.LottoNumberValidator;

import java.util.List;

public record Lotto(List<Integer> numbers) {
    public Lotto {
        LottoNumberValidator.validateLottoSize(numbers);
        LottoNumberValidator.validateDuplication(numbers);
        LottoNumberValidator.validateNumberRangeInLotto(numbers);
    }
}
