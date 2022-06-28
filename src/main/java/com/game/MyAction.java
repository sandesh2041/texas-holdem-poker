package com.game;

import com.model.enums.Actions;

import java.util.Scanner;

public class MyAction {

    private int caseNum = 0;
    private int wager = 0;
    Actions tempAction;

    public int getWager() {
        return wager;
    }

    public void setWager(int wager) {
        this.wager = wager;
    }

    public Actions getAction(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What action do you wish to take? Select \"1\" for check, \"2\" for call, or \"3\" for fold. ");
        caseNum =  scanner.nextInt();
        switch(caseNum){
            case 1:
                tempAction = Actions.CHECK;
                break;
            case 2:
                tempAction = Actions.CALL;
                setWager(10); //$10 for call always
                break;
            case 3:
                tempAction = Actions.FOLD;
                break;
            default:
                //implement validation of input
        }
        return tempAction;
    }
}
