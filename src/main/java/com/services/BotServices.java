package com.services;

import com.samgame.Actions;
import com.samgame.BotActions;
import com.model.enums.Decision;
import com.model.team.Card;
import com.model.team.CardRankings;
import com.model.team.Hands;

import java.util.ArrayList;

public class BotServices {

    BotActions bAction = new BotActions();
    //Determines bot action for initial two pocket cards
    public Decision botTwoCardAction (Decision userAction, double score) {
        Decision botAction = Decision.CALL;
        switch (userAction) {
            case CALL:
            case RAISE:
                if(score < 0){
                    botAction = Decision.FOLD;
                }
                break;
            case CHECK:
                if(score < 0){ //5
                    botAction = Decision.CHECK;
                }
                break;
            case ALL_IN:
                if(score <= 8){
                    botAction = Decision.FOLD;
                }
                break;
            default:
        }
        return botAction;
    }

    //Determine the strength score of the bot cards combining pocket and community cards
    public int check(ArrayList<Card> cards){
        CardRankings handStength = (CardRankings) Hands.getHand(cards).get(1);
        return handStength.getValue();
    }

    //Determines bot action after each additional community card flipped(After flop, turn and river)
    public Decision botMultiCardAction (Decision userAction, int score) {
        Decision botAction = Decision.CALL;
        if(userAction == Decision.CALL) {
            if(score < 0){ //20
                botAction = Decision.FOLD;
            }
        } else if(userAction == Decision.CHECK) {
            if(score < 20){
                botAction = Decision.CHECK;
            }
        } else if(userAction == Decision.RAISE) {
            if(score <= 20){
                botAction = Decision.FOLD;
            }
        } else if(userAction == Decision.ALL_IN) {
            if(score <= 30){
                botAction = Decision.FOLD;
            }
        }
        return botAction;

    }

    public int getBotBet(Decision botAction, int dealerBank) {
        int bBet = 0;
        if (botAction == Decision.CHECK){
            System.out.println("CHECKED!!!!");
        } else if (botAction == Decision.FOLD) {
            System.out.println("FOLD!!!!!!!!");
        } else if(botAction == Decision.CALL){
            if (dealerBank < bAction.getBet()) {
                bBet = dealerBank;
            } else {
                if(bAction.getAction() == Decision.CHECK){
                    bBet = 10;
                } else {
                    bBet = Actions.bet;
                }
            }
            System.out.println(("CALL!!!!!!!"));
        }
        return bBet;
    }
}