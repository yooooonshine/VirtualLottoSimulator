package com.virtuallotto.virtuallottosimulator.model;

import com.virtuallotto.virtuallottosimulator.model.BonusNumber;
import com.virtuallotto.virtuallottosimulator.model.WinningNumber;
import com.virtuallotto.virtuallottosimulator.validator.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

@DisplayName("보너스 번호 입력 테스트")
public class BonusNumberTest {

    @ParameterizedTest
    @DisplayName("문자를 입력했을 때 예외를 던지는 지 테스트")
    @ValueSource(strings = {"a",","," "})
    void 문자_입력_예외_테스트(String input) {
        Assertions.assertThatThrownBy(
                () -> Validator.validateBonusNumberForm(input)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("로또 번호 범위가 주어진 범위를 넘었을 때 예외를 던지는 지 테스트")
    @ValueSource(strings = {"0","55"})
    void 로또_번호_범위_테스트(String input) {
        Assertions.assertThatThrownBy(
                () -> new BonusNumber(input)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호와 중복되면 예외를 던지는 지 테스트")
    void 로또_번호_중복_테스트() {
        // given
        String numbers = "1,2,3,4,5,6";
        WinningNumber winningNumber = new WinningNumber(numbers);
        String bonusNumber = "6";
        // when, then
        Assertions.assertThatThrownBy(
                () -> new BonusNumber(bonusNumber)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
