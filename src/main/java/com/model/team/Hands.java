package com.model.team;

import java.util.ArrayList;
import java.util.Collections;

public class Hands {

    ArrayList<Card> hands;


    public Hands(ArrayList<Card> hands) {
        this.hands = hands;
    }

    //Royal Flush
    public boolean royalFlush(ArrayList<Card> hand) {
        ArrayList<Card> royal = null;
        Suit suit = null;
        int suitCounter = 0;
        for (int i = 0; i < hand.size(); i++) {
            int counter = 0;
            for (int j = 0; j < hand.size(); j++) {
                if (hand.get(i).getSuit() == hand.get(j).getSuit()) {
                    counter += 1;
                }
            }
            if (counter > suitCounter) {
                suit = hand.get(i).getSuit();
                suitCounter = counter;
            }
        }

        royal.add(new Card(suit, Rank.ACE));
        royal.add(new Card(suit, Rank.KING));
        royal.add(new Card(suit, Rank.QUEEN));
        royal.add(new Card(suit, Rank.JACK));
        royal.add(new Card(suit, Rank.TEN));
        return hands.containsAll(royal);
    }

    //Straight Flush
    public boolean Flush(ArrayList<Card> hand) {

        Suit suit = null;
        int suitCounter = 0;

        for (int i = 0; i < hand.size(); i++) {
            int counter = 0;

            for (int j = 0; j < hand.size(); i++) {
                if (hand.get(i).getSuit() == hand.get(j).getSuit()) {
                    counter += 1;
                }
            }
            if (counter > suitCounter) {
                suit = hand.get(i).getSuit();
                suitCounter = counter;
            }
        }
        return suitCounter >= 5;
    }

    //Four of a kind
    public boolean FourOfAKind(ArrayList<Card> hand) {
        Rank rank = null;
        int rankCounter = 0;

        for (int i = 0; i < hand.size(); i++) {
            int counter = 0;

            for (int j = 0; j < hand.size(); i++) {
                if (hand.get(i).getRank() == hand.get(j).getRank()) {
                    counter += 1;
                }
            }
            if (counter > rankCounter) {
                rank = hand.get(i).getRank();
                rankCounter = counter;
            }
        }
        return rankCounter >= 4;
    }


    //Full House
    public boolean FullHouse(ArrayList<Card> hand) {
        Rank tripleRank = null;
        Rank doublesRank = null;
        boolean isFullHouse = false;
        int occurrences = 0;
        for (int i = 0; i < hand.size(); i++) {
            occurrences = Collections.frequency(hand, hand.get(i).getRank());
            if(occurrences == 3){
                tripleRank = hand.get(i).getRank();
            }
            else if(occurrences == 2){
                doublesRank = hand.get(i).getRank();
            }
        }
        if (tripleRank != null && doublesRank != null) {
            isFullHouse = true;
        }
        else{
            isFullHouse = false;
        }
        return isFullHouse;
    }


    //Flush

    //Straight

    //Three of a kind

    //Two pair

    //One pair

    //High Card

    //compareTo
}
