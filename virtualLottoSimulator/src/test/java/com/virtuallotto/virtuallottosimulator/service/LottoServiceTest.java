package com.virtuallotto.virtuallottosimulator.service;

import com.virtuallotto.virtuallottosimulator.domain.Lotto;
import com.virtuallotto.virtuallottosimulator.domain.User;
import com.virtuallotto.virtuallottosimulator.repository.LottoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class LottoServiceTest {

    @Autowired
    LottoRepository lottoRepository;
    @Autowired
    LottoService lottoService;

    @Test
    @DisplayName("로또 저장을 하였을 떄 정상적으로 저장되는 지 테스트한다.")
    public void 로또_발행_테스트() {
        //give
        int lottoRound  = 10;
        String lottoNumber = "1,2,3,4,5,6";

        //when
        Lotto lotto = Lotto.createLotto(lottoRound, lottoNumber);
        Long savedLottoId = lottoService.saveLotto(lotto).getId();

        // then
        Assertions.assertEquals(lotto.getId(), savedLottoId);
    }

    @Test
    @DisplayName("로또 숫자 범위에 맞지 않는 숫자를 저장하려 할 때 예외가 나는 지 테스트한다.")
    @ValueSource(strings = {"1,2,3,4,5,46", "0,1,2,3,4,5"})
    public void 로또_범위_테스트(String lottoNumber) {

        //when, then
        assertThatThrownBy(() -> Lotto.createLotto(lottoNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("중복 숫자를 저장하려 할 때 예외가 나는 지 테스트한다.")
    @ValueSource(strings = {"1,2,3,4,5,5", "1,1,1,3,4,5", "1,2,3,1,4,5"})
    public void 로또_숫자_중복_테스트(String lottoNumber) {

        //when, then
        assertThatThrownBy(() -> Lotto.createLotto(lottoNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

}