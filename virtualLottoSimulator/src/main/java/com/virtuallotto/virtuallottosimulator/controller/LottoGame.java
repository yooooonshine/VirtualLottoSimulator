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







    public <T> T readUserInput(Supplier<T> read) {
        try {
            return read.get();
        } catch (IllegalArgumentException e) {
            e.getMessage();
            return readUserInput(read);
        }
    }

}

