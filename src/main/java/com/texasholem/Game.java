package com.texasholem;

import com.texasholem.team.Card;
import com.texasholem.team.Deck;

import java.util.ArrayList;

public class Game {
    public static ArrayList<Card> user;

    public static ArrayList<Card> table;
    public static ArrayList<Card> dealer;


    public Game() {
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();
        Game game = new Game();
        game.deal(deck);

    }

    public ArrayList<Card> Hand() {
        return user;
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
    }
}

