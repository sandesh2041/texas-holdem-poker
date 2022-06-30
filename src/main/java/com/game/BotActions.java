package com.game;

import com.model.enums.Decision;



public class BotActions {

//    private static final ResourceBundle bundle = ResourceBundle.getBundle("strings");

    public static int botBet = 0;
    private final int time = 1; //750
    Decision tempAction;

    public int getBet() {
        return botBet;
    }

    public void setBet(int bBet) {
        botBet = bBet;
    }

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

//    public Decision getSecondAction() {
//        int caseNum = Actions.actionDecision;
//        switch (caseNum) {
//            case 1:
//                tempAction = Decision.CALL;
//                break;
//            case 2:
//                tempAction = Decision.FOLD;
//                break;
//        }
//        return tempAction;
//    }

}