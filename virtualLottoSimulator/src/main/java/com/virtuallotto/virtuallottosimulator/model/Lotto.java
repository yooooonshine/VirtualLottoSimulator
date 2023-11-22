package com.virtuallotto.virtuallottosimulator.model;


import com.virtuallotto.virtuallottosimulator.validator.LottoValidator;

import java.util.List;

public record Lotto(List<Integer> numbers) {
    public Lotto {
        LottoValidator.validateLottoSize(numbers);
        LottoValidator.validateDuplication(numbers);
        LottoValidator.validateNumberRangeInLotto(numbers);
    }
}
