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

    public static ArrayList<Card> compare(ArrayList<Card> hand1, ArrayList<Card> hand2) {
        ArrayList<Object> first = getHand(hand1);
        ArrayList<Object> second = getHand(hand2);
        System.out.println(hand1);
        System.out.println(hand2);
        System.out.println(first);
        System.out.println(second);

        Enum f = (Enum) first.get(1);
        Enum s = (Enum) second.get(1);
        Rank fC = (Rank) first.get(2);
        Rank sC = (Rank) second.get(2);
        Rank flC = (Rank) first.get(3);
        Rank slC = (Rank) first.get(3);

        if ((f.ordinal() - s.ordinal()) == 1) {
            return hand1;
        }
        if (f.ordinal() - s.ordinal() == -1) {
            return hand2;
        }
        if (f.ordinal() - s.ordinal() == 0) {
            if (fC.ordinal() - sC.ordinal() == 1) {
                return hand1;
            }
            if (fC.ordinal() - sC.ordinal() == -1) {
                return hand2;
            }
            if (fC.ordinal() - sC.ordinal() == 0) {
                if (flC.ordinal() - slC.ordinal() == 1) {
                    return hand1;
                }
                if (flC.ordinal() - slC.ordinal() == -1) {
                    return hand2;
                } else {
                    System.out.println("Split Pot!");
                    return null;
                }
            }
        }
        return null;
    }


    public static ArrayList<Object> royalFlush(ArrayList<Card> hand) {
        ArrayList<Object> royal = new ArrayList<>();
        ArrayList<Card> royalTrue = new ArrayList<>();
        Suit suit = null;
        int colors = 0;
        //out:
        for (int i = 0; i < hand.size(); i++) {
            for (int j = 0; j < hand.size(); j++) {
                if (hand.get(i).getSuit() == hand.get(j).getSuit()) {
                    colors += 1;
                }
                if (colors >= 4) { //todo 5
                    suit = hand.get(i).getSuit();
                    // break out;
                }
            }
            colors = 0;
        }
        if (colors < 5) {
            return null;
        }

        royalTrue.add(new Card(suit, Rank.ACE));
        royalTrue.add(new Card(suit, Rank.KING));
        royalTrue.add(new Card(suit, Rank.QUEEN));
        royalTrue.add(new Card(suit, Rank.JACK));
        royalTrue.add(new Card(suit, Rank.TEN));

        if (hand.containsAll(royalTrue)) {
            royal.add(hand.containsAll(royalTrue));
            royal.add(CardRankings.ROYAL_FLUSH);
            royal.add(null);
            royal.add(null);
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
        int occurrences;
        for (int i = 0; i < hand.size(); i++) {
            occurrences = Collections.frequency(hand, hand.get(i).getRank().ordinal());
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
    public static ArrayList<Object> flush(ArrayList<Card> hand) {
        ArrayList<Object> flush = new ArrayList<>();
        Suit suit = null;
        for (Card cards : hand) {
            if (Collections.frequency(hand, cards.getSuit()) >= 4) {
                flush = new ArrayList<>();
                suit = cards.getSuit();
                flush.add(true);
                flush.add(CardRankings.FLUSH);
                flush.add(cards.getRank());
                break;
            } else {
                flush = null;

            }
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
        Rank highest = null;
        for (int i = 0; i < hand.size(); i++) {
            if (Collections.frequency(hand, hand.get(i).getRank()) == 3) {
                highest = hand.get(i).getRank();
            }
        }
        if (highest != null) {
            three.add(true);
            three.add(CardRankings.THREE_OF_A_KIND);
            three.add(highest);
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
        for (int i = 0; i < hand.size(); i++) {
            if (Collections.frequency(hand, hand.get(i).getRank()) == 2) {
                ranks.add(hand.get(i).getRank());
            }
        }
        if (ranks.size() < 4) {
            return null;
        }
        while (ranks.size() > 4) {
            ranks.remove(0);
        }
        two.add(ranks.size() == 4);
        two.add(CardRankings.TWO_PAIRS);
        two.add(ranks.get(3));
        two.add(ranks.get(0));
        return two;
    }

    //One pair
    public static ArrayList<Object> pair(ArrayList<Card> hand) {
        ArrayList<Object> pair = new ArrayList<>();
        ArrayList<Rank> ranks = new ArrayList<>();
        Rank highestRank = null;

        for (Card cards : hand) {
            if (Collections.frequency(hand, (cards.getRank().ordinal())) == 2) {
                ranks.add(cards.getRank());
            } else {
                highestRank = cards.getRank();
            }
        }
        if (ranks.size() == 0) {
            return null;
        }
        pair.add(true);
        pair.add(CardRankings.ONE_PAIR);
        pair.add(ranks.get(ranks.size() - 1));
        pair.add(highestRank);

        return pair;
    }


    //High Card
    public static ArrayList<Object> HighCard(ArrayList<Card> hand) {
        ArrayList<Object> high = new ArrayList<>();
        high.add(true);
        high.add(CardRankings.HIGH_CARD);
        high.add(hand.get(hand.size() - 1).getRank());
        high.add(null);
        return high;
    }
}
