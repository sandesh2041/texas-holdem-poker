package com.poker.game;

import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Creates new actions that you are able to use, fold, call, raise, check.
 */
public class Actions {
    private final int time = 700;
    private static final ResourceBundle bundle = ResourceBundle.getBundle("strings");

    public static int actionDecision = 0;
    public static int bet = 0;
    Scanner scanner = new Scanner(System.in);

    /**
     * Allows the player to select actions.
     * @return returns the action taken.
     */
    public int actions() {
        Scanner scanner = new Scanner(System.in);
        sleep(time);
        System.out.println(bundle.getString("actions_menu"));
        menu();
        actionDecision = 0;
        int caseNum = 0;
        String result = scanner.next();
        while (!(caseNum >= 1 && caseNum <= 3)) {
            try {
                caseNum = Integer.parseInt(String.valueOf(Integer.parseInt(result)));
                if (caseNum < 1 || caseNum > 3) {
                    System.out.println(bundle.getString("actions_menuError"));
                    result = scanner.next();
                }
            } catch (NumberFormatException ignored) {
                System.out.println(bundle.getString("actions_menuError"));
                result = scanner.next();
            }
            if (caseNum >= 1 && caseNum <= 3) {
                break;
            }
        }
        actionDecision = caseNum;
        switch (caseNum) {
            case 1:
                check();
                break;
            case 2:
                raise();
                break;
            case 3:
                fold();
                break;
        }
        actionDecision = caseNum;
        return actionDecision;
    }

    /**
     * Allows the dealer AI to select actions.
     * @return returns the action taken.
     */
    public int secondAction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(bundle.getString("actions_menu1"));
        menu1();
        actionDecision = 0;
        int caseNum = 0;
        String result = scanner.next();
        while (!(caseNum >= 1 && caseNum <= 3)) {
            try {
                caseNum = Integer.parseInt(String.valueOf(Integer.parseInt(result)));
                if (caseNum < 1 || caseNum > 3) {
                    System.out.println(bundle.getString("actions_menu"));
                    result = scanner.next();
                }
            } catch (NumberFormatException ignored) {
                System.out.println(bundle.getString("actions_menu"));
                result = scanner.next();
            }
            if (caseNum >= 1 && caseNum <= 3) {
                break;
            }
        }
        switch (caseNum) {
            case 1:
                call();
                break;
            case 2:
                raise();
                break;
            case 3:
                fold();
                break;
        }
        actionDecision = caseNum;
        return actionDecision;
    }

    /**
     * Calls the check method and assigns the bet to zero.
     */
    public void check() {
        bet = 0;
        System.out.println(bundle.getString("actions_check"));
    }

    /**
     * Calls the call method and then removes the amount from bot bet and into bank.
     */
    public void call() {
        if (Game.bank >= Game.bBet) {
            Game.pot += Game.bBet * 2;
            Game.bank -= Game.bBet;
            Game.dealerBank -= Game.bBet;
        } else {
            Game.pot = Game.pot + (Game.bank * 2);
            Game.dealerBank = Game.dealerBank + Game.bBet - Game.bank;
            Game.bank = 0;
        }
        System.out.println(bundle.getString("actions_call"));
    }

    /**
     * Gives the raised amount.
     */
    public void raise() {
        bet = 0;
        int wager = 0;
        if (Game.getBank() < 10) {
            System.out.println(bundle.getString("raise_bankLow"));
            Actions actions = new Actions();
            actions.actions();
        }
        String[] dealerResponses = {bundle.getString("raise_menu"), bundle.getString("raise_menu1"), bundle.getString("raise_menu2"), bundle.getString("raise_menu3")};
        Random ran = new Random();
        String dealerRandomResponse = dealerResponses[ran.nextInt(dealerResponses.length)];
        if (Game.getBank() >= 10) {
            System.out.println(dealerRandomResponse);
            String result = scanner.next();
            do {
                try {
                    wager = Integer.parseInt(String.valueOf(result));
                    if (wager < 10) {
                        System.out.println(bundle.getString("raise_betLow"));
                        result = scanner.next();
                    } else if (wager > Game.getBank()) {
                        System.out.println(bundle.getString("raise_betHigh"));
                        result = scanner.next();
                    } else if (wager < Game.bBet) {
                        System.out.println(bundle.getString("raise_higherDealer"));
                        result = scanner.next();
                    }
                } catch (NumberFormatException ignored) {
                    System.out.println(bundle.getString("raise_betNope"));
                    result = scanner.next();
                }
                if ((wager >= 10 && wager <= Game.getBank()) && wager >= Game.bBet) {
                    break;
                }
            } while (wager < 10 || wager >= Game.getBank() || wager < Game.bBet);
        }
        bet = wager;
        Game.bank -= bet;
        Game.pot += bet;
    }

    /**
     * Allows the user to fold and assigns the pot to the dealer.
     */
    public void fold() {
        Game.dealerBank += Game.pot;
        System.out.println(bundle.getString("actions_fold"));
        sleep(time);
        GameBoard gameboard = new GameBoard();
        gameboard.playerTurn();
    }

    /**
     * Waiting amount to slow down things a bit.
     * @param timer Takes an input in MS.
     */
    public void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Shows the user a menu of that is provided similar to a GUI.
     */
    public void menu() {
        String menu = "===============";
        System.out.printf("%nPot:%4d %6s Blinds: %2d %5sBoard:%-21.21s %4sDealer Bank:%4d %n", Game.pot, "", Game.blinds, "", Game.sharedCards1.toString(), "", Game.dealerBank);
        System.out.printf("%s %12s %12s %12s %12s\n", menu, menu, menu, menu, menu);
        System.out.printf("%s %10d %15d %15d %14sHand: %s\n", "Enter:", 1, 2, 3, "", Game.playerHand);
        System.out.printf("%s %13s %15s %14s %10s Bank: %4d%n", "Action:", "Check", "Raise", "Fold", "", Game.getBank());
        System.out.printf("%s %12s %12s %12s %12s\n", menu, menu, menu, menu, menu);
    }

    /**
     * Shows the user a menu of that is provided similar to a GUI if the dealer raises.
     */
    public void menu1() {
        String menu = "===============";
        System.out.printf("%nPot:%4d %6s Blinds: %2d %5sBoard:%-21.21s %4sDealer Bank:%4d %n", Game.pot, "", Game.blinds, "", Game.sharedCards1.toString(), "", Game.dealerBank);
        System.out.printf("%s %12s %12s %12s %12s\n", menu, menu, menu, menu, menu);
        System.out.printf("%s %10d %15d %15d %14sHand: %s\n", "Enter:", 1, 2, 3, "", Game.playerHand);
        System.out.printf("%s %12s %16s %14s %10s Bank: %4d%n", "Action:", "Call", "Raise", "Fold", "", Game.getBank());
        System.out.printf("%s %12s %12s %12s %12s\n", menu, menu, menu, menu, menu);
    }
}

