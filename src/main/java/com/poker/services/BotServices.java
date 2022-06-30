package com.poker.services;

import com.poker.game.Actions;
import com.poker.game.Game;
import com.poker.game.GameBoard;
import com.poker.model.Decision;
import com.poker.model.Card;
import com.poker.model.CardRankings;
import com.poker.model.Hands;

import java.util.ArrayList;
import java.util.Random;

/**
 * Initiates the bot action that will be used.
 * It will calculate what kind of actions that will be made depending on the cards.
 */
public class BotServices {
    BotActions bAction = new BotActions();

    /**
     * Returns the best actions that can be made with the first two cards.
     * @param userAction what kind decisions that is available.
     * @param score gets the score of the cards.
     * @return returns the actions that will be made.
     */
    public Decision botTwoCardAction(Decision userAction, double score) {
        Decision botAction = Decision.CALL;
        switch (userAction) {
            case CALL:
            case RAISE:
                if (score < 0) {
                    botAction = Decision.FOLD;
                }
                break;
            case CHECK:
                if (score < 5) {
                    botAction = Decision.CHECK;
                }
                break;
            case ALL_IN:
                if (score <= 8) {
                    botAction = Decision.FOLD;
                }
                break;
            default:
        }
        return botAction;
    }

    /**
     * Determine the strength score of the bot cards combining pocket and community cards
     * @param cards Takes in the arraylist of cards to determine the strength of the card.
     * @return returns the card strength.
     */
    public int check(ArrayList<Card> cards) {
        CardRankings handStength = (CardRankings) Hands.getHand(cards).get(1);
        return handStength.getValue();
    }

    /**
     * Determines bot action after each additional community card flipped(After flop, turn and river)
     * @param userAction The action that will be given.
     * @param score score based on the cards provided.
     * @return returns the best action
     */
    public Decision botMultiCardAction(Decision userAction, int score) {
        Decision botAction = Decision.CALL;
        if (userAction == Decision.CALL) {
            if (score < 20) {
                botAction = Decision.FOLD;
            }
        } else if (userAction == Decision.CHECK) {
            if (score < 20) {
                botAction = Decision.CHECK;
            }
        } else if (userAction == Decision.RAISE) {
            if (score <= 20) {
                botAction = Decision.FOLD;
            }
        } else if (userAction == Decision.ALL_IN) {
            if (score <= 30) {
                botAction = Decision.FOLD;
            }
        }
        return botAction;
    }

    /**
     * returns the bot's bet depending on the action and dealer bank.
     * @param botAction the action done.
     * @param dealerBank the dealers bank value.
     * @return returns the bank value after action.
     */
    public int getBotBet(Decision botAction, int dealerBank) {
        int bBet = 0;
        if (botAction == Decision.CHECK) {
            sleep(750);
            System.out.println("Dealer checks...");
        } else if (botAction == Decision.FOLD) {
            sleep(750);
            Game.bank += Game.pot;
            System.out.println("Dealer folds...");
            sleep(750);
            System.out.println("Dealer: \"Player wins the pot!\"");
            GameBoard gameboard = new GameBoard();
            gameboard.playerTurn();
        } else if (botAction == Decision.CALL) {
            if (dealerBank < Actions.bet) {
                bBet = dealerBank;
                Game.bank = Game.bank + Actions.bet - bBet;
                Game.pot = Game.pot - Actions.bet + (bBet * 2);
                Actions.bet = bBet;
            } else {
                if (bAction.getAction() == Decision.CHECK) {
                    Random random = new Random();
                    if (Game.dealerBank >= 100) {
                        bBet = (random.nextInt(9)) * 10 + 10;
                        sleep(750);
                        System.out.println(("Dealer raises " + bBet + "..."));
                    } else if (Game.dealerBank >= 10) {
                        bBet = 10;
                        sleep(750);
                        System.out.println(("Dealer raises " + bBet + "..."));
                    } else {
                        sleep(750);
                        System.out.println(("Dealer checks..."));
                    }
                } else {
                    bBet = Actions.bet;
                    System.out.println("Dealer calls...");
                }
            }
        }
        return bBet;
    }

    /**
     * Used for a delay between interfaces.
     * @param timer the amount of time needed.
     */
    public void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}