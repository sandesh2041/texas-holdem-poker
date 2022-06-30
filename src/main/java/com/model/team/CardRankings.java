package com.model.team;

/**
 * CardRankings refers to the winning cards in the hands.
 * That compares{@link Card} and compares {@link Suit}.
 *
 */
public enum CardRankings {
    /**
     * Enum Card Value of a Royal Flush.
     */
    ROYAL_FLUSH,
    /**
     * Enum Card Value of a Straight Flush.
     */
    STRAIGHT_FLUSH,
    /**
     * Enum Card Value of a Four of a Kind.
     */
    FOUR_OF_A_KIND,
    /**
     * Enum Card Value of a Full House.
     */
    FULL_HOUSE,
    /**
     * Enum Card Value of a Flush.
     */
    FLUSH,
    /**
     * Enum Card Value of a Straight.
     */
    STRAIGHT,
    /**
     * Enum Card Value of a Three of a Kind.
     */
    THREE_OF_A_KIND,
    /**
     * Enum Card Value of Two Pairs.
     */
    TWO_PAIRS,
    /**
     * Enum Card Value of One Pair.
     */
    ONE_PAIR,
    /**
     * Enum Card Value of the High Card.
     */
    HIGH_CARD;
}
