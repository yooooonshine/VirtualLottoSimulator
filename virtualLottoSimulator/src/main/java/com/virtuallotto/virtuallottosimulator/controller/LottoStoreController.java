package com.virtuallotto.virtuallottosimulator.controller;

import com.virtuallotto.virtuallottosimulator.model.Lotto;
import com.virtuallotto.virtuallottosimulator.model.Payment;
import com.virtuallotto.virtuallottosimulator.service.DTOService;
import com.virtuallotto.virtuallottosimulator.service.LottoCalculator;
import com.virtuallotto.virtuallottosimulator.service.LottoMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.function.Supplier;

@Controller
public class LottoStoreController {
    private final DTOService dtoService;
    private final LottoCalculator lottoCalculator;
    private final LottoMachine lottoMachine;

    @Autowired
    public LottoStoreController(DTOService dtoService, LottoCalculator lottoCalculator, LottoMachine lottoMachine) {
        this.dtoService = dtoService;
        this.lottoCalculator = lottoCalculator;
        this.lottoMachine = lottoMachine;
    }

    @GetMapping("lottoStore")
    public String lottoStore(Model model) {
        int nthInformation = 10; //추후 db에서 받도록 변경
        model.addAttribute("nttInformation", nthInformation);
        return "lottoStore";
    }

    @GetMapping("lottoStore-parchaseAmount")
    public String buyTickets(
            @RequestParam("purchaseAmount") String purchaseAmount,
            @RequestParam("userId") String userId,
            Model model)
    {
        Payment payment = new Payment(purchaseAmount);
        List<Lotto> tickets =  lottoMachine.generateTickets(payment);


        model.addAttribute("tickets", tickets);
        return "lottoStore";
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
