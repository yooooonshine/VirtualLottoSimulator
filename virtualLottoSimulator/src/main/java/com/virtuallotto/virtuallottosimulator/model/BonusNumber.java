package com.virtuallotto.virtuallottosimulator.model;

import com.virtuallotto.virtuallottosimulator.validator.LottoValidator;

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
        LottoValidator.validateLottoNumberRange(bonusNumber);
        LottoValidator.validateDuplication(bonusNumber,WinningNumber.getWinningNumbers());
    }

}
