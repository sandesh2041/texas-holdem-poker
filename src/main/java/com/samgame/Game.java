package com.samgame;


import com.model.enums.Decision;
import com.model.team.Card;
import com.model.team.Deck;
import com.model.team.Hands;
import com.model.utils.ChenScore;
import com.services.BotServices;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

public class Game {

    private final int time = 1; //750;
    public static int bank = 0;
    public static int dealerBank = 0;
    public static int pot = 0;
    public static int blinds = 0;

    double score;
    int bBet = 0;
    BotServices botServices = new BotServices();
    BotActions bAction = new BotActions();
    Actions actions = new Actions();

    public static Deck deck = new Deck();
    Scanner scanner = new Scanner(System.in);

    public static  ArrayList<Card> hand = new ArrayList<>();
    public static ArrayList<Card> dealerHand = new ArrayList<>();
    public static ArrayList<Card> burnPile = new ArrayList<>();
    public static ArrayList<Card> sharedCards = new ArrayList<>();
    private final ResourceBundle bundle = ResourceBundle.getBundle("strings");

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
        hand.add(card2);
        dealerHand.add(card3);
        hand.add(card4);
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

        //Start a while loop to execute until player bet amount = bot bet amount
        //If bot amount is more than player, ask player to call or raise or all in, but no check option

        actions.actions();

        //Bot actions
        ChenScore chenScore = new ChenScore();
        score = chenScore.calculateScore(dealerHand); //Calculated Chen Score
        Decision botAction = botServices.botTwoCardAction(bAction.getAction(), score); //Determine what bot will do with initial 2 pocket card
        bBet = botServices.getBotBet(botAction, dealerBank);
        System.out.println("Dealer Hand: " + dealerHand);
        System.out.println("Bot Bet is : " + bBet);

        bank = bank - Actions.bet;
        System.out.println("Player Bank: " + bank);
        pot = pot + Actions.bet + bBet;
        System.out.println("Pot Money: " + pot);
        dealerBank = dealerBank - bBet;
        System.out.println("Dealer Bank: " + dealerBank);
        checkBetDifference();

    }

    public void flop() {

        Card card1 = deck.draw();
        Card card2 = deck.draw();
        Card card3 = deck.draw();
        sharedCards.add(card1);
        sharedCards.add(card2);
        sharedCards.add(card3);
        System.out.printf(bundle.getString("dealing_flop"),card1);
        sleep(time);
        System.out.printf(bundle.getString("dealing_flop"),card2);
        sleep(time);
        System.out.printf(bundle.getString("dealing_flop"),card3);
        sleep(time);
        System.out.printf(bundle.getString("shared_cards"),sharedCards);
        sleep(time);

        //Start a while loop to execute until player bet amount = bot bet amount
        //If bot amount is more than player, ask player to call or raise or all in, but no check option
        actions.actions();

        combinedCards = dealerHand;
        combinedCards.add(card1);
        combinedCards.add(card2);
        combinedCards.add(card3);

        //Bot action
        score = botServices.check(combinedCards);
        Decision botAction =  botServices.botMultiCardAction(bAction.getAction(), (int)score);
        bBet = botServices.getBotBet(botAction, dealerBank);
        System.out.println("Combined Cards: " + combinedCards);
        System.out.println("Bot Bet is : " + bBet);

        bank = bank - Actions.bet;
        System.out.println("Player Bank: " + bank);
        pot = pot + Actions.bet + bBet;
        System.out.println("Pot Money: " + pot);
        dealerBank = dealerBank - bBet;
        System.out.println("Dealer Bank: " + dealerBank);
        checkBetDifference();
    }

    public void turn() {
//        bank = bank - Actions.bet;
//        System.out.println("Player Bank: " + bank);
//        pot = pot + Actions.bet + bBet;
//        System.out.println("Pot money: " + pot);
//        dealerBank = dealerBank - bBet;
//        System.out.println("Dealer Bank: " + dealerBank);

        Card card1 = deck.draw();
        sharedCards.add(card1);
        System.out.printf(bundle.getString("dealing_turn"),card1);
        sleep(time);
        System.out.printf(bundle.getString("shared_cards"),sharedCards);
        sleep(time);

        //Start a while loop to execute until player bet amount = bot bet amount
        //If bot amount is more than player, ask player to call or raise or all in, but no check option
        actions.actions();

        //Bot action
        sharedCards.add(deck.draw());
        Decision botAction = botServices.botMultiCardAction(bAction.getAction(), (int)score);
        bBet = botServices.getBotBet(botAction, dealerBank);
        System.out.println("Combined Cards: " + combinedCards);
        System.out.println("Bot Bet is : " + bBet);

        bank = bank - Actions.bet;
        System.out.println("Player Bank: " + bank);
        pot = pot + Actions.bet + bBet;
        System.out.println("Pot money: " + pot);
        dealerBank = dealerBank - bBet;
        System.out.println("Dealer Bank: " + dealerBank);
        checkBetDifference();

    }
    public void river(){
//        bank = bank - Actions.bet;
//        System.out.println("Player Bank: " + bank);
//        pot = pot + Actions.bet + bBet;
//        System.out.println("Pot money: " + pot);
//        dealerBank = dealerBank - bBet;
//        System.out.println("Dealer Bank: " + dealerBank);

        Card card1 = deck.draw();
        sharedCards.add(card1);

        System.out.printf(bundle.getString("dealing_river"),card1);
        System.out.printf(bundle.getString("shared_cards"),sharedCards);
        sleep(time);

        //Start a while loop to execute until player bet amount = bot bet amount
        //If bot amount is more than player, ask player to call or raise or all in, but no check option

        actions.actions();

        //Bot Action
        sharedCards.add(deck.draw());
        Decision botAction = botServices.botMultiCardAction(bAction.getAction(), (int)score);
        bBet = botServices.getBotBet(botAction, dealerBank);
        System.out.println("Combined Cards: " + combinedCards);
        System.out.println("Bot Bet is : " + bBet);

        bank = bank - Actions.bet;
        System.out.println("Player Bank: " + bank);
        pot = pot + Actions.bet + bBet;
        System.out.println("Pot money: " + pot);
        dealerBank = dealerBank - bBet;
        System.out.println("Dealer Bank: " + dealerBank);
        checkBetDifference();

//        boolean winnerResult = Hands.compares(dealerHand,hand,sharedCards).equals(hand) ? true : false;
//        System.out.println(winnerResult);
        System.out.println(printWinner());
        if (Hands.compares(dealerHand,hand,sharedCards).equals(hand)){
            bank+=pot;
        }
        else if (Hands.compares(dealerHand,hand,sharedCards).equals(dealerHand)){
            dealerBank+=pot;
        }
        else {
            bank+=pot/2;
            dealerBank+=pot/2;
        }
    }
    public static String printWinner() {
        String dealerResult = "";
        String userResult = "";
        Hands.compares(dealerHand,hand,sharedCards);

        for (int i = 1; i < Hands.dealerSharedHand.size(); i++) {
            dealerResult += Hands.dealerSharedHand.get(i) + " ";
        }
        for (int i = 1; i < Hands.playerSharedHand.size(); i++) {
            userResult += Hands.playerSharedHand.get(i) + " ";
        }
        String message = "Dealer: \"The dealer hand is " + dealerHand + " and has a " + dealerResult + "\"\n"
                + "Dealer: \"The player hand is " + hand + " and has a " + userResult;
//        System.out.println("The dealer hand is " + dealerResult);
//        System.out.println("The user hand is " + userResult);
        return message;
    }

    public void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Card> getHand() {
//        Deck deck = new Deck();
//        Card card1 = deck.draw();
//        Card card2 = deck.draw();
//        hand.add(card1);
//        hand.add(card2);
        List<Card> filtered = hand.stream().collect(Collectors.toList());
        return filtered;
    }

    public static int getBank() {
        return bank;
    }
    public static void setBank(int bank) {
        Game.bank = bank;
    }

    public static int getDealerBank() {
        return dealerBank;
    }

    public static void setDealerBank(int dealerBank) {
        Game.dealerBank = dealerBank;
    }

    public static int getBlinds() {
        return blinds;
    }
    public static int getPot() {
        return pot;
    }

    public static ArrayList<Card> getSharedCards() {
        return sharedCards;
    }

    public void checkBetDifference(){
        //Need to make change to bank amounts
        if(Actions.bet != bBet) {
            System.out.println("Dealer CALLED!!! What you want to do?");
            actions.secondAction();
            System.out.println("Player Made RAISE!!!! " + Actions.bet);
            bank = bank - Actions.bet;
            System.out.println("Player Bank: " + bank);
            pot = pot + Actions.bet;
            System.out.println("Pot money: " + pot);
            System.out.println("Dealer Bank: " + dealerBank);

        }
        int tempBet = bBet;
        if(Actions.bet != tempBet){
            bBet = Actions.bet - tempBet;
            System.out.println("Computer CALLED!!!!!! " + bBet);
            System.out.println("Player Bank: " + bank);
            pot = pot  + bBet;
            System.out.println("Pot money: " + pot);
            dealerBank = dealerBank - bBet;
            System.out.println("Dealer Bank: " + dealerBank);
        }
    }
}