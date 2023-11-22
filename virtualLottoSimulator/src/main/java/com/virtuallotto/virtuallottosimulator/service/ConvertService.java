package com.virtuallotto.virtuallottosimulator.service;

import java.util.Arrays;
import java.util.List;

public class ConvertService {

    private ConvertService() {}

    public static List<Integer> makeStringToIntList(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .toList();
    }

    public static String makeIntListToString(List<Integer> lottoNumberList) {
        return String.join(",", (CharSequence) lottoNumberList);
    }
}
