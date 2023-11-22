package com.virtuallotto.virtuallottosimulator.service;


import com.virtuallotto.virtuallottosimulator.constants.NumberConstants;
import com.virtuallotto.virtuallottosimulator.constants.Rank;
import com.virtuallotto.virtuallottosimulator.domain.Lotto;
import com.virtuallotto.virtuallottosimulator.model.WinningAndBonusNumber;
import com.virtuallotto.virtuallottosimulator.model.WinningResult;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LottoCalculator {

//    public WinningResult calculateCustomerWinningResult(User user) {
//        WinningResult winningResult = new WinningResult();
//        for (Lotto lotto : user.getLottoTickets()) {
//            int numberOfMatchingNumbers = findNumberOfCommonElements(lotto.getLottoNumber(), WinningAndBonusNumber.getWinningNumber());
//            int hasBonusNumber = 0;
//            if (numberOfMatchingNumbers == Rank.getNumberOfMatchesRequiredFromIndex(2)) {
//                hasBonusNumber = hasBonusNumberInLotto(WinningAndBonusNumber.getBonusNumber(), lotto.getLottoNumber());
//            }
//            winningResult.addNumberOfPrizeFromIndex(getIndexFromConditions(numberOfMatchingNumbers, hasBonusNumber));
//        }
//        return winningResult;
//    }
//
//    //로또 숫자 범위만큼의 배열을 만들어서 배열에 로또 번호랑, 당첨번호에 해당하는 숫자 각각 +1하여 결론적으로 +2된 인덱스 개수를 리턴한다.
//    private int findNumberOfCommonElements(List<Integer> firstList, List<Integer> secondList) {
//        int[] array = new int[NumberConstants.MAX_LOTTO_NUMBER.getValue() + 1]; //0번 인덱스는 제외
//        firstList.stream().forEach(number -> array[number]++);
//        secondList.stream().forEach(number -> array[number]++);
//        return (int) Arrays.stream(array).filter(number -> number == 2).count();
//    }
//
//    private int hasBonusNumberInLotto(int bonusNumber, List<Integer> lottoNumbers) {
//        if (lottoNumbers.stream().anyMatch(number -> number == bonusNumber)) {
//            return 1;
//        }
//        return -1;
//    }
//
//    private int getIndexFromConditions(int numberOfMatchingNumbers, int hasBonusNumber) {
//        return Rank.getIndexFromConditions(numberOfMatchingNumbers, hasBonusNumber);
//    }
//
//    public float calculateRateOfReturn() {
//        return 100 * (float) this.getEarnedMoney() / (float) this.payment.getPayment();
//    }
//
//    public int getEarnedMoney() {
//        int winningPrize = 0;
//        for (int index = 1; index <= NumberConstants.NUMBER_OF_WINNING_PRIZE.getValue(); index++) {
//            winningPrize += winningResult.getNumberOfPrizeFromIndex(index) * Rank.getPrizeFromIndex(index);
//        }
//        return winningPrize;
//    }
}
