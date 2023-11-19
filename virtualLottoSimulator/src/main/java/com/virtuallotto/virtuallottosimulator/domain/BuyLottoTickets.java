package com.virtuallotto.virtuallottosimulator.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class BuyLottoTickets {

    @EmbeddedId
    private BuyLottoTicketsId buyLottoTicketsId;


    private int lottoRound;

    private int purchaseAmount;

    private String lottoNumber;

    @MapsId("userId") //BuyLottoTicketsId.userId 와 매핑
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
