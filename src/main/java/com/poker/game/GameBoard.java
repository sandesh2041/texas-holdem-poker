package com.poker.game;

import com.poker.model.Deck;

public class GameBoard {
    Game game = new Game();

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
}
