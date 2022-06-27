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
    public boolean straightFlush(ArrayList<Card> hand) {
        int colors = Collections.frequency(hand, hand.get(0).getSuit());

        if (colors != 5) {
            return false;
        }

        int counter = 0;
        for (Card card : hand) {
            for (Card next : hand) {
                if (card.getRank().ordinal() + 1 == next.getRank().ordinal()) {
                    counter += 1;
                }
            }
        }
        if (counter == 4 && hand.get(0).equals(Rank.TWO) && hand.get(4).equals(Rank.ACE)) ;
        {
            counter = 5;
        }
        return counter == 5;
    }

    //Four of a kind
    public boolean fourOfAKind(ArrayList<Card> hand) {
        Rank rank = null;
        boolean output = false;
        int rankCounter = Collections.frequency(hand, hand.get(0).getRank());
        if (rankCounter != 4) {
            rankCounter = Collections.frequency(hand, hand.get(1).getRank());
            if (rankCounter == 4) {
                output = true;
            }
        }
        return output;
    }


    //Full House
    public boolean fullHouse(ArrayList<Card> hand) {
        Rank tripleRank = null;
        Rank doublesRank = null;
        boolean isFullHouse = false;
        int occurrences = 0;
        for (int i = 0; i < hand.size(); i++) {
            occurrences = Collections.frequency(hand, hand.get(i).getRank());
            if (occurrences == 3) {
                tripleRank = hand.get(i).getRank();
            } else if (occurrences == 2) {
                doublesRank = hand.get(i).getRank();
            }
        }
        if (tripleRank != null && doublesRank != null) {
            isFullHouse = true;
        } else {
            isFullHouse = false;
        }
        return isFullHouse;
    }


    //Flush
    public boolean flush(ArrayList<Card> hand) {
        int occurrence = Collections.frequency(hand, hand.get(0).getSuit());
        return occurrence == 5;
    }

    //Straight
    public boolean straight(ArrayList<Card> hand) {
        int counter = 0;
        for (Card card : hand) {
            for (Card next : hand) {
                if (card.getRank().ordinal() + 1 == next.getRank().ordinal()) {
                    counter += 1;
                }
            }
        }
        return counter == 5;
    }

    //Three of a kind
    public boolean three(ArrayList<Card> hand) {
        Rank three = null;
        for (int i = 0; i < hand.size(); i++) {
            if (Collections.frequency(hand, hand.get(i).getRank()) == 3) {
                three = hand.get(i).getRank();
            }
        }

        return three != null;
    }

    //Two pair
    public boolean twoPairs(ArrayList<Card> hand) {
        ArrayList<Rank> ranks = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            for(int j = 0; j < hand.size(); j++){
                if (hand.get(i).getRank().equals(hand.get(j).getRank())){
                   ranks.add(hand.get(i).getRank());
                   ranks.add(hand.get(j).getRank());
                }
            }
        }
        if(ranks.size()==6){
            ranks.remove(0);
            ranks.remove(0);
        }
        return ranks.size() == 4;
    }

    //One pair
    public boolean pair(ArrayList<Card> hand) {
        ArrayList<Rank> ranks = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            for(int j = 0; j < hand.size(); j++){
                if (hand.get(i).getRank().equals(hand.get(j).getRank())){
                    ranks.add(hand.get(i).getRank());
                    ranks.add(hand.get(j).getRank());
                }
            }
        }
        return ranks.size() == 2;
    }


    //High Card
    public boolean HighCard(ArrayList<Card> hand){
        ArrayList<Rank> ranks = new ArrayList<>();
        return ranks.add(hand.get(4).getRank());
    }

    //compareTo
}
