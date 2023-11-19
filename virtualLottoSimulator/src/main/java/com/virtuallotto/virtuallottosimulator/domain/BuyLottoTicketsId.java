package com.virtuallotto.virtuallottosimulator.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class BuyLottoTicketsId implements Serializable {

    private String userId; //@MapsId("userId")로 매핑

    @Column(name = "lotto_ticket_id")
    private long id; //BuyLottoTickets.buyLottoTicketsId와 매핑
}
