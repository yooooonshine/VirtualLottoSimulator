package com.virtuallotto.virtuallottosimulator.constants;

public enum Rank {
    //hasBonusNumber의 1은 true, 0은 don't care, -1은 false
    FIRST_PLACE_CONDITION(1,6, 0),
    SECOND_PLACE_CONDITION(2,5, 1),
    THIRD_PLACE_CONDITION(3,5, -1),
    FORTH_PLACE_CONDITION(4,4, 0),
    FIFTH_PLACE_CONDITION(5,3, 0);

    private static final String INVALID_INDEX = "[ERROR] %d등은 없습니다.";
    private static final String NO_INDEX_CONTAINING_THIS_CONDITIONS = "[ERROR] 해당 조건을 만족하는 등수는 없습니다.";

    private final int index;
    private final int numberOfMatchesRequired;
    private final int hasBonusNumber;

    Rank(int index, int numberOfMatchesRequired, int hasBonusNumber) {
        this.index = index;
        this.numberOfMatchesRequired = numberOfMatchesRequired;
        this.hasBonusNumber = hasBonusNumber;
    }

    public int getNumberOfMatchesRequired() {
        return numberOfMatchesRequired;
    }

    public int getHasBonusNumber() {
        return hasBonusNumber;
    }

    public static int getIndexFromConditions(int numberOfMatchesRequired, int hasBonusNumber) {
        for (Rank type : Rank.values()) {
            if (type.getNumberOfMatchesRequired() == numberOfMatchesRequired
                    && type.getHasBonusNumber() == hasBonusNumber) {
                return type.index;
            }
        }
        return 0; //Dont care
    }

    public static int getNumberOfMatchesRequiredFromIndex(int index) {
        for (Rank type : Rank.values()) {
            if (type.index == index) {
                return type.numberOfMatchesRequired;
            }
        }
        throw new IllegalArgumentException(String.format(INVALID_INDEX, index));

    }

    public static int getHasBonusNumberFromIndex(int index) {
        for (Rank type : Rank.values()) {
            if (type.index == index) {
                return type.hasBonusNumber;
            }
        }
        throw new IllegalArgumentException(String.format(INVALID_INDEX, index));
    }
}
