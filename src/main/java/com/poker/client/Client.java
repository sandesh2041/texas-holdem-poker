package com.poker.client;

import com.poker.game.Game;
import com.poker.game.GameBoard;

/**
 * Fills the game board and puts it into a while loop in order to play the game.
 */
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
