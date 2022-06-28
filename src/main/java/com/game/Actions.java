package com.game;

import java.util.Scanner;

public class Actions {

    public static int caseNum = 0;
    public static int wager = 0;
    Scanner scanner = new Scanner(System.in);

    public void actions() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("What action do you wish to take? Select \"1\" for check, \"2\" for raise, or \"3\" for fold. ");
        menu();
        while (!(caseNum >= 1 && caseNum <= 3)) {
            try {
                caseNum = Integer.parseInt(String.valueOf(Integer.parseInt(scanner.next())));
                if (caseNum < 1 || caseNum > 3) {
                    System.out.println("You must select an option between 1 and 3!");
                }
            } catch (NumberFormatException ignored) {
                System.out.println("You must select an option between 1 and 3!");
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

    public void check() {
        System.out.println("Dealer: \"Player checks.\" ");


    }

    public int raise() {
        System.out.println("Dealer: \"Player raises! What's your bet sir/ma'am?\"");
        System.out.println("Enter a valid amount to bet between 10 and 100");


        do {
            if (wager >= 10 && wager <= 100) {
                break;
            }
            try {
                wager = Integer.parseInt(String.valueOf(Integer.parseInt(scanner.next())));
                if (wager < 10 || wager > 100) {
                    System.out.println("I'm sorry sir/ma'am, your bet must be between 10 and 100!");
                }

            } catch (NumberFormatException ignored) {
                System.out.println("I'm sorry sir/ma'am, that bet is not recognized in this casino. Please bet a real number between 10 and 100.");
            }
        } while (!(wager >= 10 && wager <= 100));
        return wager;

    }



    public void fold() {
        System.out.println("Dealer: \"Player folds!\"");

    }

    public void menu() {
        String menu = "================";
        System.out.printf("%s %15s %15s %15s\n", menu, menu, menu, menu);
        System.out.printf("%s %12d %15d %16d", "Enter:", 1, 2, 3);
        System.out.printf("\n%s %15s %15s %16s", "Action:", "Check", "Raise", "Fold\n");
        System.out.printf("%s %15s %15s %15s\n", menu, menu, menu, menu);
    }

}
