package com.virtuallotto.virtuallottosimulator.model;

import com.virtuallotto.virtuallottosimulator.validator.LottoNumberValidator;

import static com.virtuallotto.virtuallottosimulator.validator.Validator.validateBonusNumberForm;

public class BonusNumber {
    private static int bonusNumber;

    public BonusNumber(String bonusNumberInput) {
        validateBonusNumberForm(bonusNumberInput);
        int bonusNumber = Integer.parseInt(bonusNumberInput);
        validator(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public static int getBonusNumber() {
        return bonusNumber;
    }

    private void validator(int bonusNumber) {
        LottoNumberValidator.validateLottoNumberRange(bonusNumber);
        LottoNumberValidator.validateDuplication(bonusNumber,WinningNumber.getWinningNumbers());
    }

}
