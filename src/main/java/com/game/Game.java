package com.game;

import com.model.team.Card;
import com.model.team.Deck;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Game {

    public static int bank = 0;
    public static int pot = 0;
    public static int blinds = 0;
    Deck deck = new Deck();

    private final Comparator<Card> displayComparator = Comparator
            .comparing((Card c) -> c.getSuit().getColor())
            .thenComparing(Card::getSuit)
            .thenComparing(Card::getRank);

    private final ArrayList<Card> hand = new ArrayList<>();
    private final ArrayList<Card> dealerHand = new ArrayList<>();
    private final ArrayList<Card> burnPile = new ArrayList<>();

    public Game() {
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        String startGame;
        String readyPlay;

        System.out.println("Welcome to Texas Hold'em! Select \"y\" or \"yes\" to play. ");
        startGame = scanner.next();
        if (startGame.equalsIgnoreCase("yes") || startGame.equalsIgnoreCase("y")) {
            System.out.println("How much would you like to start with? enter a value between 20-1000: ");
        } else {
            System.out.println("Exiting the game");
        }

        while (!(bank >= 20 && bank <= 1000)) {
            try {
                bank = Integer.parseInt(String.valueOf(Integer.parseInt(scanner.next())));
            } catch (NumberFormatException ignored) {
                System.out.println("You must select a whole value number between 20 and 1000!");
            }
        }
        System.out.println("Your current bank value is $" + bank);
        System.out.println("Please enter a value between 5-20 to use as the blind amount for each player");


        while (blinds < 5 || blinds > 20) {
            try {
                blinds = Integer.parseInt(String.valueOf(Integer.parseInt(scanner.next())));
            } catch (NumberFormatException ignored) {
                System.out.println("You must select a whole value number between 5 and 20!");
            }
        }
        System.out.println("Blind value set to $" + blinds);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


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
        Card card3 = deck.draw();
        Card card4 = deck.draw();
        Card card5 = deck.draw();

        burnPile.add(card1);
        hand.add(card2);
        dealerHand.add(card3);
        hand.add(card4);
        dealerHand.add(card5);

        System.out.println("Burn card has been dealt into the burn pile...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Your first card is " + card1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Dealer has received their first card.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Your second card is " + card2);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Dealer has received their second card.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        showHand();
        takeBet();


    }

    public void showHand() {

        System.out.println("Your hand is " + hand);
    }

    public void takeBet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("$");
        System.out.println("It is your turn, please enter \"c\" or \"check\" to check, or y");
        String preFlop = scanner.next();

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
