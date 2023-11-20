package com.virtuallotto.virtuallottosimulator.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL)
    private List<Lotto> lottoList;

    private int lottoRound;

    private int purchaseAmount;

}
