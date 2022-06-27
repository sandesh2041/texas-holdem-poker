package com.services;

import com.model.enums.Actions;

public class BotServices {

    //Determines bot action for initial two pocket cards
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

    //Determines how much bot will bet
//    public int botBet(Actions userAction, int betMatch, int botBank) {
//        int bet;
//        if(userAction == Actions.CALL) {
//            bet = betMatch;
//        } else if (userAction == Actions.RAISE) {
//
//        } else if (userAction == Actions.ALL_IN) {
//
//        }
//        return bet;
//    }
}
