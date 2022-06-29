package com.model.team;

import java.util.ArrayList;
import java.util.Collections;

public class Hands {


    //Royal Flush  [boolean, Enum.CardRankings:double ,[A], [3]]
    //             [boolean, enum.CardRankings:double, [a], [5]]
    //
    //switch or if of which CardRanking it is to return an array.
    //Check if ArrayList.get(0) == true
    //Check the Enum.ordinal

    //Hands.getHand(ArrayList<Card> dealerHand);

    //todo: getting in the users inputs and then merging.
    //todo:
    public static ArrayList<Object> getHand(ArrayList<Card> hand) {
        ArrayList<Object> set = new ArrayList<>();
        set = royalFlush(hand);
        if (set != null) {
            return set;
        }
        set = straightFlush(hand);
        if (set != null) {
            return set;
        }
        set = fourOfAKind(hand);
        if (set != null) {
            return set;
        }
        set = fullHouse(hand);
        if (set != null) {
            return set;
        }

        set = flush(hand);
        if (set != null) {
            return set;
        }

        set = straight(hand);
        if (set != null) {
            return set;
        }
        set = threeOfAKind(hand);
        if (set != null) {
            return set;
        }
        set = twoPairs(hand);
        if (set != null) {
            return set;
        }
        set = pair(hand);
        if (set != null) {
            return set;
        }
        return HighCard(hand);
    }

    public static ArrayList<Card> compares(ArrayList<Card> hand1, ArrayList<Card> hand2, ArrayList<Card> hand3) {
        Rank user = hand1.get(1).getRank();
        Rank dealer = hand2.get(1).getRank();

        ArrayList<Object> first = getHand(hand1);
        ArrayList<Object> second = getHand(hand2);

        System.out.println(first);
        System.out.println(second);
        CardRankings rank1 = (CardRankings) first.get(1);
        CardRankings rank2 = (CardRankings) second.get(1);
        if(rank1.ordinal() < rank2.ordinal()){
            System.out.println("winner hand1");
            return hand1;
        }
        if(rank1.ordinal() > rank2.ordinal()){
            System.out.println("winner hand2 ");
            return hand2;
        }
        return null;
    }


    public static ArrayList<Object> royalFlush(ArrayList<Card> hand) {
        ArrayList<Object> royal = new ArrayList<>();
        ArrayList<Card> royalTrue = new ArrayList<>();
        Suit suit = null;
        int colors = 0;
        out:
        for (int i = 0; i < hand.size(); i++) {
            for (int j = 0; j < hand.size(); j++) {
                if (hand.get(i).getSuit() == hand.get(j).getSuit()) {
                    colors += 1;
                }
            if (colors >= 5) {
                suit = hand.get(i).getSuit();
                break out;
                }
            }
            colors = 0;
        }
        if(suit != null) {
            royalTrue.add(new Card(suit, Rank.ACE));
            royalTrue.add(new Card(suit, Rank.KING));
            royalTrue.add(new Card(suit, Rank.QUEEN));
            royalTrue.add(new Card(suit, Rank.JACK));
            royalTrue.add(new Card(suit, Rank.TEN));
        }
        if (royalTrue.containsAll(hand)) {
            royal.add(hand.containsAll(royalTrue));
            royal.add(CardRankings.ROYAL_FLUSH);
            royal.add(null);
            royal.add(null);
        }
        else{
            return null;
        }
        return royal;
    }

    //Straight Flush
    public static ArrayList<Object> straightFlush(ArrayList<Card> hand) {
        ArrayList<Object> straightFlush = new ArrayList<>();

        int colors = 0;

        for (Card cards : hand) {
            if (Collections.frequency(hand, cards.getSuit()) >= 5)
                colors = Collections.frequency(hand, cards.getSuit().ordinal());
        }
        if (colors < 4) {
            return null;
        }
        int counter = 0;
        Rank highest = null;
        out:
        for (int i = 0; i < hand.size(); i++) {
            for (int j = 0; j < hand.size(); j++) {
                if (hand.get(i).getRank().ordinal() - hand.get(j).getRank().ordinal() == 1) {
                    counter += 1;
                    highest = hand.get(i).getRank();
                    break;
                } else if (hand.get(i).getRank().ordinal() - hand.get(j).getRank().ordinal() == 0) {
                    break;
                } else {
                    break out;
                }
            }
        }
        if (counter == 4 && hand.get(0).getRank() == Rank.TWO && hand.get(hand.size() - 1).getRank() == Rank.ACE) {
            straightFlush.add(true);
            straightFlush.add(CardRankings.STRAIGHT_FLUSH);
            straightFlush.add(hand.get(hand.size() - 1).getRank());
            straightFlush.add(null);
        }
        if (counter == 5) {
            straightFlush.add(true);
            straightFlush.add(CardRankings.STRAIGHT_FLUSH);
            straightFlush.add(hand.get(hand.size() - 1).getRank());
            straightFlush.add(null);
        }
        return straightFlush;
    }

    //Four of a kind
    public static ArrayList<Object> fourOfAKind(ArrayList<Card> hand) {
        ArrayList<Object> four = new ArrayList<>();
        int counter = 0;
        Rank rank = null;
        for (Card cards : hand) {
            for (Card cards1 : hand) {
                if (cards.getRank() == cards1.getRank()) {
                    counter += 1;
                }
            }
            if (counter == 4) {
                rank = cards.getRank();
            }
            counter = 0;
        }
        if (rank != null) {
            four.add(true);
            four.add(CardRankings.FOUR_OF_A_KIND);
            four.add(rank);
            four.add(null);
        } else {
            four = null;
        }
        return four;
    }


    //Full House
    public static ArrayList<Object> fullHouse(ArrayList<Card> hand) {
        ArrayList<Object> full = new ArrayList<>();
        Rank tripleRank = null;
        Rank doublesRank = null;
        int counter = 0;

        for (Card cards : hand) {
            for (Card cards1 : hand) {
                if (cards.getRank() == cards1.getRank()) {
                    counter += 1;
                }
            }
            if (counter == 3) {
                tripleRank = cards.getRank();
            } else if (counter == 2) {
                doublesRank = cards.getRank();
            }
            counter = 0;

        }
        if (tripleRank != null && doublesRank != null && doublesRank != tripleRank) {
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
    public static ArrayList<Object> flush(ArrayList<Card> hand) {
        ArrayList<Object> flush = new ArrayList<>();
        Suit suit = null;
        Rank rank = null;
        int counter = 0;
        for (Card cards : hand) {
            for (Card cards1 : hand) {
                if (cards.getSuit() == cards1.getSuit()) {
                    counter += 1;
                }
            }
            if (counter >= 5) {
                suit = cards.getSuit();
                rank = cards.getRank();
            }
            counter = 0;
        }
        if (suit != null) {
            flush.add(true);
            flush.add(CardRankings.FLUSH);
            flush.add(rank);
            flush.add(null);
        } else {
            flush = null;
        }
        return flush;
    }

    //Straight
    public static ArrayList<Object> straight(ArrayList<Card> hand) {
        ArrayList<Object> straight = new ArrayList<>();
        int counter = 0;
        Rank highest = null;
        out:
        for (int i = 0; i < hand.size(); i++) {
            for (int j = 0; j < hand.size(); j++) {
                if (hand.get(i).getRank().ordinal() - hand.get(j).getRank().ordinal() == 1) {
                    counter += 1;
                    highest = hand.get(i).getRank();
                    break;
                } else if (hand.get(i).getRank().ordinal() - hand.get(j).getRank().ordinal() == 0) {
                    break;
                } else {
                    break out;
                }
            }
        }

        if (counter >= 4) {
            straight.add(true);
            straight.add(CardRankings.STRAIGHT);
            straight.add(highest);
            straight.add(null);
        } else {
            straight = null;
        }
        return straight;
    }

    //Three of a kind
    public static ArrayList<Object> threeOfAKind(ArrayList<Card> hand) {
        ArrayList<Object> three = new ArrayList<>();
        int counter = 0;
        Rank rank = null;
        for (Card cards : hand) {
            for (Card cards1 : hand) {
                if (cards.getRank() == cards1.getRank()) {
                    counter += 1;
                }
            }
            if (counter == 3) {
                rank = cards.getRank();
            }
            counter = 0;

        }
        if (rank != null) {
            three.add(true);
            three.add(CardRankings.THREE_OF_A_KIND);
            three.add(rank);
            three.add(null);
        } else {
            three = null;
        }
        return three;
    }

    //Two pair
    public static ArrayList<Object> twoPairs(ArrayList<Card> hand) {
        ArrayList<Object> two = new ArrayList<>();
        ArrayList<Rank> ranks = new ArrayList<>();
        int counter = 0;
        Rank rank = null;
        for (Card cards : hand) {
            for (Card cards1 : hand) {
                if (cards.getRank() == cards1.getRank()) {
                    counter += 1;
                }
            }
            if (counter >= 2) {
                ranks.add(cards.getRank());
            }
            counter = 0;
        }
        if (ranks.size() >= 4) {
            two.add(true);
            two.add(CardRankings.TWO_PAIRS);
            two.add(ranks.get(ranks.size() - 1));
            two.add(ranks.get(ranks.size() - 3));
        } else {
            two = null;
        }
        return two;
    }

    //One pair
    public static ArrayList<Object> pair(ArrayList<Card> hand) {
        ArrayList<Object> pair = new ArrayList<>();
        ArrayList<Rank> ranks = new ArrayList<>();
        Rank highestRank = null;
        int counter = 0;
        for (Card cards : hand) {
            for (Card cards1 : hand) {
                if (cards.getRank() == cards1.getRank()) {
                    counter += 1;
                } else {
                    highestRank = cards.getRank();
                }
            }
            if (counter == 2) {
                ranks.add(cards.getRank());
            }
                counter = 0;
        }
        if (ranks.size() > 0) {
            pair.add(true);
            pair.add(CardRankings.ONE_PAIR);
            pair.add(ranks.get(ranks.size() - 1));
            pair.add(highestRank);
        } else {
            pair = null;
        }

        return pair;
    }


    //High Card
    public static ArrayList<Object> HighCard(ArrayList<Card> hand) {
        ArrayList<Object> high = new ArrayList<>();
        high.add(true);
        high.add(CardRankings.HIGH_CARD);
        high.add(hand.get(hand.size() - 1).getRank());
        high.add(hand.get(hand.size() - 2).getRank());
        return high;
    }
}
