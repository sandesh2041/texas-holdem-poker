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
////        Game.dealerBank = 510;
////        Game.bank = 500;
////        Game.pot = 100;
//        Deck deck = new Deck();
//        Card card1 = deck.draw();
//        Card card2 = deck.draw();
//        Card card3 = deck.draw();
//        Card card4 = deck.draw();
//        Card card5 = deck.draw();
//        Card card6 = deck.draw();
//        Card card7 = deck.draw();
//        Card card8 = deck.draw();
//        Card card9 = deck.draw();
//        Game.blinds = 20;
//        Game.sharedCards.add(card8);
//        Game.sharedCards.add(card9);
//        Game.sharedCards.add(card7);
//        Game.sharedCards.add(card5);
//        Game.sharedCards.add(card6);
//        Game.hand.add(card1);
//        Game.hand.add(card2);
//        Game.dealerHand.add(card3);
//        Game.dealerHand.add(card4);
//        action.menu();
//        game.flop();
//        game.river();

//
        gameBoard.setGameOptions();
        do {
            gameBoard.playerTurn();
            if (Game.getBank() < Game.getBlinds()) {
                System.out.println("Game Over! You do not have enough money to continue...");
                break;
            } else if (Game.getDealerBank() < Game.getBlinds()) {
                System.out.println("You win! Dealer is out of money!");
                break;
            } else if (Game.getDealerBank() > Game.getBlinds() && Game.getBank() > Game.getBlinds()){
                gameBoard.dealerTurn();
            }
        }
        while (Game.getBank() > Game.getBlinds() || Game.getDealerBank() > Game.getBlinds());

    }

//        dealer.showHand();

}



//ask about text bundles - using interface to incorporate?
// ask about Enums to reference EXIT, etc..
// think about validMove in terms of bet failing, locking while loops, exit etc..
// Controller = referee to manage this
// 300 lines of code in a class? nahhh, break into multiple classes/methods