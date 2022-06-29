package com.game;

import com.model.team.Card;
import com.model.team.Deck;

public class Client {
    public static void main(String[] args) {

        GameBoard gameBoard = new GameBoard();
        Actions action = new Actions();
//        Game.setBank(500);
//        action.actions();
//        System.out.println("ACTION VALUE=" + Actions.actionDecision);
//        Game game = new Game();
//        Game.dealerBank = 2010;
//        Game.bank = 10;
//        Game.pot = 100;
//        Deck deck = new Deck();
//        Card card1 = deck.draw();
//        Card card2 = deck.draw();
//        Game.sharedCards.add(card1);
//        Game.sharedCards.add(card2);
//        Game.sharedCards.add(card1);
//        Game.sharedCards.add(card2);
//        Game.sharedCards.add(card1);
//        Game.hand.add(card1);
//        Game.hand.add(card2);
//        action.menu();
//        game.flop();


        gameBoard.setGameOptions();
        while (Game.getBank() > 0 || Game.getDealerBank() > 0) {
            gameBoard.playerTurn();
            gameBoard.dealerTurn();

        }

//        dealer.showHand();

    }
}


//ask about text bundles - using interface to incorporate?
// ask about Enums to reference EXIT, etc..
// think about validMove in terms of bet failing, locking while loops, exit etc..
// Controller = referee to manage this
// 300 lines of code in a class? nahhh, break into multiple classes/methods