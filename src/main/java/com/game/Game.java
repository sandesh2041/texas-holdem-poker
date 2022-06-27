package com.game;

import com.model.team.Card;
import com.model.team.Deck;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Game {

    public static int bank = 0;
    Deck deck = new Deck();

    private final Comparator<Card> displayComparator = Comparator
            .comparing((Card c) -> c.getSuit().getColor())
            .thenComparing(Card::getSuit)
            .thenComparing(Card::getRank);

    //
//    public static ArrayList<Card> user = new ArrayList<>();
//    public static ArrayList<Card> table = new ArrayList<>();
//    public static ArrayList<Card> dealer = new ArrayList<>();
//        System.out.println(user.toString());
//        System.out.println(table.subList(0,3));
//        System.out.println(dealer.toString());
    private ArrayList<Card> hand = new ArrayList<>();

    public Game() {
    }

    public static void main(String[] args) {

        Game game = new Game();

        game.startGame();

//        dealer.showHand();


//        System.out.println("Would you like to play? 10$ minimum.");
//        game.bet();
//
//        System.out.println("Would you like to Bet X2");
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        int addToBank;
        String startGame;
        String readyPlay;

        System.out.println("Welcome to Texas Hold'em! Select \"y\" or \"yes\" to play. ");
        startGame = scanner.next();
        if (startGame.equalsIgnoreCase("yes") || startGame.equalsIgnoreCase("y")) {
            System.out.println("How much would you like to start with? enter a value between 10-1000: ");
        } else {
            System.out.println("Exiting the game");
        }

        while (scanner.nextInt() < 10 || scanner.nextInt() > 1000) {
            System.out.println("You must select a whole value number between 10 and 1000!");
                scanner.nextInt();

        }
        addToBank = scanner.nextInt();
        bank = addToBank;
        System.out.println("Your current bank value is " + bank);


        System.out.println("Shuffling cards...");
        deck.shuffle();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Cards are shuffled! Please type \"deal\" to deal cards: ");
        readyPlay = scanner.next();

        if (readyPlay.equalsIgnoreCase("deal")) {
            System.out.println("Dealing hands...");
            dealHand();
        } else {
            System.out.println("Exiting the game");
        }
    }


    public void dealHand() {
        Deck deck = new Deck();
        deck.shuffle();
        Card card1 = deck.draw();
        Card card2 = deck.draw();
        hand.add(card1);
        hand.add(card2);
        System.out.println("Your first card is " + card1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Your second card is " + card2);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        showHand();


    }

    public void showHand() {

        System.out.println("Your hand is " + hand);
    }

    public void takeBet() {

    }

    public void bet() {
        Scanner userInput = new Scanner(System.in);
        int choice = userInput.nextInt();
        while (choice <= 10) {
            System.out.println("The Minimum is 10$, please enter a higher number.");
            choice = userInput.nextInt();
        }
    }


}
