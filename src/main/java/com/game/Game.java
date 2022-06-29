package com.game;

import com.model.team.Card;
import com.model.team.Deck;
import com.model.team.Hands;

import java.util.*;

public class Game {

    private final int time = 1;
    public static int bank = 0;
    public static int dealerBank = 0;
    public static int pot = 0;
    public static int blinds = 0;
    public static Deck deck = new Deck();
    Scanner scanner = new Scanner(System.in);

    public static ArrayList<Card> playerHand = new ArrayList<>();
    public static ArrayList<Card> dealerHand = new ArrayList<>();
    public static ArrayList<Card> burnPile = new ArrayList<>();
    public static ArrayList<Card> sharedCards = new ArrayList<>();
    private final ResourceBundle bundle = ResourceBundle.getBundle("strings");


    public void startGame() {
        System.out.println(bundle.getString("start_game"));
        String startGame = scanner.next();
        do {
            if (startGame.equalsIgnoreCase("y") || startGame.equalsIgnoreCase("yes")) {
                break;
            } else if (startGame.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the game");
                System.exit(0);
            } else {
                System.out.println(bundle.getString("start_game"));
                startGame = scanner.next();
            }
        } while (!(startGame.equalsIgnoreCase("y")) && !(startGame.equalsIgnoreCase("yes")));
    }

    public void chooseBankValue() {
        System.out.println(bundle.getString("set_bank"));

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

        System.out.printf(bundle.getString("bank_value"), getBank(), getDealerBank());
        sleep(time);
    }

    public void chooseBlinds() {
        System.out.println(bundle.getString("set_blinds"));

        while (blinds < 5 || blinds > 20) {
            try {
                blinds = Integer.parseInt(String.valueOf(Integer.parseInt(scanner.next())));
                if (blinds < 5 || blinds > 20) {
                    System.out.println(bundle.getString("blinds_error"));
                }
            } catch (NumberFormatException ignored) {
                System.out.println(bundle.getString("blinds_error"));
            }
        }

        System.out.printf(bundle.getString("blinds_value"), getBlinds());
        sleep(time);
    }

    public void startHandMenu() {
        System.out.println(bundle.getString("shuffle_cards"));
        deck.shuffle();
        sleep(time);
        System.out.println(bundle.getString("deal_menu"));
        String deal = scanner.next();

        do {
            if (deal.equalsIgnoreCase("deal") || deal.equalsIgnoreCase("d")) {
                break;
            } else if (deal.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the game");
                System.exit(0);
            } else {
                System.out.println(bundle.getString("deal_menu"));
                deal = scanner.next();
            }
        } while (!(deal.equalsIgnoreCase("deal")) && !(deal.equalsIgnoreCase("d")));

        sleep(time);
        System.out.println(bundle.getString("deal_hands"));
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
        playerHand.add(card2);
        dealerHand.add(card3);
        playerHand.add(card4);
        dealerHand.add(card5);
        System.out.println(bundle.getString("burn_pile"));
        sleep(time);
        System.out.printf(bundle.getString("card1"), card2);
        sleep(time);
        System.out.println(bundle.getString("dealer_card1"));
        sleep(time);
        System.out.printf(bundle.getString("card2"), card4);
        sleep(time);
        System.out.println(bundle.getString("dealer_card2"));
    }

    public void preFlopAction() {
        bank = bank - blinds;
        pot = blinds * 2;
        dealerBank = dealerBank - blinds;
        Actions actions = new Actions();
        actions.actions();
        bank = bank - Actions.bet;
        pot = pot + Actions.bet * 2;
        dealerBank = dealerBank - Actions.bet;
    }

    public void flop() {

        Card card1 = deck.draw();
        Card card2 = deck.draw();
        Card card3 = deck.draw();
        sharedCards.add(card1);
        sharedCards.add(card2);
        sharedCards.add(card3);
        System.out.printf(bundle.getString("dealing_flop"), card1);
        sleep(time);
        System.out.printf(bundle.getString("dealing_flop"), card2);
        sleep(time);
        System.out.printf(bundle.getString("dealing_flop"), card3);
        sleep(time);
        System.out.printf(bundle.getString("shared_cards"), sharedCards);
        sleep(time);
        Actions actions = new Actions();
        actions.actions();
        bank = bank - Actions.bet;
        pot = pot + Actions.bet * 2;
        dealerBank = dealerBank - Actions.bet;
        ;

    }

    public void turn() {

        Card card1 = deck.draw();
        sharedCards.add(card1);
        System.out.printf(bundle.getString("dealing_turn"), card1);
        sleep(time);
        System.out.printf(bundle.getString("shared_cards"), sharedCards);
        sleep(time);
        Actions action = new Actions();
        action.actions();
        bank = bank - Actions.bet;
        pot = pot + Actions.bet * 2;
        dealerBank = dealerBank - Actions.bet;
    }

    public void river() {
        Card card1 = deck.draw();
        sharedCards.add(card1);

        System.out.printf(bundle.getString("dealing_river"), card1);
        sleep(time);
        System.out.printf(bundle.getString("shared_cards"), sharedCards);
        sleep(time);
        Actions action = new Actions();
        action.actions();
        bank = bank - Actions.bet;
        pot = pot + Actions.bet * 2;
        dealerBank = dealerBank - Actions.bet;
    }


    public void determineWinner() {
        printWinner();
        sleep(time);
        System.out.println(Objects.equals(Hands.compares(dealerHand, playerHand, sharedCards), Hands.playerSharedHand));
        System.out.println(Objects.equals(Hands.compares(dealerHand, playerHand, sharedCards), playerHand));
        System.out.println(Objects.equals(Hands.compares(dealerHand, playerHand, sharedCards), Hands.dealerSharedHand));
        System.out.println(Objects.equals(Hands.compares(dealerHand, playerHand, sharedCards), dealerHand));
        System.out.println(Hands.compares(dealerHand, playerHand, sharedCards));

        System.out.println("testing was in 5 lines above");
        if (Objects.equals(Hands.compares(dealerHand, playerHand, sharedCards), Hands.playerSharedHand)) {
            System.out.println("Dealer: \"Player wins!\"");
            bank += pot;
        } else if (Objects.equals(Hands.compares(dealerHand, playerHand, sharedCards), Hands.dealerSharedHand)) {
            System.out.println("Dealer: \"Dealer wins!\"");
            dealerBank += pot;
        } else {
            System.out.println("Dealer: \"Split pot!\"");
            bank += pot / 2;
            dealerBank += pot / 2;
        }
    }

    public void printWinner() {
        String dealerResult = "";
        String userResult = "";
        sleep(time);
        System.out.println("Dealer flips over their cards showing..." + dealerHand);
        sleep(time);
        Hands.compares(dealerHand, playerHand, sharedCards);
        for (int i = 1; i < Hands.dealerSharedHand.size(); i++) {
            dealerResult += Hands.dealerSharedHand.get(i) + " ";
        }
        for (int i = 1; i < Hands.playerSharedHand.size(); i++) {
            userResult += Hands.playerSharedHand.get(i) + " ";
        }
        String message = "Dealer: \"The dealer has " + dealerResult + "and the player has " + userResult + "\"";

        System.out.println(message);
    }

    public void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

//    public static List<Card> getHand() {
////        Deck deck = new Deck();
////        Card card1 = deck.draw();
////        Card card2 = deck.draw();
////        hand.add(card1);
////        hand.add(card2);
//        List<Card> filtered = hand.stream().collect(Collectors.toList());
//        return filtered;
//    }

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

//    public static ArrayList<Object> dealerSharedHand = new ArrayList<>();
//    public static ArrayList<Object> playerSharedHand = new ArrayList<>();
//                    dealerSharedHand=first;
//                            playerSharedHand=second;
