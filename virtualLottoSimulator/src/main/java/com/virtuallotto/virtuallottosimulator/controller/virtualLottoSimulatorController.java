package com.virtuallotto.virtuallottosimulator.controller;

import com.virtuallotto.virtuallottosimulator.service.DTOService;
import com.virtuallotto.virtuallottosimulator.service.LottoCalculator;
import com.virtuallotto.virtuallottosimulator.service.LottoMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.function.Supplier;

@Controller
public class virtualLottoSimulatorController {
    private final DTOService dtoService;
    private final LottoCalculator lottoCalculator;
    private final LottoMachine lottoMachine;

    @Autowired
    public virtualLottoSimulatorController(DTOService dtoService, LottoCalculator lottoCalculator, LottoMachine lottoMachine) {
        this.dtoService = dtoService;
        this.lottoCalculator = lottoCalculator;
        this.lottoMachine = lottoMachine;
    }

    @GetMapping("signup")
    public String signup() {
        return "signup";
    }

    //    @PostMapping("purchaseamount")
//    public String purchaseAmount() {
//
//    }
    @GetMapping("lottoStore")
    public String lottoStore(Model model) {
//        model.addAttribute("nttInformation", nthInformation);
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
