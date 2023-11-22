package com.virtuallotto.virtuallottosimulator.model;


import com.virtuallotto.virtuallottosimulator.validator.LottoValidator;

import java.util.Arrays;
import java.util.List;

import static com.virtuallotto.virtuallottosimulator.validator.Validator.validateWinningNumberForm;

public class WinningNumber {
    private static List<Integer> winningNumbers = null;

    public WinningNumber(String winningNumberString) {
        validateWinningNumberForm(winningNumberString);
        List<Integer> winningNumbers = stringToIntList(winningNumberString);
        LottoValidator.validateLottoSize(winningNumbers);
        LottoValidator.validateDuplication(winningNumbers);
        LottoValidator.validateNumberRangeInLotto(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    private static List<Integer> stringToIntList(String input) {
        return Arrays.stream(input.split(","))
                .map((number) -> Integer.parseInt(number))
                .toList();
    }

    public static List<Integer> getWinningNumbers() {
        return winningNumbers;
    }
}
