package com.virtuallotto.virtuallottosimulator.service;

import com.virtuallotto.virtuallottosimulator.constants.GameNumberConstants;
import com.virtuallotto.virtuallottosimulator.constants.Rank;
import com.virtuallotto.virtuallottosimulator.dto.LottoTicketsDTO;
import com.virtuallotto.virtuallottosimulator.dto.WinningStatisticsDTO;
import com.virtuallotto.virtuallottosimulator.model.WinningResult;
import org.springframework.stereotype.Service;

@Service
public class DTOService {
    private static final String BONUS_BALL_MATCH = ", 보너스 볼 일치";

    public WinningStatisticsDTO getWinningStatisticsDTO(WinningResult winningResult, float rateOfReturn) {
        String[][] winningStatisticsStrings = new String[GameNumberConstants.NUMBER_OF_WINNING_PRIZE.getValue() + 1][4];

        for (int index = 1; index <= GameNumberConstants.NUMBER_OF_WINNING_PRIZE.getValue(); index++) {
            winningStatisticsStrings[index] = new String[]{
                    Integer.toString(Rank.getNumberOfMatchesRequiredFromIndex(index)),
                    checkBonusBallString(Rank.getHasBonusNumberFromIndex(index)),
                    String.valueOf(Rank.getPrizeFromIndex(index)),
                    Integer.toString(winningResult.getNumberOfPrizeFromIndex(index))};
        }
        return new WinningStatisticsDTO(winningStatisticsStrings, rateOfReturn);
    }

    public static String checkBonusBallString(int hasBonusNumber) {
        if (hasBonusNumber == 1) {
            return BONUS_BALL_MATCH;
        }
        return "";
    }
}
