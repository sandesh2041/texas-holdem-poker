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

public class BotServices {
    BotActions bAction = new BotActions();

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

    public int check(ArrayList<Card> cards) {
        CardRankings handStength = (CardRankings) Hands.getHand(cards).get(1);
        return handStength.getValue();
    }

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

    public void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}