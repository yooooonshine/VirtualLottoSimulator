package com.virtuallotto.virtuallottosimulator.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConvertService {

    private ConvertService() {}

    public static List<Integer> makeStringToIntList(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .toList();
    }

    public static String makeIntListToString(List<Integer> lottoNumberList) {
        return lottoNumberList.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(","));
    }
}
