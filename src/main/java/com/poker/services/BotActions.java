package com.poker.services;

import com.poker.game.Actions;
import com.poker.model.Decision;

/**
 * Allows the dealer to receive actions and make a decision.
 */
public class BotActions {
    public static int botBet = 0;

    Decision tempAction;

    /**
     * Returns that actions that is given through actionDecision.
     * @return returns the best action.
     */
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

    /**
     * Gets the bet and returns boBet
     * @return botBet
     */
    public int getBet() {
        return botBet;
    }

    /**
     * Setter for the botBet.
     * @param bBet Takes in an input for botBet.
     */
    public void setBet(int bBet) {
        botBet = bBet;
    }
}