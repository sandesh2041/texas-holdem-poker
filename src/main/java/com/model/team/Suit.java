package com.model.team;

/**
 * Enumerates all suits of the standard 52-card deck of playing cards (aka French-suited playing cards), each with the
 * corresponding Unicode symbol and {@link Color}.
 */
public enum Suit {
    /**
     * Enum Suit of Clubs with the suit '\u2663' and color.
     */
    CLUBS('\u2663', Color.BLACK),
    /**
     * Enum Suit of Diamonds with the suit '\u2662' and color.
     */
    DIAMONDS('\u2662', Color.RED),
    /**
     * Enum Suit of Hearts with the suit '\u2661' and color.
     */
    HEARTS('\u2661', Color.RED),
    /**
     * Enum Suit of Spades with the suit '\u2660' and color.
     */
    SPADES('\u2660', Color.BLACK);

    private final char symbol;
    private final Color color;

    /**
     * Returns the symbol and color of the card.
     * @param symbol returns a symbol.
     * @param color returns a color.
     */
    Suit(char symbol, Color color) {
        this.symbol = symbol;
        this.color = color;
    }


    /**
     * Returns the Unicode symbol of this instance: {@code '\u005Cu2663'} (\u2663) for {@link #CLUBS},
     * {@code '\u005Cu2662'} (\u2662) for {@link #DIAMONDS}, {@code '\u005Cu2661'} (\u2661) for {@link #HEARTS}, and
     * {@code '\u005cu2660'} (\u2660) for {@link #CLUBS}. This is primarily intended for use in constructing a simple
     * {@link String} representation of a playing card (e.g. as returned by {@link Card#toString()}), rather than for
     * any sophisticated GUI or other visual presentation. However, such a text representation may be suitable for use
     * in a console-mode game or other application.
     *
     * @return Unicode symbol of this instance, as a {@code char}.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Returns the {@link Color} of this instance.
     *
     * @return returns the symbol
     */
    public Color getColor() {
        return color;
    }

    /**
     * Enum values of color.
     */
    public enum Color {
        /**
         * Enum values of Black.
         */
        BLACK,
        /**
         * Enum values of Red.
         */
        RED
    }

}
