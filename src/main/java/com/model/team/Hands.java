package com.model.team;

import java.util.ArrayList;
import java.util.Collections;

public class Hands {


    //Royal Flush
    public ArrayList<Object> royalFlush(ArrayList<Card> hand) {
        ArrayList<Object> royal = new ArrayList<>();
        ArrayList<Card> royalTrue = null;
        Suit suit = null;

        int colors = 0;

        for (Card cards : hand) {
            if (Collections.frequency(hand, cards.getSuit()) >= 5) {
                colors = Collections.frequency(hand, cards.getSuit());
                suit = cards.getSuit();
            }
        }
        if (colors < 5) {
            return null;
        }
        royalTrue.add(new Card(suit, Rank.ACE));
        royalTrue.add(new Card(suit, Rank.KING));
        royalTrue.add(new Card(suit, Rank.QUEEN));
        royalTrue.add(new Card(suit, Rank.JACK));
        royalTrue.add(new Card(suit, Rank.TEN));
        royal.add(hand.containsAll(royalTrue));
        royal.add(CardRankings.ROYAL_FLUSH);
        royal.add(null);
        royal.add(null);
        return royal;
    }

    //Straight Flush
    public ArrayList<Object> straightFlush(ArrayList<Card> hand) {
        ArrayList<Object> straightFlush = new ArrayList<>();

        int colors = 0;

        for (Card cards : hand) {
            if (Collections.frequency(hand, cards.getSuit()) >= 5)
                colors = Collections.frequency(hand, cards.getSuit());
        }
        if (colors < 4) {
            return null;
        }

        int counter = 0;
        for(int i = 0; i < hand.size(); i++){
            for(int j = 0; j < hand.size(); j++){
                if (hand.get(i).getRank().ordinal() == hand.get(j).getRank().ordinal()+1) {
                    counter += 1;
                    break;
                }
                else {
                    hand.remove(j);
                }
            }
        }
        if (counter == 4 && hand.get(0).getRank() == Rank.TWO && hand.get(4).getRank() == Rank.ACE) {
            counter = 5;
            straightFlush.add(true);
            straightFlush.add(CardRankings.STRAIGHT_FLUSH);
            straightFlush.add(hand.get(hand.size()-1).getRank());
            straightFlush.add(null);
        }
        return straightFlush;
    }

    //Four of a kind
    public ArrayList<Object> fourOfAKind(ArrayList<Card> hand) {
        ArrayList<Object> four = new ArrayList<>();
        int counter = 0;
        Rank rank = null;
        for (Card cards : hand) {
            if (Collections.frequency(hand, cards.getRank()) >= 4) {
                rank = cards.getRank();
            }
        }
        if(rank != null){
            four.add(true);
            four.add(CardRankings.FOUR_OF_A_KIND);
            four.add(rank);
            four.add(null);
        }
        return four;
    }


    //Full House
    public ArrayList<Object> fullHouse(ArrayList<Card> hand) {
        ArrayList<Object> full = new ArrayList<>();
        Rank tripleRank = null;
        Rank doublesRank = null;
        int occurrences;
        for (int i = 0; i < hand.size(); i++) {
            occurrences = Collections.frequency(hand, hand.get(i).getRank());
            if (occurrences == 3) {
                tripleRank = hand.get(i).getRank();
            } else if (occurrences == 2) {
                doublesRank = hand.get(i).getRank();
            }
        }
        if (tripleRank != null && doublesRank != null) {
            full.add(true);
            full.add(CardRankings.FULL_HOUSE);
            full.add(tripleRank);
            full.add(doublesRank);
        } else {
            full = null;
        }

        return full;
    }


    //Flush
    public ArrayList<Object> flush(ArrayList<Card> hand) {
        ArrayList<Object> flush = new ArrayList<>();
        Suit suit = null;
        for (Card cards : hand) {
            if (Collections.frequency(hand, cards.getSuit()) >= 5) {
                flush = null;
                suit = cards.getSuit();
                flush.add(true);
                flush.add(CardRankings.FLUSH);
                flush.add(cards.getRank());
            }
        }

        return flush;
    }

    //Straight
    public boolean straight(ArrayList<Card> hand) {
        int counter = 0;
        for(int i = 0; i < hand.size(); i++){
            for(int j = 0; j < hand.size(); j++){
                if (hand.get(i).getRank().ordinal() == hand.get(j).getRank().ordinal()+1) {
                    counter += 1;
                    break;
                }
                else {
                    hand.remove(j);
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
            for (int j = 0; j < hand.size(); j++) {
                if (hand.get(i).getRank().equals(hand.get(j).getRank())) {
                    ranks.add(hand.get(i).getRank());
                    ranks.add(hand.get(j).getRank());
                }
            }
        }
        if (ranks.size() == 6) {
            ranks.remove(0);
            ranks.remove(0);
        }
        return ranks.size() == 4;
    }

    //One pair
    public boolean pair(ArrayList<Card> hand) {
        ArrayList<Rank> ranks = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            for (int j = 0; j < hand.size(); j++) {
                if (hand.get(i).getRank().equals(hand.get(j).getRank())) {
                    ranks.add(hand.get(i).getRank());
                    ranks.add(hand.get(j).getRank());
                }
            }
        }
        return ranks.size() == 2;
    }


    //High Card
    public boolean HighCard(ArrayList<Card> hand) {
        ArrayList<Rank> ranks = new ArrayList<>();
        return ranks.add(hand.get(4).getRank());
    }

    //Checking which is the highest.
}
