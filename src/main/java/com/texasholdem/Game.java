package com.texasholdem;

import com.texasholdem.team.Card;
import com.texasholdem.team.Deck;

import java.util.ArrayList;

public class Game {
    public static ArrayList<Card> user = new ArrayList<>();

    public static ArrayList<Card> table = new ArrayList<>();
    public static ArrayList<Card> dealer = new ArrayList<>();


    public Game() {
    }
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();
        Game game = new Game();
        game.deal(deck);
        System.out.println(user.toString());
        System.out.println(table.toString());
        System.out.println(dealer.toString());
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

