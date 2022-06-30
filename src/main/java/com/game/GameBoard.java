package com.game;

import com.model.team.Deck;
import com.services.BotServices;

public class GameBoard {
    Game game = new Game();
//    public static int counter = 0;

    public void setGameOptions() {
        game.startGame();
        game.chooseBankValue();
        game.chooseBlinds();
    }

    public void playerTurn() {
        do {
            Actions.bet = 0;
            Game.pot = 0;
            Game.deck = new Deck();
            Game.playerHand.clear();
            Game.dealerHand.clear();
            Game.sharedCards.clear();
            Game.sharedCards1.clear();
            Game.burnPile.clear();
            game.startHandMenu();
            game.dealHand();
            game.preFlopAction();
            game.flop();
            game.turn();
            game.river();
            game.determineWinner();
        } while ((Game.getBank() > Game.getBlinds() && Game.getDealerBank() > Game.getBlinds()));

    }

//    public void dealerTurn() {
//        System.out.println("Dealer: \"Button moves to the player! Dealer has first action.\"");
//        counter++;
//        Game.pot = 0;
//        Game.deck = new Deck();
//        Game.playerHand.clear();
//        Game.dealerHand.clear();
//        Game.sharedCards.clear();
//        Game.burnPile.clear();
//        game.startHandMenu();
//        game.dealHand();
//        game.preFlopAction();
//        game.flop();
//        game.turn();
//        game.river();
//        game.determineWinner();
//    }
//    public boolean flag(){
//        boolean flag = true
//        return flag;
//    }

}
