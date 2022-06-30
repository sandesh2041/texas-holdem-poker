package com.model.team;

public enum CardRankings {
    ROYAL_FLUSH(100),
    STRAIGHT_FLUSH(90),
    FOUR_OF_A_KIND(80),
    FULL_HOUSE(70),
    FLUSH(60),
    STRAIGHT(50),
    THREE_OF_A_KIND(40),
    TWO_PAIRS(30),
    ONE_PAIR(20),
    HIGH_CARD(10);

    private final int value;

    CardRankings(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
