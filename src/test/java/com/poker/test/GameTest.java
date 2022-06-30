package com.poker.test;

import com.poker.model.Card;
import com.poker.model.Deck;
import com.poker.model.Hands;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    /**
     * Checks to see if the RoyalFlush is correct and prints off who the winnner is.
     */
    @Test
    public void royalFlushRankTest() {
        ArrayList<Card> user = new ArrayList<>();
        ArrayList<Card> table = new ArrayList<>();
        ArrayList<Card> dealer = new ArrayList<>();
        dealer.add(card13);
        dealer.add(card12);
        table.add(card11);
        table.add(card10);
        table.add(card9);
        table.add(card5);
        table.add(card6);
        user.add(card44);
        user.add(card32);
        System.out.println(" ");
        System.out.println("Checking Royal Flush");
        System.out.println((dealer + " " + table + " " + user));
        assertEquals("winner: Dealer", Hands.compares(dealer, user, table));
    }

    /**
     * Checks to see if the StraightFlush is correct and prints off whom the winner is.
     */
    @Test
    public void straightFlushRankTest() {
        ArrayList<Card> user = new ArrayList<>();
        ArrayList<Card> table = new ArrayList<>();
        ArrayList<Card> dealer = new ArrayList<>();
        dealer.add(card6);
        dealer.add(card2);
        table.add(card3);
        table.add(card4);
        table.add(card1);
        table.add(card5);
        table.add(card16);
        user.add(card44);
        user.add(card32);
        System.out.println(" ");
        System.out.println("Checking Straight Flush");
        System.out.println((dealer + " " + table + " " + user));
        assertEquals("winner: Dealer", Hands.compares(dealer, user, table));
    }

    /**
     * Checks to see if the Four of a Kind is correct and prints off whom the winner is.
     */
    @Test
    public void fourOfAKindTest() {
        ArrayList<Card> user = new ArrayList<>();
        ArrayList<Card> table = new ArrayList<>();
        ArrayList<Card> dealer = new ArrayList<>();
        dealer.add(card13);
        dealer.add(card26);
        table.add(card1);
        table.add(card5);
        table.add(card39);
        table.add(card16);
        table.add(card52);
        user.add(card44);
        user.add(card31);
        System.out.println(" ");
        System.out.println("Checking Four of A Kind");
        System.out.println((dealer + " " + table + " " + user));
        assertEquals("winner: Dealer", Hands.compares(dealer, user, table));
    }

    /**
     * Checks to see if the Full House is correct and prints off whom the winner is.
     */
    @Test
    public void fullHouseTest() {
        ArrayList<Card> user = new ArrayList<>();
        ArrayList<Card> table = new ArrayList<>();
        ArrayList<Card> dealer = new ArrayList<>();
        dealer.add(card44);
        dealer.add(card31);
        table.add(card1);
        table.add(card5);
        table.add(card39);
        table.add(card16);
        table.add(card52);
        user.add(card12);
        user.add(card23);
        System.out.println(" ");
        System.out.println("Checking Full House");
        System.out.println((dealer + " " + table + " " + user));
        assertEquals("winner: Dealer", Hands.compares(dealer, user, table));
    }

    /**
     * Checks to see if the Flush is correct and prints off whom the winner is.
     */
    @Test
    public void flushTest() {
        ArrayList<Card> user = new ArrayList<>();
        ArrayList<Card> table = new ArrayList<>();
        ArrayList<Card> dealer = new ArrayList<>();
        dealer.add(card1);
        dealer.add(card2);
        table.add(card3);
        table.add(card5);
        table.add(card7);
        table.add(card16);
        table.add(card52);
        user.add(card44);
        user.add(card31);
        System.out.println(" ");
        System.out.println("Checking Flush");
        System.out.println((dealer + " " + table + " " + user));
        assertEquals("winner: User", Hands.compares(dealer, user, table));
    }

    /**
     * Checks to see if the Straight is correct and prints off whom the winner is.
     */
    @Test
    public void straightTest() {
        ArrayList<Card> user = new ArrayList<>();
        ArrayList<Card> table = new ArrayList<>();
        ArrayList<Card> dealer = new ArrayList<>();
        dealer.add(card14);
        dealer.add(card28);
        table.add(card4);
        table.add(card5);
        table.add(card39);
        table.add(card16);
        table.add(card52);
        user.add(card42);
        user.add(card31);
        System.out.println(" ");
        System.out.println("Checking Straight");
        System.out.println((dealer + " " + table + " " + user));
        assertEquals("winner: Dealer", Hands.compares(dealer, user, table));
    }

    /**
     * Checks to see if the Three of a kind is correct and prints off whom the winner is.
     */
    @Test
    public void threeOfAKindTest() {
        ArrayList<Card> user = new ArrayList<>();
        ArrayList<Card> table = new ArrayList<>();
        ArrayList<Card> dealer = new ArrayList<>();
        dealer.add(card13);
        dealer.add(card21);
        table.add(card4);
        table.add(card5);
        table.add(card39);
        table.add(card16);
        table.add(card52);
        user.add(card24);
        user.add(card12);
        System.out.println(" ");
        System.out.println("Checking Three of a Kind");
        System.out.println((dealer + " " + table + " " + user));
        assertEquals("winner: Dealer", Hands.compares(dealer, user, table));
    }

    /**
     * Checks to see if the two pairs is correct and prints off whom the winner is.
     */
    @Test
    public void twoPairsTest() {
        ArrayList<Card> user = new ArrayList<>();
        ArrayList<Card> table = new ArrayList<>();
        ArrayList<Card> dealer = new ArrayList<>();
        dealer.add(card13);
        dealer.add(card31);
        table.add(card4);
        table.add(card5);
        table.add(card39);
        table.add(card16);
        table.add(card50);
        user.add(card24);
        user.add(card12);
        System.out.println(" ");
        System.out.println("Checking Two Pairs");
        System.out.println((dealer + " " + table + " " + user));
        assertEquals("winner: Dealer", Hands.compares(dealer, user, table));
    }

    /**
     * Checks to see if the One Pair is correct and prints off whom the winner is.
     */
    @Test
    public void onePairTest() {
        ArrayList<Card> user = new ArrayList<>();
        ArrayList<Card> table = new ArrayList<>();
        ArrayList<Card> dealer = new ArrayList<>();
        dealer.add(card13);
        dealer.add(card32);
        table.add(card4);
        table.add(card5);
        table.add(card39);
        table.add(card16);
        table.add(card50);
        user.add(card24);
        user.add(card12);
        System.out.println(" ");
        System.out.println("Checking One Pair");
        System.out.println((dealer + " " + table + " " + user));
        //String x = Hands.compares(dealer, user, table);
        assertEquals("winner: Dealer", Hands.compares(dealer, user, table));
    }

    Deck deck = new Deck();
    Card card1 = deck.draw();
    Card card2 = deck.draw();
    Card card3 = deck.draw();
    Card card4 = deck.draw();
    Card card5 = deck.draw();
    Card card6 = deck.draw();
    Card card7 = deck.draw();
    Card card8 = deck.draw();
    Card card9 = deck.draw();
    Card card10 = deck.draw();
    Card card11 = deck.draw();
    Card card12 = deck.draw();
    Card card13 = deck.draw();
    Card card14 = deck.draw();
    Card card15 = deck.draw();
    Card card16 = deck.draw();
    Card card17 = deck.draw();
    Card card18 = deck.draw();
    Card card19 = deck.draw();
    Card card20 = deck.draw();
    Card card21 = deck.draw();
    Card card22 = deck.draw();
    Card card23 = deck.draw();
    Card card24 = deck.draw();
    Card card25 = deck.draw();
    Card card26 = deck.draw();
    Card card27 = deck.draw();
    Card card28 = deck.draw();
    Card card29 = deck.draw();
    Card card30 = deck.draw();
    Card card31 = deck.draw();
    Card card32 = deck.draw();
    Card card33 = deck.draw();
    Card card34 = deck.draw();
    Card card35 = deck.draw();
    Card card36 = deck.draw();
    Card card37 = deck.draw();
    Card card38 = deck.draw();
    Card card39 = deck.draw();
    Card card40 = deck.draw();
    Card card41 = deck.draw();
    Card card42 = deck.draw();
    Card card43 = deck.draw();
    Card card44 = deck.draw();
    Card card45 = deck.draw();
    Card card46 = deck.draw();
    Card card47 = deck.draw();
    Card card48 = deck.draw();
    Card card49 = deck.draw();
    Card card50 = deck.draw();
    Card card51 = deck.draw();
    Card card52 = deck.draw();
}
