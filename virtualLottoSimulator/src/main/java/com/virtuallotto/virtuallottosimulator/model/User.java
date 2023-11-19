package com.virtuallotto.virtuallottosimulator.model;

import com.virtuallotto.virtuallottosimulator.constants.GameNumberConstants;
import com.virtuallotto.virtuallottosimulator.constants.Rank;

import java.util.List;

public class User {

    private List<Lotto> lottoTickets;
    private Payment payment;
    private WinningResult winningResult = null;

    public User(Payment payment, List<Lotto> lottoTickets) {
        this.payment = payment;
        this.lottoTickets = lottoTickets;
    }

    public List<Lotto> getLottoTickets() {
        return lottoTickets;
    }

    public int getPayment() {
        return payment.getPayment();
    }


    public WinningResult getWinningResult() {
        return winningResult;
    }

    public int getEarnedMoney() {
        int winningPrize = 0;
        for (int index = 1; index <= GameNumberConstants.NUMBER_OF_WINNING_PRIZE.getValue(); index++) {
            winningPrize += winningResult.getNumberOfPrizeFromIndex(index) * Rank.getPrizeFromIndex(index);
        }
        return winningPrize;
    }

    public float calculateRateOfReturn() {
        return 100 * (float) this.getEarnedMoney() / (float) this.payment.getPayment();
    }

}
