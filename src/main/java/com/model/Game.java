package com.model;

import com.model.team.Card;
import com.model.team.Deck;
import com.model.team.Hands;

import java.util.ArrayList;
import java.util.Comparator;

public class Game {
    public static ArrayList<Card> user = new ArrayList<>();
    public static ArrayList<Card> table = new ArrayList<>();
    public static ArrayList<Card> dealer = new ArrayList<>();
    private final Comparator<Card> displayComparator = Comparator
            .comparing(Card::getRank);


    public Game() {
    }

    public static void main(String[] args) {
        Game game = new Game();
        Deck deck = new Deck();
        game.deal(deck);
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
        Card card21= deck.draw();
        Card card22 = deck.draw();
        Card card23= deck.draw();
        Card card24 = deck.draw();
        Card card25 = deck.draw();
        Card card26= deck.draw();

        dealer.add(card25);
        dealer.add(card26);
        user.add(card12);
        user.add(card13);

//        table.add(card14);
//        table.add(card15);
//        table.add(card3);
        table.add(card4);
        table.add(card15);
        table.add(card9);
        table.add(card10);
        table.add(card11);

        System.out.println("Dealer" + dealer);
        System.out.println("User" + user);
        System.out.println("Table" + table);
        game.sorter(dealer);
        game.sorter(user);
        //Hands.getHand(dealer);
        Hands.compares(dealer, user, table);
    }


    public void deal(Deck deck) {
        user.sort(displayComparator);
        table.sort(displayComparator);
        dealer.sort(displayComparator);

    }

    public void sorter(ArrayList<Card> deck) {
        deck.sort(displayComparator);
    }
}


