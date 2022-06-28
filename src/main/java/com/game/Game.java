package com.game;

import com.model.enums.Actions;
import com.model.team.Card;
import com.model.team.Deck;
import com.model.utils.ChenScore;
import com.services.BotServices;

import java.util.*;

public class Game {

    public static int bank = 0;
    public static int dealerBank = 0;
    public static int pot = 0;
    public static int blinds = 0;

    double score;
    int bBet = 0;

    Deck deck = new Deck();
    BotServices botServices = new BotServices();
    MyAction playerAction = new MyAction();

    private final Comparator<Card> displayComparator = Comparator.comparing((Card c) -> c.getSuit().getColor()).thenComparing(Card::getSuit).thenComparing(Card::getRank);

    private final List<Card> hand = new ArrayList<>();
    private final List<Card> dealerHand = new ArrayList<>();
    private final List<Card> burnPile = new ArrayList<>();
    private final List<Card> sharedCards = new ArrayList<>();

    private List<Card> combinedCards = new ArrayList<>();
    private List<Card> bestCards = new ArrayList<>();

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
            } else {
                System.out.println("Please type \"y\" or \"yes\" to play. Or type \"exit\" to exit game.");
                startGame = scanner.next();
            }
        } while (!(startGame.equalsIgnoreCase("y")) && !(startGame.equalsIgnoreCase("yes")));

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

        sleep(250);
        System.out.printf("Your current bank value: $%d.%nDealer's current bank value: $%d.%n", getBank(), getDealerBank());
        sleep(250);
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
        sleep(250);
        System.out.println("Shuffling cards...");
        deck.shuffle();
        sleep(250);

        System.out.println("Cards are shuffled! Please type \"d\" or \"deal\" to deal cards.");
        String deal = scanner.next();
        do {
            if (deal.equalsIgnoreCase("deal") || deal.equalsIgnoreCase("d")) {
                break;
            } else if (deal.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the game");
                System.exit(0);
            } else {
                System.out.println("Please type \"d\" or \"deal\" to deal cards, or \"exit\" to exit the game.");
                deal = scanner.next();
            }
        } while (!(deal.equalsIgnoreCase("deal")) && !(deal.equalsIgnoreCase("d")));

        System.out.println("Dealing hands...");
        dealHand();
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
        sleep(250);


        System.out.println("Your first card is " + card1);
        sleep(250);
        System.out.println("Dealer has received their first card.");
        sleep(250);
        System.out.println("Your second card is " + card2);
        sleep(250);
        System.out.println("Dealer has received their second card.");
        sleep(250);

        showHand();
        sleep(250);
//        preFlopAction();


    }

    public void showHand() {
        System.out.println("Your hand is " + hand);
    }

//    public void preFlopAction() {
//        Scanner scanner = new Scanner(System.in);
//        bank = bank - blinds;
//        pot = blinds * 2;
//        dealerBank = dealerBank - blinds;
//        System.out.println("It is your turn, please enter \"c\" or \"check\" to check, or y");
//        Actions action = new Actions();
//        action.actions();
//        System.out.println("SHOULDNT SEE THIS YET!!!");
//    }

    public void preFlopActionV1() {
//       Scanner scanner = new Scanner(System.in);
        bank = bank - blinds;
        pot = blinds * 2;
        dealerBank = dealerBank - blinds;
 //       System.out.println("It is your turn, please enter \"c\" or \"check\" to check, or y");
//        System.out.println("SHOULDNT SEE THIS YET!!!");


        //Bot actions
        ChenScore chenScore = new ChenScore();
        score = chenScore.calculateScore(dealerHand); //Calculated Chen Score
        Actions botAction = botServices.botTwoCardAction(playerAction.getAction(), score); //Determine what bot will do with initial 2 pocket card
        bBet = GetBotBet(botAction);
    }

    public void flop() {
        Card card1 = deck.draw();
        Card card2 = deck.draw();
        Card card3 = deck.draw();
        sharedCards.add(card1);
        sharedCards.add(card2);
        sharedCards.add(card3);
        showSharedCards();

        combinedCards = dealerHand;
        combinedCards.add(card1);
        combinedCards.add(card2);
        combinedCards.add(card3);

        score = botServices.check(combinedCards);
        Actions botAction =  botServices.botMultiCardAction(playerAction.getAction(), (int)score);
        bBet = GetBotBet(botAction);

    }

    public void turn() {
        sharedCards.add(deck.draw());
        showSharedCards();
        Actions botAction = botServices.botMultiCardAction(playerAction.getAction(), (int)score);
        bBet = GetBotBet(botAction);


    }

    public void river() {
        sharedCards.add(deck.draw());
        showSharedCards();
        Actions botAction = botServices.botMultiCardAction(playerAction.getAction(), (int)score);
        bBet = GetBotBet(botAction);
    }

    private int GetBotBet(Actions botAction) {
        int botBet = 0;
        if(botAction == Actions.CALL){
            if (dealerBank < playerAction.getWager()) {
                bBet = dealerBank;
            } else {
                if(playerAction.getAction() == Actions.CHECK){
                    bBet = 10;
                } else {
                    bBet = playerAction.getWager() - bBet;
                }
            }
        }
        return botBet;
    }



    public void showSharedCards() {
        System.out.println("The current shared cards is " + sharedCards);
    }


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


    public void bet() {
        Scanner userInput = new Scanner(System.in);
        int choice = userInput.nextInt();
        while (choice <= 10) {
            System.out.println("The Minimum is 10$, please enter a higher number.");
            choice = userInput.nextInt();
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
