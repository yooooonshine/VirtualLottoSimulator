package com.virtuallotto.virtuallottosimulator.domain;

import com.virtuallotto.virtuallottosimulator.constants.NumberConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.virtuallotto.virtuallottosimulator.constants.NumberConstants.*;

@Entity
@Table(name = "orders")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private static final String INPUT_IS_NOT_IN_UNITS_OF_LOTTO_PRICE = "[ERROR] 입력이 %d원 단위가 아닙니다.";

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Lotto> lottoList = new ArrayList<>();


    private int purchaseAmount;

    protected Order() {
    }

    private void setPurchaseAmount(int purchaseAmount) {
        isUnitsOfLottoPrice(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    private  void isUnitsOfLottoPrice(int input) {
        if (input % NumberConstants.LOTTO_PRICE.getValue() == 0) {
            return;
        }
        throw new IllegalArgumentException(String.format(
                INPUT_IS_NOT_IN_UNITS_OF_LOTTO_PRICE,
                NumberConstants.LOTTO_PRICE.getValue()));
    }



    // 생성 메서드

    public void createOrder(User User, int lottoRound, int purchaseAmount) {
        Order order = new Order();
        order.
        order.setPurchaseAmount(purchaseAmount);
    }

}
