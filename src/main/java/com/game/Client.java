package com.game;

import com.model.team.Card;
import com.model.team.Deck;
import com.model.team.Hands;

public class Client {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();

        gameBoard.setGameOptions();
        do {
            gameBoard.playerTurn();
            if (Game.getBank() < Game.getBlinds()) {
                System.out.println("Game Over! You do not have enough money to continue...");
                break;
            } else if (Game.getDealerBank() < Game.getBlinds()) {
                System.out.println("You win! Dealer is out of money!");
                break;
            }
        }
        while (Game.getBank() > Game.getBlinds() || Game.getDealerBank() > Game.getBlinds());

    }
}
//        while (Game.getBank() > 0 || Game.getDealerBank() > 0) {
//            gameBoard.playerTurn();
//            gameBoard.dealerTurn();
//
//        }

//        dealer.showHand();

//}
//        do {
//                if (Game.getBank() < Game.getBlinds()) {
//        System.out.println("Game Over! You do not have enough money to continue...");
//        break;
//        } else if (Game.getDealerBank() < Game.getBlinds()) {
//        System.out.println("You win! Dealer is out of money!");
//        break;
//        } else {
//        int trueFalse = GameBoard.counter % 2;
//        boolean flag = (trueFalse == 0);
//        if (flag) {
//        gameBoard.playerTurn();
//        } else if (!flag) {
//        gameBoard.dealerTurn();
//
//        }
//
//
//        }
//        }