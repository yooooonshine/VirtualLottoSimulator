package com.virtuallotto.virtuallottosimulator.domain;

import com.virtuallotto.virtuallottosimulator.constants.NumberConstants;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static com.virtuallotto.virtuallottosimulator.constants.NumberConstants.*;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    private static final String INPUT_IS_NOT_IN_UNITS_OF_LOTTO_PRICE = "[ERROR] 입력이 %d원 단위가 아닙니다.";
    private static final String INPUT_SHOULD_BE_POSITIVE_NUMBER = "[ERROR] 입력은 양수여야 합니다.";

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

    private Integer lottoRound;

    private Integer purchaseAmount;

    // 생성 메서드
    public static Order createOrder(User user, Integer purchaseAmount, Integer lottoRound) {
        Order order = new Order();
        order.setUser(user);
        order.setPurchaseAmount(purchaseAmount);
        order.setLottoRound(lottoRound);
        return order;
    }

    private void setUser(User user) {
        this.user = user;
        user.getOrderList().add(this);
    }

    private void setPurchaseAmount(Integer purchaseAmount) {
        isUnitsOfLottoPrice(purchaseAmount);
        isPositiveNumber(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    private void setLottoRound(Integer lottoRound) {
        this.lottoRound = lottoRound;
    }

    public void addLotto(Lotto lotto) {
        lottoList.add(lotto);
        lotto.setOrder(this);
    }

    //주문 삭제
    public void cancel() {

    }

    //validator
    private  void isUnitsOfLottoPrice(Integer input) {
        if (input % NumberConstants.LOTTO_PRICE.getValue() == 0) {
            return;
        }
        throw new IllegalArgumentException(String.format(
                INPUT_IS_NOT_IN_UNITS_OF_LOTTO_PRICE,
                NumberConstants.LOTTO_PRICE.getValue()));
    }

    private void isPositiveNumber(Integer input) {
        if (input <= 0) {
            throw new IllegalArgumentException(INPUT_SHOULD_BE_POSITIVE_NUMBER);
        }
    }

}
