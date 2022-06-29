//package com.game;
//
//import java.util.ResourceBundle;
//import java.util.Scanner;
//
//public class AIMethods {
//
//
//        private static final ResourceBundle bundle = ResourceBundle.getBundle("strings");
//
//        public static boolean flag;
//        public static int actionDecision = Actions.actionDecision;
//        public static int bet = 0;
//        private final int time = 750;
//        Scanner scanner = new Scanner(System.in);
//
//        public int aiActions() {
//
//
//
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("What action do you wish to take? Select \"1\" for check, \"2\" for raise, or \"3\" for fold. ");
//            menu();
//
//
//
//            int caseNum = Actions.actionDecision;
////            String result = scanner.next();
////            while (!(caseNum >= 1 && caseNum <= 3)) {
////                try {
////                    caseNum = Integer.parseInt(String.valueOf(Integer.parseInt(result)));
////                    if (caseNum < 1 || caseNum > 3) {
////                        System.out.println(bundle.getString("actions_menu"));
////                        result = scanner.next();
////                    }
////                } catch (NumberFormatException ignored) {
////                    System.out.println(bundle.getString("actions_menu"));
////                    result = scanner.next();
////                }
////                if (caseNum >= 1 && caseNum <= 3) {
////                    break;
////                }
////            }
//            if (flag == true){
//
//            if (chenScore > 10 && Game.dealerBank > 10) {
//                raise(10);
//            } else if (chenScore >15 && Game.dealerBank>50){
//                raise(50);
//
//            }
//            else if (chenScore <10){
//                check();
//            }
//            switch (caseNum) {
//                case 1:
//                    check();
//                    break;
//                case 2:
//                    raise();
//                    break;
//                case 3:
//                    fold();
//                    break;
//            }
//            actionDecision = caseNum;
//            return actionDecision;
//        } }
//
//        public void check() {
//            bet = 0;
//            sleep(time);
//            System.out.println("Dealer: \"Player checks.\" ");
//
//        }
////    public void call() {
////        int call;
////        call = MyActions.getDealerWager;
////        Game.getBank() = Game.getBank() - call;
////
////        Game.getPot = Game.getPot() + call;
////    }
//
//        public void raise() {
//
//            if (Game.getBank() < 10) {
//                System.out.println(bundle.getString("raise_bankLow"));
//                com.game.Actions actions = new com.game.Actions();
//                actions.actions();
//            }
//            sleep(time);
//            System.out.println("Dealer: \"Player raises! What's your bet sir/ma'am?\"");
//            sleep(time);
//            System.out.println("Enter a valid amount to bet between 10 and 100");
//            bet = 0;
//            int wager = 0;
//            String result = scanner.next();
//            if (Game.getBank() >= 10) {
//                do {
//                    if (wager >= 10 && wager <= Game.getBank()) {
//                        break;
//                    }
//                    try {
//                        wager = Integer.parseInt(String.valueOf(result));
//                        if (wager < 10) {
//                            System.out.println(bundle.getString("raise_betLow"));
//                        } else if (wager > Game.getBank()) {
//                            System.out.println(bundle.getString("raise_betHigh"));
//                        }
//                    } catch (NumberFormatException ignored) {
//                        System.out.println("\"Dealer: \"I'm sorry sir/ma'am, that bet is not recognized in this casino. Please bet a whole number between 10 and 100.\"");
//                        result = scanner.next();
//                    }
//                } while (!(wager >= 10 && wager <= Game.getBank()));
//            }
//            bet = wager;
//        }
////        while (Game.getBank()<10){
////            game.sleep(500);
////            System.out.println("CURRENT TEST: You do not have enough to bet!");
////            actions();
////            break;
////        }
//
//        public void fold() {
//            bet = 0;
//            sleep(time);
//            System.out.println("Dealer: \"Player folds!\"");
//        }
//
//        public void sleep(int timer) {
//            try {
//                Thread.sleep(timer);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        //    public void menu() {
////        String menu = "=============";
////        System.out.printf("%s %12s %12s %12s %12s\n", menu, menu, menu, menu, menu);
////        System.out.printf("%s %8d %13d %13d %24s", "Enter:", 1, 2, 3, "Hand"+Game.getHand());
////        System.out.printf("\n%s %11s %13s %12s %17s\n", "Action:", "Check", "Raise", "Fold","Bank:"+Game.getBank());
////        System.out.printf("%s %12s %12s %12s %12s\n", menu, menu, menu, menu, menu);
////    }
//        public void menu() {
//            String menu = "===============";
//            System.out.printf("%nPot: %4d %15s Board: %8s  %8s Dealer Bank: %4d%n", Game.pot,"", Game.sharedCards, "", Game.dealerBank);
//            System.out.printf("%s %12s %12s %12s %12s\n", menu, menu, menu, menu, menu);
//            System.out.printf("%s %10d %15d %15d %14sHand: %s\n", "Enter:", 1, 2, 3, "", Game.getHand());
//            System.out.printf("%s %13s %15s %14s %10s Bank: %4d%n", "Action:", "Check", "Raise", "Fold","", Game.getBank());
//            System.out.printf("%s %12s %12s %12s %12s\n", menu, menu, menu, menu, menu);
//
//        }
//
//    public static void setFlag(boolean flag) {
//        AIMethods.flag = flag;
//    }
//}
//
//
//
