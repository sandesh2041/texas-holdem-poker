package com.game;

public class Client {
    public static void main(String[] args) {

        GameBoard gameBoard = new GameBoard();
//        Actions action = new Actions();
//        Game.setBank(500);
//        action.actions();
//        Game game = new Game();
//        game.flop();


        gameBoard.createGameStructure();
//
        while(Game.getBank() >0 || Game.getDealerBank()>0){
            gameBoard.repeatGame();

        }

//        dealer.showHand();

    }
}


//ask about text bundles - using interface to incorporate?
// ask about Enums to reference EXIT, etc..
// think about validMove in terms of bet failing, locking while loops, exit etc..
// Controller = referee to manage this
// 300 lines of code in a class? nahhh, break into multiple classes/methods