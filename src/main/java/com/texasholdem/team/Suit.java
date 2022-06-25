package com.texasholdem.team;

/**
 * Enumerates all suits of the standard 52-card deck of playing cards (aka French-suited playing cards), each with the
 * corresponding Unicode symbol and {@link Color}.
 */
public enum Suit {

    CLUBS('\u2663', Color.BLACK),
    DIAMONDS('\u2662', Color.RED),
    HEARTS('\u2661', Color.RED),
    SPADES('\u2660', Color.BLACK);

    private final char symbol;
    private final Color color;

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
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * Enumerates the colors of playing card suits.
     */
    public enum Color {
        BLACK, RED
    }

}
