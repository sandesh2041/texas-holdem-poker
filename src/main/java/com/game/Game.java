package com.game;

import com.model.enums.Decision;
import com.model.team.Card;
import com.model.team.Deck;
import com.model.team.Hands;
import com.model.utils.ChenScore;
import com.services.BotServices;

import java.util.*;

public class Game {

    private final int time = 1;
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

    public static ArrayList<Card> playerHand = new ArrayList<>();
    public static ArrayList<Card> dealerHand = new ArrayList<>();
    public static ArrayList<Card> burnPile = new ArrayList<>();
    public static ArrayList<Card> sharedCards = new ArrayList<>();
    private final ResourceBundle bundle = ResourceBundle.getBundle("strings");

    private ArrayList<Card> combinedCards = new ArrayList<>(); //Remove later

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

        actions.actions();

        //Bot actions
        ChenScore chenScore = new ChenScore();
        score = chenScore.calculateScore(dealerHand); //Calculated Chen Score
        Decision botAction = botServices.botTwoCardAction(bAction.getAction(), score); //Determine what bot will do with initial 2 pocket card
        bBet = botServices.getBotBet(botAction, dealerBank);

        bank = bank - Actions.bet;
        pot = pot + Actions.bet + bBet;
        dealerBank = dealerBank - bBet;
        checkBetDifference();
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

        actions.actions();

        //Remove below 4 lines
        combinedCards = dealerHand;
        combinedCards.add(card1);
        combinedCards.add(card2);
        combinedCards.add(card3);

        //Bot action
        score = botServices.check(combinedCards);
        Decision botAction =  botServices.botMultiCardAction(bAction.getAction(), (int)score);
        bBet = botServices.getBotBet(botAction, dealerBank);

        bank = bank - Actions.bet;
        pot = pot + Actions.bet + bBet;
        dealerBank = dealerBank - bBet;
        checkBetDifference();
    }

    public void turn() {
        Card card1 = deck.draw();
        sharedCards.add(card1);
        sleep(time);
        System.out.printf(bundle.getString("dealing_turn"), card1);
        sleep(time);
        System.out.printf(bundle.getString("shared_cards"), sharedCards);
        sleep(time);

        actions.actions();

        combinedCards.add(card1);

        sharedCards.add(deck.draw());
        Decision botAction = botServices.botMultiCardAction(bAction.getAction(), (int)score);
        bBet = botServices.getBotBet(botAction, dealerBank);

        bank = bank - Actions.bet;
        pot = pot + Actions.bet + bBet;
        dealerBank = dealerBank - bBet;
        checkBetDifference();
    }

    public void river() {
        Card card1 = deck.draw();
        sharedCards.add(card1);

        sleep(time);
        System.out.printf(bundle.getString("dealing_river"), card1);
        sleep(time);
        System.out.printf(bundle.getString("shared_cards"), sharedCards);
        sleep(time);

        actions.actions();

        combinedCards.add(card1);

        bank = bank - Actions.bet;
        pot = pot + Actions.bet + bBet;
        dealerBank = dealerBank - bBet;
        checkBetDifference();
    }


    public void determineWinner() {
        sleep(time);
        System.out.println("Dealer flips over their cards showing..." + dealerHand);

        String resultCompare = Hands.compares(dealerHand, playerHand, sharedCards);
        String replace1 = Hands.printWinner.replace("_", " ");
        String replace2 = replace1.replace(" and",", and");
        String replace3 = replace2.replace("null","");
        System.out.printf(replace3);
        sleep(time);

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



