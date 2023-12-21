package com.virtuallotto.virtuallottosimulator.controller;

import com.virtuallotto.virtuallottosimulator.domain.Lotto;
import com.virtuallotto.virtuallottosimulator.domain.Order;
import com.virtuallotto.virtuallottosimulator.model.WinningAndBonusNumber;
import com.virtuallotto.virtuallottosimulator.model.WinningResult;
import com.virtuallotto.virtuallottosimulator.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lottoPurchased")
@RequiredArgsConstructor
public class LottoPurchasedController {

    private final OrderService orderService;

    @PostMapping("/userLottoRound")
    @ResponseBody
    public Object giveUserLottoRound(HttpServletRequest request) {
        System.out.println("여긴가");
        String userId = request.getParameter("userId");
        List<Order> orderList = orderService.findByUserId(userId);
        List<Long> userLottoRoundList = orderList.stream().map(order -> order.getLottoRound())
                .distinct().toList();
        HashMap<String, Object> map = new HashMap<>();
        map.put("userLottoRoundList", userLottoRoundList);
        return map;
    }

    @PostMapping("/purchasedLottoList")
    @ResponseBody
    public Object givePurchasedLottoList(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        Long lottoRound = Long.valueOf(request.getParameter("lottoRound"));

        List<Lotto> purchasedLottoList = new ArrayList<>();
        List<Order> orderList = orderService.findByUserId(userId);
        orderList.stream()
                .filter(order -> order.getLottoRound() == lottoRound)
                .forEach(order -> purchasedLottoList.addAll(order.getLottoList()));

        List<String> purchasedLottoStringList = new ArrayList<>();
        purchasedLottoList.stream().forEach(lotto -> purchasedLottoStringList.add(lotto.getLottoNumber()));
        System.out.println("purchasedLottoList: " + purchasedLottoStringList);

        HashMap<String, Object> map = new HashMap<>();
        map.put("purchasedLottoList", purchasedLottoStringList);
        return map;
    }

    @PostMapping("/hasLottoRoundResult")
    @ResponseBody
    public Object hasLottoRoundResult(HttpServletRequest request) {
        Long lottoRound = Long.valueOf(request.getParameter("lottoRound"));

        // 이 부분에 크롤링을 통해 db에 해당하는 회차의 당첨결과가 있는 지 확인
        boolean result = true;


        HashMap<String, Object> map = new HashMap<>();
        map.put("result", result);
        return map;
    }

    @PostMapping("/hasWinningNumber")
    @ResponseBody
    public Object hasWinningNumber(HttpServletRequest request) {
        Long lottoRound = Long.valueOf(request.getParameter("lottoRound"));

        //이부분 크롤링 데이터베이스에서 로또당첨번호 있는 지 확인


        HashMap<String, Object> map = new HashMap<>();
        map.put("result", true);
        return map;
    }

    @PostMapping("/winningResult")
    @ResponseBody
    public Object giveWinningResult(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        Long lottoRound = Long.valueOf(request.getParameter("lottoRound"));
//        System.out.println("winningResult start");
        //로또 당첨번호 가져오기
        List<Integer> winningNumber = new ArrayList<>();
        winningNumber.add(1);
        winningNumber.add(2);
        winningNumber.add(3);
        winningNumber.add(4);
        winningNumber.add(5);
        winningNumber.add(6);

        int bonusNumber = 7;
        WinningAndBonusNumber winningAndBonusNumber = new WinningAndBonusNumber(winningNumber, bonusNumber);
        int[] gameWinningPrizeList = {0,200000000,10000000,5000000,10000,5000};

        List<Lotto> purchasedLottoList = new ArrayList<>();
        List<Order> orderList = orderService.findByUserId(userId);
        orderList.stream()
                .filter(order -> order.getLottoRound() == lottoRound)
                .forEach(order -> purchasedLottoList.addAll(order.getLottoList()));

        List<String> purchaseLottoStringList = new ArrayList<>();
        purchasedLottoList.stream().forEach(lotto -> purchaseLottoStringList.add(lotto.getLottoNumber()));

        WinningResult winningResult = new WinningResult(winningAndBonusNumber, gameWinningPrizeList, purchasedLottoList);

        WinningResultDTO winningResultDTO = new WinningResultDTO(
                winningResult.getGameWinningPrizeList(),
                winningResult.getUserWinningPrizeResult(),
                winningResult.getTotalPurchaseAmount(),
                winningResult.getEarnedMoney(),
                winningResult.getRateOfReturn());
        return winningResultDTO;
    }
}
