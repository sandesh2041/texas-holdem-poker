package com.poker.model;

/**
 * CardRankings refers to the winning cards in the hands.
 * That compares{@link Card} and compares {@link Suit}.
 */
public enum CardRankings {
    /**
     * Enum Card Value of a Royal Flush.
     */
    ROYAL_FLUSH(100),
    /**
     * Enum Card Value of a Straight Flush.
     */
    STRAIGHT_FLUSH(90),
    /**
     * Enum Card Value of a Four of a Kind.
     */
    FOUR_OF_A_KIND(80),
    /**
     * Enum Card Value of a Full House.
     */
    FULL_HOUSE(70),
    /**
     * Enum Card Value of a Flush.
     */
    FLUSH(60),
    /**
     * Enum Card Value of a Straight.
     */
    STRAIGHT(50),
    /**
     * Enum Card Value of a Three of a Kind.
     */
    THREE_OF_A_KIND(40),
    /**
     * Enum Card Value of Two Pairs.
     */
    TWO_PAIRS(30),
    /**
     * Enum Card Value of One Pair.
     */
    ONE_PAIR(20),
    /**
     * Enum Card Value of the High Card.
     */
    HIGH_CARD(10);

    private final int value;

    CardRankings(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
