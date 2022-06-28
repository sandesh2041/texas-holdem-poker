package com.game;

import com.model.team.Deck;

public class GameBoard {
    Game game = new Game();

    public void createGameStructure() {
        game.startGame();
        game.chooseBankValue();
        game.chooseBlinds();
        game.dealCardsMenu();
        game.dealHand();
        game.preFlopAction();
        game.flop();
        game.turn();
        game.river();

    }

    public void repeatGame() {
        Actions action = new Actions();
        Game.pot = 0;
        Game.deck = new Deck();
        Game.hand.clear();
        Game.dealerHand.clear();
        Game.sharedCards.clear();
        Game.burnPile.clear();
        game.dealCardsMenu();
        game.dealHand();
        game.preFlopAction();
        action.actions();
        game.flop();
        game.turn();
        game.river();
    }
}
