package com.poker.game;

import com.poker.model.Deck;

/**
 * Creates the game structure in order to play the game by order of methods
 * as appropriate, they will set the game options then start the sequence of
 * Texas Hold'em - pre-flop, flop, turn and river actions.
 */
public class GameBoard {
    Game game = new Game();

    /**
     * Orders the user select screens for setting game options.
     */
    public void setGameOptions() {
        game.startGame();
        game.chooseBankValue();
        game.chooseBlinds();
    }

    /**
     * Order of sequence that the player will go first, in new versions of this game,
     * a dealerTurn() method may be created that allows user to go first.
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
