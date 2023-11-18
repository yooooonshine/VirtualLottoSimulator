package com.virtuallotto.virtuallottosimulator.controller;

import com.virtuallotto.virtuallottosimulator.constants.GameNumberConstants;
import com.virtuallotto.virtuallottosimulator.constants.Rank;
import com.virtuallotto.virtuallottosimulator.dto.LottoTicketsDTO;
import com.virtuallotto.virtuallottosimulator.dto.WinningStatisticsDTO;
import com.virtuallotto.virtuallottosimulator.model.*;
import com.virtuallotto.virtuallottosimulator.service.DTOService;
import com.virtuallotto.virtuallottosimulator.service.LottoCalculator;
import com.virtuallotto.virtuallottosimulator.service.LottoMachine;
import com.virtuallotto.virtuallottosimulator.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

import static com.virtuallotto.virtuallottosimulator.service.LottoCalculator.*;

public class LottoGame {

    private final LottoMachine lottoMachine;
    private final LottoCalculator lottoCalculator;
    private final DTOService dtoService;

    public LottoGame(
        LottoMachine lottoMachine,
        LottoCalculator lottoCalculator,
        DTOService dtoService)
    {

        this.lottoMachine = lottoMachine;
        this.lottoCalculator = lottoCalculator;
        this.dtoService = dtoService;
    }


    public void run() {
        Payment payment = getPaymentAndValidate();
        List<Lotto> tickets = LottoMachine.generateTickets(payment.getPayment());
        Customer customer = new Customer(payment, tickets);
        printCustomerTickets(customer);
        makeWinningAndBonusNumber();
        customer.setWinningResult(calculateCustomerWinningResult(customer));
        customer.setEarnedMoney(calculateEarnedMoney(customer));
        RateOfReturn rateOfReturn = new RateOfReturn(calculateRateOfReturn(customer.getEarnedMoney(), customer.getPayment()));
        OutputView.printWinningStatistics(makeWinningStatisticsDTO(customer.getWinningResult(), rateOfReturn.getRateOfReturn()));
    }


    private void printCustomerTickets(Customer customer) {
        OutputView.printLottoTickets(new LottoTicketsDTO(
                customer.getLottoTickets().size(),
                customer.getLottoTickets())
        );
    }

    private WinningAndBonusNumber makeWinningAndBonusNumber() {
        WinningNumber winningNumber = ();
        BonusNumber bonusNumber = getBonusNumberAndValidate();
        return WinningAndBonusNumber.create(winningNumber, bonusNumber);
    }





    public <T> T readUserInput(Supplier<T> read) {
        try {
            return read.get();
        } catch (IllegalArgumentException e) {
            e.getMessage();
            return readUserInput(read);
        }
    }

}

