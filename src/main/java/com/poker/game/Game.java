package com.poker.game;

import com.poker.model.Decision;
import com.poker.model.Card;
import com.poker.model.Deck;
import com.poker.model.Hands;
import com.poker.services.BotActions;
import com.poker.services.ChenScore;
import com.poker.services.BotServices;

import java.util.*;

public class Game {
    private final ResourceBundle bundle = ResourceBundle.getBundle("strings");
    private final int time = 700;

    public static int bank = 0;
    public static int dealerBank = 0;
    public static int pot = 0;
    public static int blinds = 0;
    public static int bBet = 0;
    public static Deck deck = new Deck();
    public static ArrayList<Card> playerHand = new ArrayList<>();
    public static ArrayList<Card> dealerHand = new ArrayList<>();
    public static ArrayList<Card> dealerHand1 = new ArrayList<>();
    public static ArrayList<Card> burnPile = new ArrayList<>();
    public static ArrayList<Card> sharedCards = new ArrayList<>();
    public static ArrayList<Card> sharedCards1 = new ArrayList<>();

    double score;
    BotServices botServices = new BotServices();
    BotActions bAction = new BotActions();
    Actions actions = new Actions();
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Card> combinedCards = new ArrayList<>();

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
        while (!(bank >= 40 && bank <= 1000)) {
            try {
                bank = Integer.parseInt(String.valueOf(Integer.parseInt(scanner.next())));
                dealerBank = bank;
                if (bank < 40 || bank > 1000) {
                    System.out.println(bundle.getString("set_bank_error"));
                }
            } catch (NumberFormatException ignored) {
                System.out.println(bundle.getString("set_bank_error"));
            }
        }
        System.out.printf(bundle.getString("bank_value"), getBank(), getDealerBank());
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
    }

    public void startHandMenu() {
        System.out.println(bundle.getString("shuffle_cards"));
        deck.shuffle();
        sleep(time * 2);
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
        dealerHand1.add(card3);
        playerHand.add(card4);
        dealerHand.add(card5);
        dealerHand1.add(card5);
        System.out.println(bundle.getString("burn_pile"));
        sleep(time + 250);
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
        actions.actions();
        ChenScore chenScore = new ChenScore();
        score = chenScore.calculateScore(dealerHand);
        Decision botAction = botServices.botTwoCardAction(bAction.getAction(), score);
        bBet = botServices.getBotBet(botAction, dealerBank);
        dealerBank = dealerBank - bBet;
        pot += bBet;
        checkBetDifference();
        Actions.bet = 0;
    }

    public void flop() {
        Card card1 = deck.draw();
        Card card2 = deck.draw();
        Card card3 = deck.draw();
        sharedCards.add(card1);
        sharedCards.add(card2);
        sharedCards.add(card3);
        sharedCards1.add(card1);
        sharedCards1.add(card2);
        sharedCards1.add(card3);
        System.out.printf(bundle.getString("dealing_flop"), card1);
        sleep(time);
        System.out.printf(bundle.getString("dealing_flop"), card2);
        sleep(time);
        System.out.printf(bundle.getString("dealing_flop"), card3);
        sleep(time);
        System.out.printf(bundle.getString("shared_cards"), sharedCards1);
        sleep(time);
        actions.actions();
        combinedCards = dealerHand;
        combinedCards.add(card1);
        combinedCards.add(card2);
        combinedCards.add(card3);
        score = botServices.check(combinedCards);
        Decision botAction = botServices.botMultiCardAction(bAction.getAction(), (int) score);
        bBet = botServices.getBotBet(botAction, dealerBank);
        dealerBank = dealerBank - bBet;
        pot += bBet;
        checkBetDifference();
        Actions.bet = 0;
    }

    public void turn() {
        Card card4 = deck.draw();
        sharedCards.add(card4);
        sharedCards1.add(card4);
        sleep(time);
        System.out.printf(bundle.getString("dealing_turn"), card4);
        sleep(time);
        System.out.printf(bundle.getString("shared_cards"), sharedCards1);
        sleep(time);
        actions.actions();
        combinedCards.add(card4);
        sharedCards.add(deck.draw());
        Decision botAction = botServices.botMultiCardAction(bAction.getAction(), (int) score);
        bBet = botServices.getBotBet(botAction, dealerBank);
        dealerBank = dealerBank - bBet;
        pot += bBet;
        checkBetDifference();
        Actions.bet = 0;
    }

    public void river() {
        Card card5 = deck.draw();
        sharedCards.add(card5);
        sharedCards1.add(card5);
        sleep(time);
        System.out.printf(bundle.getString("dealing_river"), card5);
        sleep(time);
        System.out.printf(bundle.getString("shared_cards"), sharedCards1);
        sleep(time / 3);
        actions.actions();
        combinedCards.add(card5);
        Decision botAction = botServices.botMultiCardAction(bAction.getAction(), (int) score);
        bBet = botServices.getBotBet(botAction, dealerBank);
        dealerBank = dealerBank - bBet;
        pot += bBet;
        checkBetDifference();
        Actions.bet = 0;
    }

    public void determineWinner() {
        sleep(time);
        System.out.println("Dealer flips over their cards showing..." + dealerHand1);
        String resultCompare = Hands.compares(dealerHand1, playerHand, sharedCards1);
        String replace1 = Hands.printWinner.replace("_", " ");
        String replace2 = replace1.replace(" and", ", and");
        String replace3 = replace2.replace("null", "");
        System.out.printf(replace3);
        sleep(time);
        dealerHand1.clear();
        playerHand.clear();
        sharedCards1.clear();
        if (resultCompare.equals("winner: User")) {
            System.out.println("Dealer: \"Player wins!\"");
            bank += pot;
        } else if (resultCompare.equals("winner: Dealer")) {
            System.out.println("Dealer: \"Dealer wins!\"");
            dealerBank += pot;
        } else {
            System.out.println("Dealer: \"Split pot!\"");
            bank += pot / 2;
            dealerBank += pot / 2;
        }
        sleep(time);
    }

    public void checkBetDifference() {
        if (Actions.bet != bBet) {
            actions.secondAction();
            sleep(750);
            System.out.println("Dealer will play...");
        }
        int tempBet = bBet;
        if (Actions.bet != tempBet) {
            bBet = Actions.bet - tempBet;
            pot = pot + bBet;
            dealerBank = dealerBank - bBet;
        }
    }

    public void sleep(int timer) {
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



