package com.texasholem.team;

import java.util.*;

/**
 * Implements a standard 52-card deck of playing cards, with methods to sort, shuffle, and draw from the deck. Note that
 * this implementation <strong>is not</strong> thread-safe. In particular, the {@link Iterator Iterator&lt;Card&gt;}
 * returned by the {@link #iterator} method will be invalidated by calls to any of the {@link #sort()} or
 * {@link #shuffle()} overloads; any subsequent attempt to use that {@link Iterator Iterator&lt;Card&gt;} will result in
 * a {@link ConcurrentModificationException} being thrown.
 */
public class Deck implements Iterable<Card> {

    private final int size;
    private final List<Card> cards;

    private Iterator<Card> iterator;
    private int remaining;

    /**
     * TODO Complete Javadoc comment.
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
     * TODO Complete Javadoc comment (or remove, if not needed).
     *
     * @return
     */
    @Override
    public int hashCode() {
        return cards.hashCode();
    }

    /**
     * TODO Complete Javadoc comment (or remove, if not needed).
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return (this == obj || (obj instanceof Deck && cards.equals(((Deck) obj).cards)));
    }

    /**
     * TODO Complete Javadoc comment (or remove, if not needed).
     *
     * @return
     */
    @Override
    public String toString() {
        return cards.toString();
    }

    /**
     * TODO Complete Javadoc comment (or remove, if not needed).
     *
     * @return
     */
    @Override
    public Iterator<Card> iterator() {
        return Collections.unmodifiableCollection(cards).iterator();
    }

    /**
     * TODO Complete Javadoc comment.
     */
    public void shuffle() {
        Collections.shuffle(cards);
        reset();
    }

    /**
     * TODO Complete Javadoc comment.
     *
     * @param random
     */
    public void shuffle(Random random) {
        Collections.shuffle(cards, random);
        reset();
    }

    /**
     * TODO Complete Javadoc comment.
     */
    public void sort() {
        sort(null);
    }

    /**
     * TODO Complete Javadoc comment.
     * @param comparator
     */
    public void sort(Comparator<Card> comparator) {
        cards.sort(comparator);
        reset();
    }

    /**
     * TODO Complete Javadoc comment.
     *
     * @return
     * @throws NoCardsRemainingException
     */
    public Card draw() throws NoCardsRemainingException{
        try {
            Card card = iterator.next();
            remaining--;
            return card;
        } catch (NoSuchElementException e) {
            throw new NoCardsRemainingException("Deck is empty");
        }
    }

    /**
     * TODO Complete Javadoc comment.
     */
    public void reset() {
        iterator = cards.iterator();
        remaining = size;
    }

    /**
     * TODO Complete Javadoc comment.
     *
     * @return
     */
    public int getRemaining() {
        return remaining;
    }

}
