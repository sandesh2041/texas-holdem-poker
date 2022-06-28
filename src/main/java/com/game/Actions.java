package com.game;

import java.util.ResourceBundle;
import java.util.Scanner;

public class Actions {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("strings");

    public static int caseNum = 0;
    public static int bet = 0;
    private final int time = 750;
    Scanner scanner = new Scanner(System.in);

    public void actions() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("What action do you wish to take? Select \"1\" for check, \"2\" for raise, or \"3\" for fold. ");
        menu();
        caseNum=0;
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
                check();
                break;
            case 2:
                raise();
                break;
            case 3:
                fold();
                break;
        }
    }

    public void check(){
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

        if (Game.getBank() < 10){
            System.out.println(bundle.getString("raise_bankLow"));
            Actions actions = new Actions();
            actions.actions();
        }
        sleep(time);
        System.out.println("Dealer: \"Player raises! What's your bet sir/ma'am?\"");
        sleep(time);
        System.out.println("Enter a valid amount to bet between 10 and 100");
        bet = 0;
        int wager = Integer.parseInt(String.valueOf(Integer.parseInt(scanner.next())));
        if (Game.getBank() >= 10) {
            do {
                if (wager >= 10 && wager <= 100 && wager <= Game.getBank()) {
                    break;
                }
                try {
                    wager = Integer.parseInt(String.valueOf(Integer.parseInt(scanner.next())));

                    if (wager < 10 || wager > 100) {
                        System.out.println("Dealer: \"I'm sorry sir/ma'am, your bet must be between 10 and 100!\"");
                    } else {
                        System.out.println("\"Dealer: \"Sorry sir/ma'am, you can not bet more money than you have!\"");
                    }

                } catch (NumberFormatException ignored) {
                    System.out.println("\"Dealer: \"I'm sorry sir/ma'am, that bet is not recognized in this casino. Please bet a whole number between 10 and 100.\"");
                }
            } while (!(wager >= 10 && wager <= 100 && wager <= Game.getBank()));
        }
        bet = wager;
    }
//        while (Game.getBank()<10){
//            game.sleep(500);
//            System.out.println("CURRENT TEST: You do not have enough to bet!");
//            actions();
//            break;
//        }

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
        String menu = "=============";
        System.out.printf("%s %12s %12s %12s %12s\n", menu, menu, menu, menu, menu);
        System.out.printf("%s %8d %13d %13d %24s", "Enter:", 1, 2, 3, "Hand"+Game.getHand());
        System.out.printf("\n%s %11s %13s %12s %17s\n", "Action:", "Check", "Raise", "Fold","Bank:"+Game.getBank());
        System.out.printf("%s %12s %12s %12s %12s\n", menu, menu, menu, menu, menu);
    }
}
