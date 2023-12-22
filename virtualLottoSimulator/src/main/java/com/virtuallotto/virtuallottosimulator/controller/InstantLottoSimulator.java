package com.virtuallotto.virtuallottosimulator.controller;

import com.virtuallotto.virtuallottosimulator.constants.NumberConstants;
import com.virtuallotto.virtuallottosimulator.domain.Lotto;
import com.virtuallotto.virtuallottosimulator.domain.Order;
import com.virtuallotto.virtuallottosimulator.domain.User;
import com.virtuallotto.virtuallottosimulator.model.InstantWinningResult;
import com.virtuallotto.virtuallottosimulator.model.WinningAndBonusNumber;
import com.virtuallotto.virtuallottosimulator.model.WinningResult;
import com.virtuallotto.virtuallottosimulator.service.LottoMachine;
import com.virtuallotto.virtuallottosimulator.service.LottoService;
import com.virtuallotto.virtuallottosimulator.service.OrderService;
import com.virtuallotto.virtuallottosimulator.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.virtuallotto.virtuallottosimulator.constants.NumberConstants.*;

@Controller
@RequestMapping("/instantLottoSimulator")
@RequiredArgsConstructor
public class InstantLottoSimulator {

    private final LottoMachine lottoMachine;

    @PostMapping("/purchaseAmount")
    @ResponseBody
    public Object order(
            HttpServletRequest request)
    {
        Long purchaseAmount = Long.valueOf(request.getParameter("purchaseAmount"));
        int purchaseLottoSize = (int) (purchaseAmount / LOTTO_PRICE.getValue());

        List<List<Integer>> lottoList = new ArrayList<>();
        for (int i = 0; i < purchaseLottoSize; i++) {
            lottoList.add(lottoMachine.generateLottoNumber());
        }

        List<Integer> randomWinningAndBonusNumber = lottoMachine.generateLottoNumber(7);

        List<Integer> winningNumber = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            winningNumber.add(randomWinningAndBonusNumber.get(i));
        }

        int bonusNumber = randomWinningAndBonusNumber.get(6);

        WinningAndBonusNumber winningAndBonusNumber = new WinningAndBonusNumber(winningNumber, bonusNumber);
        int[] gameWinningPrizeList = {0,200000000,10000000,5000000,10000,5000};



        InstantWinningResult instantWinningResult = new InstantWinningResult(winningAndBonusNumber, gameWinningPrizeList, lottoList);

        InstantWinningResultDTO instantWinningResultDTO = new InstantWinningResultDTO(
                instantWinningResult.getLottoList(),
                instantWinningResult.getWinningNumber(),
                instantWinningResult.getBonusNumber(),
                instantWinningResult.getGameWinningPrizeList(),
                instantWinningResult.getUserWinningPrizeResult(),
                instantWinningResult.getTotalPurchaseAmount(),
                instantWinningResult.getEarnedMoney(),
                instantWinningResult.getRateOfReturn());
        return instantWinningResultDTO;

    }

}
