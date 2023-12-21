package com.virtuallotto.virtuallottosimulator.model;


import com.virtuallotto.virtuallottosimulator.constants.NumberConstants;
import com.virtuallotto.virtuallottosimulator.constants.Rank;
import com.virtuallotto.virtuallottosimulator.domain.Lotto;

import java.util.Arrays;
import java.util.List;

import static com.virtuallotto.virtuallottosimulator.constants.NumberConstants.*;
import static com.virtuallotto.virtuallottosimulator.constants.NumberConstants.LOTTO_PRICE;

public class WinningResult {
    private List<Lotto> lottoList;
    private WinningAndBonusNumber winningAndBonusNumber;

    private int[] gameWinningPrizeList;
    private int[][] userWinningPrizeResult = new int[6][2]; //int[x][y]에서 x값 0은 사용x, 1은 1등, int[x][0]은 개수, int[x][1]은 금액

    public WinningResult(WinningAndBonusNumber winningAndBonusNumber, int[] gameWinningPrizeList, List<Lotto> lottoList) {
        this.winningAndBonusNumber = winningAndBonusNumber;
        this.gameWinningPrizeList = gameWinningPrizeList;
        this.lottoList = lottoList;
        calculateUserWinningPrizeResult();
    }

    public int getNumberOfPrizeFromIndex(int index) {
        return userWinningPrizeResult[index][0];
    }

    public void addNumberOfPrizeFromIndex(int index) {
        userWinningPrizeResult[index][0]++;
    }


    public void calculateUserWinningPrizeResult() {
        for (Lotto lotto : lottoList) {
            int numberOfMatchingNumbers = findNumberOfCommonElements(lotto.getLottoNumberIntegerList(), winningAndBonusNumber.getWinningNumber());
            int hasBonusNumber = 0;
            if (numberOfMatchingNumbers == Rank.getNumberOfMatchesRequiredFromIndex(2)) {
                hasBonusNumber = hasBonusNumberInLotto(winningAndBonusNumber.getBonusNumber(), lotto.getLottoNumberIntegerList());
            }
            addNumberOfPrizeFromIndex(getIndexFromConditions(numberOfMatchingNumbers, hasBonusNumber));
        }
        for (int i = 1; i < userWinningPrizeResult.length; i++) {
            userWinningPrizeResult[i][1] = userWinningPrizeResult[i][0] * gameWinningPrizeList[i];
        }
    }

    //로또 숫자 범위만큼의 배열을 만들어서 배열에 로또 번호랑, 당첨번호에 해당하는 숫자 각각 +1하여 결론적으로 +2된 인덱스 개수를 리턴한다.
    private int findNumberOfCommonElements(List<Integer> firstList, List<Integer> secondList) {
        int[] array = new int[MAX_LOTTO_NUMBER.getValue() + 1]; //0번 인덱스는 제외
        firstList.stream().forEach(number -> array[number]++);
        secondList.stream().forEach(number -> array[number]++);
        return (int) Arrays.stream(array).filter(number -> number == 2).count();
    }

    private int hasBonusNumberInLotto(int bonusNumber, List<Integer> lottoNumbers) {
        if (lottoNumbers.stream().anyMatch(number -> number == bonusNumber)) {
            return 1;
        }
        return -1;
    }

    private int getIndexFromConditions(int numberOfMatchingNumbers, int hasBonusNumber) {
        return Rank.getIndexFromConditions(numberOfMatchingNumbers, hasBonusNumber);
    }


    public int getRateOfReturn() {
        return -100 + 100 * getEarnedMoney() / getTotalPurchaseAmount();
    }

    public int getTotalPurchaseAmount() {
        return lottoList.size() * LOTTO_PRICE.getValue();
    }


    public int getEarnedMoney() {
        int winningPrize = 0;
        for (int index = 1; index <= NUMBER_OF_WINNING_PRIZE.getValue(); index++) {
            winningPrize += getNumberOfPrizeFromIndex(index) * gameWinningPrizeList[index];
        }
        return winningPrize;
    }

    public int[] getGameWinningPrizeList() {
        return gameWinningPrizeList;
    }

    public int[][] getUserWinningPrizeResult() {
        return userWinningPrizeResult;
    }
}
