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
        Deck deck = new Deck();
        deck.shuffle();
        Game game = new Game();
        game.deal(deck);
        System.out.println("User"+user);
        System.out.println("Dealer"+dealer);
        System.out.println("Table"+ table);
        dealer.addAll(table);
        game.sorter(dealer);
        user.addAll(table);
        game.sorter(user);
        System.out.println(dealer);
        System.out.println(user);
        //Hands.getHand(dealer);
        Hands.compares(dealer,user);
    }


    public void deal(Deck deck) {
        user.add(deck.draw());
        user.add(deck.draw());
        table.add(deck.draw());
        table.add(deck.draw());
        table.add(deck.draw());
        table.add(deck.draw());
        table.add(deck.draw());
        dealer.add(deck.draw());
        dealer.add(deck.draw());
        user.sort(displayComparator);
        table.sort(displayComparator);
        dealer.sort(displayComparator);
    }

    public void sorter(ArrayList<Card> deck) {
        deck.sort(displayComparator);
    }
}


