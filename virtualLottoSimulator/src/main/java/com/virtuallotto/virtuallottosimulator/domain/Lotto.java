package com.virtuallotto.virtuallottosimulator.domain;


import com.virtuallotto.virtuallottosimulator.validator.LottoValidator;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Entity
@Getter
public class Lotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lotto_id")
    private Long id;

    private String lottoNumber;

    private int lottoRound;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private void setLottoRound(int lottoRound) {
        this.lottoRound = lottoRound;
    }

    private void setLottoNumber(String lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    private void setOrder(Order order) {
        this.order = order;
        order.getLottoList().add(this);
    }

    // 생성 메서드
    public static Lotto createLotto(int lottoRound, String lottoNumber, Order order) {
        validateLotto(lottoNumber);
        Lotto lotto = new Lotto();
        lotto.setLottoRound(lottoRound);
        lotto.setLottoNumber(lottoNumber);
        lotto.setOrder(order);
        return lotto;
    }

    private static void validateLotto(String lottoNumber) {
        List<Integer> lottoNumberList = makeStringToIntList(lottoNumber);
        LottoValidator.validateLottoSize(lottoNumberList);
        LottoValidator.validateDuplication(lottoNumberList);
        LottoValidator.validateNumberRangeInLotto(lottoNumberList);
    }

    private static List<Integer> makeStringToIntList(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .toList();
    }




}

