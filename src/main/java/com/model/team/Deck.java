package com.model.team;

import java.util.*;

/**
 * Implements a standard 52-card deck of playing cards, with methods to sort, shuffle, and draw from the deck. Note that
 * this implementation <strong>is not</strong> thread-safe. In particular, the {@link Iterator Iterator&lt;Card&gt;}
 * returned by the {@link #iterator} method will be invalidated by calls to any of the sort or
 * {@link #shuffle()} overloads; any subsequent attempt to use that {@link Iterator Iterator&lt;Card&gt;} will result in
 * a {@link ConcurrentModificationException} being thrown.
 */
public class Deck implements Iterable<Card> {

    private final int size;
    private final List<Card> cards;

    private Iterator<Card> iterator;
    private int remaining;

    /**
     * Implements the ability to use a full set of playing cards using the {@link Card}, {@link Suit}, and {@link Rank}.
     * In order to get all cards with ranks and suits.
     */
    public Deck() {
        Suit[] suits = Suit.values();
        Rank[] ranks = Rank.values();
        size = suits.length * ranks.length;
        cards = new ArrayList<>(size);
        for (Suit s : suits) {
            for (Rank r : ranks) {
                Card c = new Card(s, r);
                cards.add(c);
            }
        }
        reset();
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of hash tables such as those provided by HashMap.
     * {@link Override} is used to return the hashcode of {@link Card}.
     *
     * @return returns tha hash code.
     */
    @Override
    public int hashCode() {
        return cards.hashCode();
    }

    /**
     * Returns a {@link Boolean} comparing objects or object instanceof {@link Deck} and {@link Card}.
     *
     * @param obj can take in cards.
     * @return a {@link Boolean} if true/false.
     */
    @Override
    public boolean equals(Object obj) {
        return (this == obj || (obj instanceof Deck && cards.equals(((Deck) obj).cards)));
    }

    /**
     * Returns the {@link Card} to string back.
     *
     * @return returns the toString of the card.
     */
    @Override
    public String toString() {
        return cards.toString();
    }

    /**
     * returns the cards iterated through Two and Ace's in Rank and all {@link Suit}.
     *
     * @return returns the deck with cards 2-A, clubs, spade, hearts, diamonds.
     */
    @Override
    public Iterator<Card> iterator() {
        return Collections.unmodifiableCollection(cards).iterator();
    }

    /**
     * Returns the {@link Deck} shuffled.
     */
    public void shuffle() {
        Collections.shuffle(cards);
        reset();
    }

    /**
     * returns the {@link Deck} shuffled using Random.
     *
     * @param random inputs a random.
     */
    public void shuffle(Random random) {
        Collections.shuffle(cards, random);
        reset();
    }


    /**
     * Sorts the cards that were given to it using the {@link Comparator}.
     *
     * @param comparator compares cards using comparator.
     */
    public void sort(Comparator<Card> comparator) {
        cards.sort(comparator);
        reset();
    }

    /**
     * Draws a card from the {@link Deck} then returns a {@link Card}.
     * @return a single card and if no more cards than it.
     * @throws NoCardsRemainingException throws an exception if no cards are remaining.
     */
    public Card draw() throws NoCardsRemainingException {
        try {
            Card card = iterator.next();
            remaining--;
            return card;
        } catch (NoSuchElementException e) {
            throw new NoCardsRemainingException("Deck is empty");
        }
    }

    /**
     * resets the cards remaining.
     */
    public void reset() {
        iterator = cards.iterator();
        remaining = size;
    }

    /**
     * Returns the amount of the cards remaining.
     *
     * @return Returns the cards remaining.
     */
    public int getRemaining() {
        return remaining;
    }

}
