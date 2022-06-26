package com.henblair.model;

import java.util.Objects;

/**
 * Encapsulates a simple abstraction of a standard (French-suited) playing card, composed of a {@link Suit} and
 * {@link Rank}. Its implementation of {@link Comparable} orders according to {@link Suit} (alphabetical ascending) and
 * then {@link Rank} (ascending, with Ace low).
 */
public final class Card implements Comparable<Card> {

    private final Suit suit;
    private final Rank rank;
    private final int hash;
    private final String symbol;

    /**
     * Initializes this instance with specified {@link Rank} and {@link Suit}.
     *
     * @param suit {@link Suit} of this instance.
     * @param rank {@link Rank} of this instance.
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        hash = Objects.hash(suit, rank);
        symbol = rank.getAbbreviation() + suit.getSymbol();
    }

    /**
     * Returns the {@link Suit} of this instance.
     *
     * @return
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Returns the {@link Rank} of this instance.
     *
     * @return
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Returns an {@code int} hash value based on the {@link Suit} and {@link Rank} of this instance.
     *
     * @return
     */
    @Override
    public int hashCode() {
        return hash;
    }

    /**
     * Compares this instance to {@code obj} for equality. Two instances will be considered equal if and only if both
     * have the same {@link Suit} and both have the same {@link Rank}.
     *
     * @param obj Other instance to be compared for equality with this instance.
     * @return {@code true} if {@code obj} is an instance of {@link Card}, and if both it and this instance have equal
     * suits and ranks.
     */
    @Override
    public boolean equals(Object obj) {
        boolean comparison;
        if (this == obj) {
            comparison = true;
        } else if (obj instanceof Card) {
            Card other = (Card) obj;
            comparison = (rank == other.rank && suit == other.suit);
        } else {
            comparison = false;
        }
        return comparison;
    }

    /**
     * Returns the {@link String} representation of this instance. This representation consists of the rank abbreviation
     * (as returned by {@link Rank#getAbbreviation()}) followed by the suit symbol (returned by
     * {@link Suit#getSymbol()})&mdash;{@code "7\u2663"}, {@code "A\u2661"}, etc.
     *
     * @return Abbreviated {@link String} representation of this instance.
     */
    @Override
    public String toString() {
        return symbol;
    }

    /**
     * Compares this instance to {@code other}, implementing a natural ordering based first on suit
     * (alphabetical ascending) and then on rank (ascending, with Ace low).
     *
     * @param other Instance to which this instance will compare itself.
     * @return Negative, positive, or zero, according to the {@link Comparable} contract.
     */
    @Override
    public int compareTo(Card other) {
        int comparison = suit.compareTo(other.suit);
        return (comparison != 0) ? comparison : rank.compareTo(other.rank);
    }

}

