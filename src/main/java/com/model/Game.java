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
        deck.shuffle();
        game.deal(deck);
        System.out.println("Dealer" + dealer);
        System.out.println("User" + user);
        System.out.println("Table" + table);
        game.sorter(dealer);
        game.sorter(user);
        //Hands.getHand(dealer);
        Hands.compares(dealer, user, table);
    }


    public void deal(Deck deck) {
        user.add(deck.draw());
        user.add(deck.draw());
        dealer.add(deck.draw());
        dealer.add(deck.draw());
        table.add(deck.draw());
        table.add(deck.draw());
        table.add(deck.draw());
        table.add(deck.draw());
        table.add(deck.draw());


        user.sort(displayComparator);
        table.sort(displayComparator);
        dealer.sort(displayComparator);

    }

    public void sorter(ArrayList<Card> deck) {
        deck.sort(displayComparator);
    }
}


