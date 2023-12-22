package com.virtuallotto.virtuallottosimulator.controller;

import java.util.List;

public record InstantWinningResultDTO(
        List<List<Integer>> lottoList,
        List<Integer> winningNumber,
        int bonusNumber,
        int[] gameWinningPrizeList,
        int[][] userWinningPrizeResult,
        int totalPurchaseAmount,
        int totalWinningPrice,
        int rateOfReturn){
}
