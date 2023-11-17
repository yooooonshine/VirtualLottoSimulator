package com.virtuallotto.virtuallottosimulator.model;

import com.virtuallotto.virtuallottosimulator.validator.LottoNumberValidator;

public class BonusNumber {
    private static int number;

    public BonusNumber(int number) {
        validator(number);
        this.number = number;
    }

    public static int getNumber() {
        return number;
    }

    private void validator(int number) {
        LottoNumberValidator.validateLottoNumberRange(number);
        LottoNumberValidator.validateDuplication(number,WinningNumber.getWinningNumbers());
    }

}
