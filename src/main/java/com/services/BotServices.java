package com.services;

import com.model.enums.Actions;

public class BotServices {

    public Actions botTwoCardAction (Actions userAction, double score) {
        Actions botAction = Actions.CALL;
        if(userAction == Actions.CALL) {
            if(score <= 10){
                botAction = Actions.FOLD;
            }
        } else if(userAction == Actions.CHECK) {
            if(score <= 8){
                botAction = Actions.CHECK;
            }
        } else if(userAction == Actions.RAISE) {
            if(score <= 10){
                botAction = Actions.FOLD;
            }
        } else if(userAction == Actions.ALL_IN) {
            if(score <= 13){
                botAction = Actions.FOLD;
            }
        }
        return botAction;
    }

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

    public int botBet(Actions botAction, int userBet) {
        int bet = 10;
        if(botAction == Actions.CALL) {
            bet = userBet;
        }
        return bet;
    }
}
