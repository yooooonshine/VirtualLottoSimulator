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

    // 생성 메서드
    public static Order createOrder(User user, int purchaseAmount) {
        Order order = new Order();
        order.setUser(user);
        order.setPurchaseAmount(purchaseAmount);
        return order;
    }

    private void setUser(User user) {
        this.user = user;
        user.getOrderList().add(this);
    }

    private void setPurchaseAmount(int purchaseAmount) {
        isUnitsOfLottoPrice(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    public void addLotto(Lotto lotto) {
        lottoList.add(lotto);
        lotto.setOrder(this);
    }

    //주문 삭제
    public void cancel() {

    }

    //validator
    private  void isUnitsOfLottoPrice(int input) {
        if (input % NumberConstants.LOTTO_PRICE.getValue() == 0) {
            return;
        }
        throw new IllegalArgumentException(String.format(
                INPUT_IS_NOT_IN_UNITS_OF_LOTTO_PRICE,
                NumberConstants.LOTTO_PRICE.getValue()));
    }

}
