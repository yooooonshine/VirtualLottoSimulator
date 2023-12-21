package com.virtuallotto.virtuallottosimulator.model;

import java.util.List;

public class WinningAndBonusNumber {
    private List<Integer> winningNumber;
    private int bonusNumber;

    public WinningAndBonusNumber(List<Integer> winningNumber, int bonusNumber) {
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getWinningNumber() {
        return winningNumber;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}