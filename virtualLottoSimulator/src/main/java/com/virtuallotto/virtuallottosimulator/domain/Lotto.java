package com.virtuallotto.virtuallottosimulator.domain;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Lotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lotto_id")
    private Long id;

    private String lottoNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public void setOrder(Order order) {
        this.order = order;
        order.getLottoList().add(this);
    }
}
