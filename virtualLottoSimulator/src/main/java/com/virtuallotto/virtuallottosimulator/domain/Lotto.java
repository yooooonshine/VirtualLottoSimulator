package com.virtuallotto.virtuallottosimulator.domain;


import com.virtuallotto.virtuallottosimulator.service.ConvertService;
import com.virtuallotto.virtuallottosimulator.validator.LottoValidator;
import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public void setOrder(Order order) {
        this.order = order;
        order.getLottoList().add(this);
    }

    @Builder
    private Lotto(int lottoRound, String lottoNumber) {
        this.lottoRound = lottoRound;
        this.lottoNumber = lottoNumber;
    }

    // 생성 메서드
    public static Lotto createLotto(int lottoRound, String lottoNumber) {
        validateLotto(lottoNumber);
        return Lotto.builder()
                .lottoRound(lottoRound)
                .lottoNumber(lottoNumber)
                .build();
    }

    private static void validateLotto(String lottoNumber) {
        List<Integer> lottoNumberList = ConvertService.makeStringToIntList(lottoNumber);
        LottoValidator.validateLottoSize(lottoNumberList);
        LottoValidator.validateDuplication(lottoNumberList);
        LottoValidator.validateNumberRangeInLotto(lottoNumberList);
    }






}

