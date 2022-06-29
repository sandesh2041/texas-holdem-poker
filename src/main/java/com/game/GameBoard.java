package com.game;

import com.model.team.Deck;

public class GameBoard {
    Game game = new Game();

    public void setGameOptions() {
        game.startGame();
        game.chooseBankValue();
        game.chooseBlinds();
    }

    public void playerTurn() {
//        Actions action = new Actions();
        Game.pot = 0;
        Game.deck = new Deck();
        Game.playerHand.clear();
        Game.dealerHand.clear();
        Game.sharedCards.clear();
        Game.burnPile.clear();
        game.startHandMenu();
        game.dealHand();
        game.preFlopAction();
        game.flop();
        game.turn();
        game.river();
        game.determineWinner();
    }

    public void dealerTurn() {
//        Actions action = new Actions();
        Game.pot = 0;
        Game.deck = new Deck();
        Game.playerHand.clear();
        Game.dealerHand.clear();
        Game.sharedCards.clear();
        Game.burnPile.clear();
        game.startHandMenu();
        game.dealHand();
        game.preFlopAction();
        game.flop();
        game.turn();
        game.river();
        game.determineWinner();
    }
//    public boolean flag(){
//        boolean flag = true
//        return flag;
//    }

}
