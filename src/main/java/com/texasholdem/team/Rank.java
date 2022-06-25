package com.texasholdem.team;

/**
 * Enumerates all ranks of the standard 52-card deck of playing cards (aka French-suited playing cards), each with a
 * short {@link String} abbreviation. No type of score or point value is included here, to enable incorporation into a
 * wide variety of applications.
 */
public enum Rank {

    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private final String abbreviation;

    Rank(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     * Returns the one- or two-letter abbreviation of this instance.
     *
     * @return
     */
    public String getAbbreviation() {
        return abbreviation;
    }

}
