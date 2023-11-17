package com.virtuallotto.virtuallottosimulator.dto;


import com.virtuallotto.virtuallottosimulator.model.Lotto;

import java.util.List;

public record LottoTicketsDTO(int ticketAmount, List<Lotto> lottoTickets) {
}
