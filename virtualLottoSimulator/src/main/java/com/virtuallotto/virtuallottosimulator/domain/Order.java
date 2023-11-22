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
@AllArgsConstructor
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
    private List<Lotto> lottoList;


    private int purchaseAmount;

    protected Order() {
        this(null, 0);
    }

    @Builder(access = AccessLevel.PRIVATE)
    private Order(User user, int purchaseAmount){
        this.user = user;
        this.purchaseAmount = purchaseAmount;
    }

//    private void setUser(User user) {
//        this.user = user;
//    }

    private void setPurchaseAmount(int purchaseAmount) {
        isUnitsOfLottoPrice(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    public void addLotto(Lotto lotto) {
        lottoList.add(lotto);
        lotto.setOrder(this);
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

    public static Order createOrder(User user, int purchaseAmount) {
        return Order.builder()
                .user(user)
                .purchaseAmount(purchaseAmount)
                .build();
    }

    //주문 삭제
    public void cancel() {

    }

}
