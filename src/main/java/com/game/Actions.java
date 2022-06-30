package com.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Actions {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("strings");
    public static ArrayList<Object> dealerHand = new ArrayList<>();
    public static ArrayList<Object> userHand = new ArrayList<>();
    public static int actionDecision = 0;
    public static int bet = 0;
    private final int time = 1;
    Scanner scanner = new Scanner(System.in);

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
                actionDecision = 1;
                break;
            case 2:
                raise();
                actionDecision = 2;
                break;
            case 3:
                fold();
                actionDecision = 3;
                break;
        }
        actionDecision = caseNum;
        return actionDecision;
    }

    public int secondAction(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What action do you wish to take? Select  \"1\" for raise, or \"2\" for fold. ");
//        menu();
        actionDecision = 0;
        int caseNum = 0;
        String result = scanner.next();
        while (!(caseNum >= 1 && caseNum <= 2)) {
            try {
                caseNum = Integer.parseInt(String.valueOf(Integer.parseInt(result)));
                if (caseNum < 1 || caseNum > 2) {
                    System.out.println(bundle.getString("actions_menu"));
                    result = scanner.next();
                }
            } catch (NumberFormatException ignored) {
                System.out.println(bundle.getString("actions_menu"));
                result = scanner.next();
            }
            if (caseNum >= 1 && caseNum <= 2) {
                break;
            }
        }
        switch (caseNum) {
            case 1:
                raise();
                break;
            case 2:
                fold();
                break;
        }
        actionDecision = caseNum;
        return actionDecision;
    }

    public void check() {
        bet = 0;
        sleep(time);
        System.out.println("Dealer: \"Player checks.\" ");

    }
//    public void call() {
//        int call;
//        call = MyActions.getDealerWager;
//        Game.getBank() = Game.getBank() - call;
//
//        Game.getPot = Game.getPot() + call;
//    }

    public void raise() {

        bet = 0;
        int wager = 0;

        if (Game.getBank() < 10) {
            System.out.println(bundle.getString("raise_bankLow"));
            Actions actions = new Actions();
            actions.actions();
        }
        String[] dealerResponses = {bundle.getString("raise_menu"), bundle.getString("raise_menu1"),bundle.getString("raise_menu2"),bundle.getString("raise_menu3")};
        Random ran = new Random();
        String dealerRandomResponse = dealerResponses[ran.nextInt(dealerResponses.length)];

        sleep(time);


        if (Game.getBank() >= 10) {
            System.out.println(dealerRandomResponse);
            String result = scanner.next();
            sleep(time);
            do {
                if (wager >= 10 && wager <= Game.getBank()) {
                    break;
                }
                try {
                    wager = Integer.parseInt(String.valueOf(result));
                    if (wager < 10) {
                        System.out.println(bundle.getString("raise_betLow"));
                        result = scanner.next();
                    } else if (wager > Game.getBank()) {
                        System.out.println(bundle.getString("raise_betHigh"));
                        result = scanner.next();
                    }
                } catch (NumberFormatException ignored) {
                    System.out.println(bundle.getString("raise_betNope"));
                    result = scanner.next();
                }
            } while (!(wager >= 10 && wager <= Game.getBank()));
        }
        bet = wager;
    }

    public void fold() {
        bet = 0;
        sleep(time);
        System.out.println("Dealer: \"Player folds!\"");
    }

    public void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void menu() {
        String menu = "===============";
        System.out.printf("%nPot:%4d %6s Blinds: %2d %5sBoard:%-21.21s %4sDealer Bank:%4d %n", Game.pot, "", Game.blinds, "", Game.sharedCards.toString(),"", Game.dealerBank);
        System.out.printf("%s %12s %12s %12s %12s\n", menu, menu, menu, menu, menu);
        System.out.printf("%s %10d %15d %15d %14sHand: %s\n", "Enter:", 1, 2, 3, "", Game.playerHand);
        System.out.printf("%s %13s %15s %14s %10s Bank: %4d%n", "Action:", "Check", "Raise", "Fold", "", Game.getBank());
        System.out.printf("%s %12s %12s %12s %12s\n", menu, menu, menu, menu, menu);

    }
}

