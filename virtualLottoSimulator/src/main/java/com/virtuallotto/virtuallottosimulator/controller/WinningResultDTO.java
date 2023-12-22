package com.virtuallotto.virtuallottosimulator.controller;

import java.util.List;

public record WinningResultDTO(
        List<Integer> winningNumber,
        int bonusNumber,
        int[] gameWinningPrizeList,
        int[][] userWinningPrizeResult,
        int totalPurchaseAmount,
        int totalWinningPrice,
        int rateOfReturn)
{}
