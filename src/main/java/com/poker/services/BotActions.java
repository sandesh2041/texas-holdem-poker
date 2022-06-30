package com.poker.services;

import com.poker.game.Actions;
import com.poker.model.Decision;

public class BotActions {
    public static int botBet = 0;

    Decision tempAction;

    public Decision getAction() {
        int caseNum = Actions.actionDecision;
        switch (caseNum) {
            case 1:
                tempAction = Decision.CHECK;
                break;
            case 2:
                tempAction = Decision.CALL;
                break;
            case 3:
                tempAction = Decision.FOLD;
                break;
        }
        return tempAction;
    }

    public int getBet() {
        return botBet;
    }

    public void setBet(int bBet) {
        botBet = bBet;
    }
}