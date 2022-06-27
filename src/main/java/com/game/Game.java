package com.game;

import com.model.team.Card;
import com.model.team.Deck;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Game {

    public static int bank = 0;
    public static int dealerBank = 0;
    public static int pot = 0;
    public static int blinds = 0;

    Deck deck = new Deck();

    private final Comparator<Card> displayComparator = Comparator.comparing((Card c) -> c.getSuit().getColor()).thenComparing(Card::getSuit).thenComparing(Card::getRank);

    private final ArrayList<Card> hand = new ArrayList<>();
    private final ArrayList<Card> dealerHand = new ArrayList<>();
    private final ArrayList<Card> burnPile = new ArrayList<>();

    public Game() {
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Texas Hold'em! Select \"y\" or \"yes\" to play. Or type \"exit\" to exit game. ");
        String startGame = scanner.next();
        do {
            if (startGame.equalsIgnoreCase("y") || startGame.equalsIgnoreCase("yes")) {
                break;
            } else if (startGame.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the game");
                System.exit(0);
            }
            else {
                System.out.println("Please type \"y\" or \"yes\" to play. Or type \"exit\" to exit game.");
                startGame = scanner.next();
            }
        }  while (!(startGame.equalsIgnoreCase("y")) && !(startGame.equalsIgnoreCase("yes")));

        System.out.println("Please set your bank. Enter your starting bank value between 20-1000: ");


        while (!(bank >= 20 && bank <= 1000)) {
            try {
                bank = Integer.parseInt(String.valueOf(Integer.parseInt(scanner.next())));
                dealerBank = bank;
                if (bank < 20 || bank > 1000) {
                    System.out.println("You must select a whole value number between 20 and 1000!");
                }
            } catch (NumberFormatException ignored) {
                System.out.println("You must select a whole value number between 20 and 1000!");
            }
        }

        sleep(750);
        System.out.printf("Your current bank value: $%d.%nDealer's current bank value: $%d.%n", getBank(), getDealerBank());
        sleep(1250);
        System.out.println("Please enter a value between 5-20 to use as the blind amount for each player");

        while (blinds < 5 || blinds > 20) {
            try {
                blinds = Integer.parseInt(String.valueOf(Integer.parseInt(scanner.next())));
                if (blinds < 5 || blinds > 20) {
                    System.out.println("You must select a whole value number between 5 and 20!");
                }
            } catch (NumberFormatException ignored) {
                System.out.println("You must select a whole value number between 5 and 20!");
            }
        }

        System.out.printf("Blind value set to $%d%n", getBlinds());
        sleep(750);
        System.out.println("Shuffling cards...");
        deck.shuffle();
        sleep(750);

        System.out.println("Cards are shuffled! Please type \"d\" or \"deal\" to deal cards.");
        String deal = scanner.next();
        do {
                if (deal.equalsIgnoreCase("deal") || deal.equalsIgnoreCase("d")) {
                    System.out.println("Dealing hands...");
                    dealHand();
                } else if (deal.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting the game");
                    System.exit(0);
                }
                else {
                    System.out.println("Please type \"d\" or \"deal\" to deal cards, or \"exit\" to exit the game.");
                    deal = scanner.next();
                }
            }  while (!(deal.equalsIgnoreCase("deal")) && !(deal.equalsIgnoreCase("d")));
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
        sleep(750);

        System.out.println("Your first card is " + card1);
        sleep(750);
        System.out.println("Dealer has received their first card.");
        sleep(750);
        System.out.println("Your second card is " + card2);
        sleep(750);
        System.out.println("Dealer has received their second card.");
        sleep(750);

        showHand();
        sleep(750);
        preFlopAction();

    }

    public void showHand() {
        System.out.println("Your hand is " + hand);
    }

    public void preFlopAction() {
        Scanner scanner = new Scanner(System.in);
        bank = bank - blinds;
        pot = blinds * 2;
        dealerBank = dealerBank - blinds;
        System.out.println("It is your turn, please enter \"c\" or \"check\" to check, or y");
        String preFlopAction = scanner.next();

//        do {
//            if (preFlopAction.equalsIgnoreCase("y") || preFlopAction.equalsIgnoreCase("yes")) {
//                break;
//            } else if (preFlopAction.equalsIgnoreCase("exit")) {
//                System.out.println("Exiting the game");
//                System.exit(0);
//            }
//            else {
//                System.out.println("Please type \"y\" or \"yes\" to play. Or type \"exit\" to exit game.");
//                preFlopAction = scanner.next();
//            }
//        }  while (!(preFlopAction.equalsIgnoreCase("y")) && !(preFlopAction.equalsIgnoreCase("yes")));


    }

    public void bet() {
        Scanner userInput = new Scanner(System.in);
        int choice = userInput.nextInt();
        while (choice <= 10) {
            System.out.println("The Minimum is 10$, please enter a higher number.");
            choice = userInput.nextInt();
        }
    }
    public void sleep(int timer){
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static int getBank() {
        return bank;
    }

    public static int getDealerBank() {
        return dealerBank;
    }

    public static int getBlinds() {
        return blinds;
    }
}
