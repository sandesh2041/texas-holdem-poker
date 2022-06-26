package com.model;

import com.model.team.Card;
import com.model.team.Deck;

import java.util.ArrayList;
import java.util.Scanner;

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

        System.out.println("Would you like to play? 10$ minimum.");
        game.bet();
//        System.out.println(user.toString());
//        System.out.println(table.subList(0,3));
//        System.out.println(dealer.toString());
        System.out.println("Would you like to Bet X2");
    }

    public void bet() {
        Scanner userInput = new Scanner(System.in);
        int choice = userInput.nextInt();
        while (choice <= 10) {
            System.out.println("The Minimum is 10$, please enter a higher number.");
            choice = userInput.nextInt();
        }
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

    public void preFold() {
        System.out.println(user.toString());
    }

}

