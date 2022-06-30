package com.model.team;

/**
 * Enumerates all ranks of the standard 52-card deck of playing cards (aka French-suited playing cards), each with a
 * short {@link String} abbreviation. No type of score or point value is included here, to enable incorporation into a
 * wide variety of applications.
 */
public enum Rank {

    /**
     * Enum Rank of Two.
     */
    TWO("2"),
    /**
     * Enum Rank of Three.
     */
    THREE("3"),
    /**
     * Enum Rank of Four.
     */
    FOUR("4"),
    /**
     * Enum Rank of Five.
     */
    FIVE("5"),
    /**
     * Enum Rank of Six.
     */
    SIX("6"),
    /**
     * Enum Rank of Seven.
     */
    SEVEN("7"),
    /**
     * Enum Rank of Eight.
     */
    EIGHT("8"),
    /**
     * Enum Rank of Nine.
     */
    NINE("9"),
    /**
     * Enum Rank of Ten.
     */
    TEN("10"),
    /**
     * Enum Rank of Jack.
     */
    JACK("J"),
    /**
     * Enum Rank of Queen.
     */
    QUEEN("Q"),
    /**
     * Enum Rank of King.
     */
    KING("K"),
    /**
     * Enum Rank of Ace.
     */
    ACE("A");

    /**
     * Sets the abbreviation of the Ranks.
     */
    private final String abbreviation;

    Rank(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     * Returns the one- or two-letter abbreviation of this instance.
     *
     * @return The {@link Rank} of the Cards.
     */
    public String getAbbreviation() {
        return abbreviation;
    }

}
