package com.virtuallotto.virtuallottosimulator.constants;

public enum NumberConstants {
    LOTTO_PRICE(1000),
    MAX_LOTTO_NUMBER(45),
    MIN_LOTTO_NUMBER(1),
    NUMBER_OF_NUMBERS_TO_MATCH(6),
    NUMBER_OF_WINNING_PRIZE(5);

    private final int value;

    NumberConstants(int value){
        this.value = value;

    }

    public int getValue(){
        return value;
    }
}
