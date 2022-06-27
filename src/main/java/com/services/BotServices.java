package com.services;

import com.model.enums.Actions;
import com.model.team.Card;
import com.model.team.CardRankings;
import com.model.team.Hands;

import java.util.ArrayList;

public class BotServices {

    Hands hand = new Hands();

    //Determines bot action for initial two pocket cards
    public Actions botTwoCardAction (Actions userAction, double score) {
        Actions botAction = Actions.CALL;
        switch (userAction) {
            case CALL:
            case RAISE:
                if(score <= 10){
                    botAction = Actions.FOLD;
                }
                break;
            case CHECK:
                if(score <= 8){
                    botAction = Actions.CHECK;
                }
                break;
            case ALL_IN:
                if(score <= 13){
                    botAction = Actions.FOLD;
                }
                break;
            default:
        }
        return botAction;
    }

    //Determine the strength score of the bot cards combining pocket and community cards
    public int check(ArrayList<Card> cards){
        int score = 10;
        if(hand.royalFlush(cards)) return CardRankings.ROYAL_FLUSH.getValue();
        if(hand.straightFlush(cards)) return CardRankings.STRAIGHT_FLUSH.getValue();
        if(hand.fourOfAKind(cards)) return CardRankings.FOUR_OF_A_KIND.getValue();
        if(hand.fullHouse(cards)) return CardRankings.FULL_HOUSE.getValue();
        if(hand.flush(cards)) return CardRankings.FLUSH.getValue();
        if(hand.straight(cards)) return CardRankings.STRAIGHT.getValue();
        if(hand.three(cards)) return CardRankings.THREE_OF_A_KIND.getValue();
        if(hand.twoPairs(cards)) return CardRankings.TWO_PAIRS.getValue();
        if(hand.pair(cards)) return CardRankings.ONE_PAIR.getValue();
        return score;
    }

    //Determines bot action after each additional community card flipped(After flop, turn and river)
    public Actions botMultiCardAction (Actions userAction, int score) {
        Actions botAction = Actions.CALL;
        if(userAction == Actions.CALL) {
            if(score < 20){
                botAction = Actions.FOLD;
            }
        } else if(userAction == Actions.CHECK) {
            if(score < 20){
                botAction = Actions.CHECK;
            }
        } else if(userAction == Actions.RAISE) {
            if(score <= 20){
                botAction = Actions.FOLD;
            }
        } else if(userAction == Actions.ALL_IN) {
            if(score <= 30){
                botAction = Actions.FOLD;
            }
        }
        return botAction;

    }

    //Determines how much bot will bet (Cannot call this method if user checks or fold)
    public int botBet(Actions userAction, int userBet, int raisedAmount, int botBank) {
        int bet = 0;
        if(botBank <= userBet || botBank <= raisedAmount){
            bet = botBank;
        } else {
            if (userAction == Actions.CALL) {
                bet = userBet;
            } else if (userAction == Actions.RAISE) {
                bet = raisedAmount;
            } else if (userAction == Actions.ALL_IN) {
                bet = raisedAmount;
            }
        }
        return bet;
    }


}

