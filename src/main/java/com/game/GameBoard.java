package com.game;

import com.model.team.Deck;
import com.services.BotServices;

/**
 * Creates the Game board in order to play the game.
 */
public class GameBoard {
    Game game = new Game();

    /**
     * Sets the game options
     */
    public void setGameOptions() {
        game.startGame();
        game.chooseBankValue();
        game.chooseBlinds();
    }

    /**
     * Does the players turn.
     */
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
