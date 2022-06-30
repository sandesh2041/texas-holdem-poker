package com.poker.model;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Implements entry point for the ArrayList of Cards.
 * Will take compare to call getHands then compare the output of the new
 * ArrayList of Objects.
 */
public class Hands {

    /**
     *
     * compares the card rankings and returns it in order of Rank.
     */
    private static final Comparator<Card> comparator = Comparator
            .comparing(Card::getRank);

    public static String printWinner;
    /**
     * iterates through the Rankings (royal,straight,ect.) to see if one of them is correct.
     *
     * @param hand Takes in the dealer/user + table hand as a single ArrayList.
     * @return the ranking of the hand.
     */
    public static ArrayList<Object> getHand(ArrayList<Card> hand) {
        ArrayList<Object> set;
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
        return highCard(hand);
    }

    /**
     * Compares both hands and iterates through the getHand to get both rankings then compares those rankings
     * with the index's from 1-4 inclusive and at which point if they do not have a winning hand they'd
     *
     * @param hand1  Takes in the dealers cards.
     * @param hand2  takes in the users cards.
     * @param shared Takes in the tables cards.
     * @return Returns a String of who won out of both hands.
     */
    public static String compares(ArrayList<Card> hand1, ArrayList<Card> hand2, ArrayList<Card> shared) {
        Rank dealer = hand1.get(1).getRank();
        Rank user = hand2.get(1).getRank();
        Rank dealerSecond = hand1.get(0).getRank();
        Rank userSecond = hand2.get(0).getRank();

        hand1.addAll(shared);
        hand2.addAll(shared);
        hand1.sort(comparator);
        hand2.sort(comparator);

        ArrayList<Object> first = getHand(hand1);
        ArrayList<Object> second = getHand(hand2);

        String dealerResult = "";
        String userResult = "";
        for (int i = 1; i < first.size(); i++) {
            dealerResult += first.get(i) + " ";
        }
        for (int i = 1; i < second.size(); i++) {
            userResult += second.get(i) + " ";
        }
        printWinner = "Dealer: \"The dealer has " + dealerResult.toLowerCase() + "and the player has " + userResult.toLowerCase() + "\"\n";

        CardRankings rank1 = (CardRankings) first.get(1);
        CardRankings rank2 = (CardRankings) second.get(1);
        Rank firstCard = (Rank) first.get(2);
        Rank secondCard = (Rank) second.get(2);
        Rank hand1LastCard = (Rank) first.get(2);
        Rank hand2LastCard = (Rank) second.get(2);

        if (rank1.ordinal() < rank2.ordinal()) {
            return "winner: Dealer";
        }
        if (rank1.ordinal() > rank2.ordinal()) {
            return "winner: User";
        }
        if (firstCard.ordinal() > secondCard.ordinal()) {
            return "winner: Dealer";
        }
        if (firstCard.ordinal() < secondCard.ordinal()) {
            return "winner: User";
        }
        if (hand1LastCard.ordinal() > hand2LastCard.ordinal()) {
            return "winner: Dealer";
        }
        if (hand1LastCard.ordinal() < hand2LastCard.ordinal()) {
            return "winner: User";
        }
        if (dealer.ordinal() > user.ordinal()) {
            return "winner: Dealer";
        }
        if (dealer.ordinal() < user.ordinal()) {
            return "winner: User";
        }
        if (dealerSecond.ordinal() < userSecond.ordinal()) {
            return "winner: Dealer";
        }
        if (dealerSecond.ordinal() > userSecond.ordinal()) {
            return "winner: User";
        }
        return "push";

    }

    /**
     * returns if CardRanking.ROYAL_FLUSH is true else it returns null.
     *
     * @param hand takes in an ArrayList of Cards
     * @return returns [boolean, CardRanking]
     */
    public static ArrayList<Object> royalFlush(ArrayList<Card> hand) {
        ArrayList<Object> straightFlush = new ArrayList<>();
        ArrayList<Card> straight = new ArrayList<>();
        ArrayList<Object> fin = new ArrayList<>();
        Suit val = null;
        Card highest = null;
        for (Card card : hand) {
            if (highest != null) {
                if (card.getRank().ordinal() - highest.getRank().ordinal() == 1 && card.getSuit() == highest.getSuit()) {
                    if (straight.size() == 0) {
                        straight.add(highest);
                    }
                    straight.add(card);
                } else {
                    if (straight.size() >= 5 && straight.get(straight.size() - 1).getRank() == Rank.ACE && straight.get(straight.size() - 5).getRank() == Rank.TEN) {
                        val = highest.getSuit();
                        fin = new ArrayList<>();
                        fin.add(true);
                        fin.add(CardRankings.ROYAL_FLUSH);
                        fin.add(straight.get(straight.size() - 1).getRank());
                        fin.add(null);
                        return fin;
                    }
                    straight.clear();
                }
            }
            highest = card;
        }
        if (straight.size() >= 5 && straight.get(straight.size() - 1).getRank() == Rank.ACE && straight.get(straight.size() - 5).getRank() == Rank.TEN) {
            straightFlush.add(true);
            straightFlush.add(CardRankings.ROYAL_FLUSH);
            straightFlush.add(straight.get(straight.size() - 1).getRank());
            straightFlush.add(null);
        } else {
            return null;
        }
        return straightFlush;
    }

    /**
     * returns if CardRanking.STRAIGHT_FLUSH is true else it returns null.
     *
     * @param hand takes in an ArrayList of Cards
     * @return returns [boolean, CardRanking]
     */
    public static ArrayList<Object> straightFlush(ArrayList<Card> hand) {
        ArrayList<Object> straightFlush = new ArrayList<>();
        ArrayList<Card> straight = new ArrayList<>();
        ArrayList<Object> fin = new ArrayList<>();
        Suit val = null;
        Card highest = null;
        for (Card card : hand) {
            if (highest != null) {
                if (card.getRank().ordinal() - highest.getRank().ordinal() == 1) {
                    if (straight.size() == 0) {
                        straight.add(highest);
                    }
                    straight.add(card);
                } else {
                    if (straight.size() >= 4 &&
                            hand.get(0).getRank() == Rank.TWO &&
                            hand.get(hand.size() - 1).getRank() == Rank.ACE &&
                            hand.get(hand.size() - 1).getSuit() == hand.get(hand.size() - 2).getSuit() &&
                            hand.get(hand.size() - 2).getSuit() == hand.get(hand.size() - 3).getSuit() &&
                            hand.get(hand.size() - 3).getSuit() == hand.get(hand.size() - 4).getSuit()
                    ) {
                        fin.add(true);
                        fin.add(CardRankings.STRAIGHT_FLUSH);
                        fin.add(straight.get(straight.size() - 1).getRank());
                        fin.add(null);
                        return fin;
                    }
                    if (highest.getRank().ordinal() != card.getRank().ordinal()) {
                        straight.clear();
                    }
                }
            }
            highest = card;
        }
        if (straight.size() >= 4 &&
                hand.get(0).getRank() == Rank.TWO &&
                hand.get(hand.size() - 1).getRank() == Rank.ACE &&
                hand.get(hand.size() - 1).getSuit() == hand.get(hand.size() - 2).getSuit() &&
                hand.get(hand.size() - 2).getSuit() == hand.get(hand.size() - 3).getSuit()) {
            straightFlush.add(true);
            straightFlush.add(CardRankings.STRAIGHT_FLUSH);
            straightFlush.add(hand.get(hand.size() - 1).getRank());
            straightFlush.add(null);
        } else if (straight.size() >= 5) {
            straightFlush.add(true);
            straightFlush.add(CardRankings.STRAIGHT_FLUSH);
            straightFlush.add(straight.get(straight.size() - 1).getRank());
            straightFlush.add(null);
        } else {
            return null;
        }
        return straightFlush;
    }

    /**
     * returns if CardRanking.FOUR_OF_A_KIND is true else it returns null.
     * @param hand takes in an ArrayList of Cards
     * @return returns [boolean, CardRanking]
     */
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

    /**
     * returns if CardRanking.FULL_HOUSE is true else it returns null.
     * @param hand takes in an ArrayList of Cards
     * @return returns [boolean, CardRanking]
     */
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
            if (counter == 3 && cards.getRank() != tripleRank) {
                if (tripleRank != null) {
                    doublesRank = tripleRank;
                }
                tripleRank = cards.getRank();
            }
            if (counter == 2) {
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

    /**
     * returns if CardRanking.FLUSH is true else it returns null.
     * @param hand takes in an ArrayList of Cards
     * @return returns [boolean, CardRanking]
     */
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

    /**
     * returns if CardRanking.STRAIGHT is true else it returns null.
     * @param hand takes in an ArrayList of Cards
     * @return returns [boolean, CardRanking]
     */
    public static ArrayList<Object> straight(ArrayList<Card> hand) {
        ArrayList<Card> straight = new ArrayList<>();
        ArrayList<Object> fin = new ArrayList<>();
        Card highest = null;
        for (Card card : hand) {
            if (highest != null) {
                if (card.getRank().ordinal() - highest.getRank().ordinal() == 1) {
                    if (straight.size() == 0) {
                        straight.add(highest);
                    }
                    straight.add(card);
                } else {
                    if (straight.size() == 5) {
                        fin.add(true);
                        fin.add(CardRankings.STRAIGHT);
                        fin.add(straight.get(straight.size() - 1).getRank());
                        fin.add(null);
                        return fin;
                    }
                    int x = hand.size();
                    if (straight.size() >= 4 && hand.get(x - 1).getRank() == Rank.ACE && straight.get(0).getRank() == Rank.TWO) {
                        fin.add(true);
                        fin.add(CardRankings.STRAIGHT);
                        fin.add(straight.get(straight.size() - 1).getRank());
                        fin.add(null);
                        return fin;
                    }
                    straight.clear();
                }
            }
            highest = card;
        }
        if (straight.size() == 5) {
            fin.add(true);
            fin.add(CardRankings.STRAIGHT);
            fin.add(straight.get(straight.size() - 1).getRank());
            fin.add(null);
            return fin;
        }
        return null;
    }

    /**
     * returns if CardRanking.THREE_OF_A_KIND is true else it returns null.
     * @param hand takes in an ArrayList of Cards
     * @return returns [boolean, CardRanking]
     */
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

    /**
     * returns if CardRanking.TWO_PAIRS is true else it returns null.
     * @param hand takes in an ArrayList of Cards
     * @return returns [boolean, CardRanking]
     */
    public static ArrayList<Object> twoPairs(ArrayList<Card> hand) {
        ArrayList<Object> two = new ArrayList<>();
        ArrayList<Rank> ranks = new ArrayList<>();
        int counter = 0;
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

    /**
     * returns if CardRanking.ONE_PAIR is true else it returns null.
     * @param hand takes in an ArrayList of Cards
     * @return returns [boolean, CardRanking]
     */
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

    /**
     * returns if CardRanking.HIGH_CARD is true else it returns null.
     * @param hand takes in an ArrayList of Cards
     * @return returns [boolean, CardRanking]
     */
    //High Card
    public static ArrayList<Object> highCard(ArrayList<Card> hand) {
        ArrayList<Object> high = new ArrayList<>();
        high.add(true);
        high.add(CardRankings.HIGH_CARD);
        high.add(hand.get(hand.size() - 1).getRank());
        high.add(hand.get(hand.size() - 2).getRank());
        return high;
    }
}
