package com.model.team;

import com.game.Game;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Hands {

    public static ArrayList<Object> dealerSharedHand = new ArrayList<>();
    public static ArrayList<Object> playerSharedHand = new ArrayList<>();


    //Royal Flush  [boolean, Enum.CardRankings:double ,[A], [3]]
    //             [boolean, enum.CardRankings:double, [a], [5]]
    //
    //switch or if of which CardRanking it is to return an array.
    //Check if ArrayList.get(0) == true
    //Check the Enum.ordinal

    //Hands.getHand(ArrayList<Card> dealerHand);

    //todo: getting in the users inputs and then merging.
    //todo:
    private static final Comparator<Card> comparator = Comparator
            .comparing(Card::getRank);

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

//    public static String printWinner() {
//        String dealerResult = "";
//        String userResult = "";
//        for (int i = 1; i < dealerHand.size(); i++) {
//            dealerResult += dealerHand.get(i) + " ";
//        }
//        for (int i = 1; i < userHand.size(); i++) {
//            userResult += userHand.get(i) + " ";
//        }
//        String message = "Dealer:\"The dealer hand is " + Game.dealerHand + " and has a " + dealerResult + "\"\n"
//                + "Dealer:\"The player hand is " + Game.hand + " and has a " + userResult;
////        System.out.println("The dealer hand is " + dealerResult);
////        System.out.println("The user hand is " + userResult);
//        return message;
//    }

    public static ArrayList<Card> compares(ArrayList<Card> hand1, ArrayList<Card> hand2, ArrayList<Card> shared) {
        //hands [0,1]
        Rank dealer = hand1.get(1).getRank();
        Rank user = hand2.get(1).getRank();

        //hands [0,1]
        Rank dealerSecond = hand1.get(0).getRank();
        Rank userSecond = hand2.get(0).getRank();

        hand1.addAll(shared);
        hand2.addAll(shared);
        hand1.sort(comparator);
        hand2.sort(comparator);

        ArrayList<Object> first = getHand(hand1);
        ArrayList<Object> second = getHand(hand2);
        dealerSharedHand= first;
        playerSharedHand = second;


//        System.out.println("Dealer" + first);
//        System.out.println("User" + second);
        CardRankings rank1 = (CardRankings) first.get(1);
        CardRankings rank2 = (CardRankings) second.get(1);

        Rank firstCard = (Rank) first.get(2);
        Rank secondCard = (Rank) second.get(2);

        Rank hand1LastCard = (Rank) first.get(2);
        Rank hand2LastCard = (Rank) second.get(2);

        if (rank1.ordinal() < rank2.ordinal()) {
            System.out.println("Dealer: \"Dealer wins!\"");
            return hand1;
        }
        if (rank1.ordinal() > rank2.ordinal()) {
            System.out.println("Dealer: \"Player wins!\"");
            return hand2;
        }
        if (firstCard.ordinal() > secondCard.ordinal()) {
            System.out.println("Dealer: \"Dealer wins!\"");
            return hand1;
        }
        if (firstCard.ordinal() < secondCard.ordinal()) {
            System.out.println("Dealer: \"Player wins!\"");
            ;
            return hand2;
        }
        if (hand1LastCard.ordinal() > hand2LastCard.ordinal()) {
            System.out.println("Dealer: \"Dealer wins!\"");
            return hand1;
        }
        if (hand1LastCard.ordinal() < hand2LastCard.ordinal()) {
            System.out.println("Dealer: \"Player wins!\"");
            ;
            return hand2;
        }
        if (dealer.ordinal() > user.ordinal()) {
            System.out.println("Dealer: \"Dealer wins!\"");
            return hand1;
        }
        if (dealer.ordinal() < user.ordinal()) {
            System.out.println("Dealer: \"Player wins!\"");
            ;
            return hand2;
        }
        if (dealerSecond.ordinal() < userSecond.ordinal()) {
            System.out.println("Dealer: \"Dealer wins!\"");
            return hand1;
        }
        if (dealerSecond.ordinal() > userSecond.ordinal()) {
            System.out.println("Dealer: \"Player wins!\"");
            ;
            return hand2;
        }
        System.out.println("Dealer: \"Even hand, push!\"");
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
        if (suit != null) {
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
        } else {
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
                if (tripleRank == null) {
                    tripleRank = cards.getRank();
                } else {
                    doublesRank = tripleRank;
                    tripleRank = cards.getRank();
                }
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
        Rank last = null;
        for (int i = 0; i < hand.size(); i++) {
            for (int j = 0; j < hand.size(); j++) {
                if (hand.get(i).getRank().ordinal() - hand.get(j).getRank().ordinal() == -1) {
                    counter += 1;
                    highest = hand.get(i).getRank();
                }
                if (highest != null) {
                    if (highest.ordinal() - hand.get(j).getRank().ordinal() < -1) {
                        counter = 0;
                    }
                }
            }

        }

        if (counter >= 5) {
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
