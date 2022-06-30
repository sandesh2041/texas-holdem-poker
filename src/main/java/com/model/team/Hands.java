package com.model.team;

import java.util.ArrayList;
import java.util.Comparator;

public class Hands {

    private static final Comparator<Card> comparator = Comparator
            .comparing(Card::getRank);
    public static String printWinner;


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
        return HighCard(hand);
    }

    public static String compares(ArrayList<Card> hand1, ArrayList<Card> hand2, ArrayList<Card> shared) {
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

        String dealerResult = "";
        String userResult = "";
        for (int i = 1; i < first.size(); i++) {
            dealerResult += first.get(i) + " ";
        }
        for (int i = 1; i < second.size(); i++) {
            userResult += second.get(i) + " ";
        }
        printWinner = "Dealer: \"The dealer has " + dealerResult.toLowerCase() + "and the player has " + userResult.toLowerCase() + ".\"\n";





        CardRankings rank1 = (CardRankings) first.get(1);
        CardRankings rank2 = (CardRankings) second.get(1);

        Rank firstCard = (Rank) first.get(2);
        Rank secondCard = (Rank) second.get(2);

        Rank hand1LastCard = (Rank) first.get(2);
        Rank hand2LastCard = (Rank) second.get(2);

        if (rank1.ordinal() < rank2.ordinal()) {
            //System.out.println("winner: Dealer");
            return "winner: Dealer";
        }
        if (rank1.ordinal() > rank2.ordinal()) {
            // System.out.println("winner: User");
            return "winner: User";
        }
        if (firstCard.ordinal() > secondCard.ordinal()) {
            //System.out.println("winner: dealer2");
            return "winner: Dealer";
        }
        if (firstCard.ordinal() < secondCard.ordinal()) {
            // System.out.println("winner: user2");
            return "winner: User";
        }
        if (hand1LastCard.ordinal() > hand2LastCard.ordinal()) {
            // System.out.println("winner: dealer3");
            return "winner: Dealer";
        }
        if (hand1LastCard.ordinal() < hand2LastCard.ordinal()) {
            //System.out.println("winner: user3");
            return "winner: User";
        }
        if (dealer.ordinal() > user.ordinal()) {
            //System.out.println("winner: dealer4");
            return "winner: Dealer";
        }
        if (dealer.ordinal() < user.ordinal()) {
            //System.out.println("winner: user4");
            return "winner: User";
        }
        if (dealerSecond.ordinal() < userSecond.ordinal()) {
            //System.out.println("winner: dealer5");
            return "winner: Dealer";
        }
        if (dealerSecond.ordinal() > userSecond.ordinal()) {
            //System.out.println("winner: user5");
            return "winner: User";
        }
        //System.out.println("push");
        return "push";

    }


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
                    if (straight.size() >= 5 && straight.get(straight.size()-1).getRank() == Rank.ACE && straight.get(straight.size()-5).getRank() == Rank.TEN) {
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
        if (straight.size() >= 5 && straight.get(straight.size()-1).getRank() == Rank.ACE && straight.get(straight.size()-5).getRank() == Rank.TEN) {
            straightFlush.add(true);
            straightFlush.add(CardRankings.ROYAL_FLUSH);
            straightFlush.add(straight.get(straight.size() - 1).getRank());
            straightFlush.add(null);
        } else {
            return null;
        }
        return straightFlush;
    }

    //Straight Flush
    public static ArrayList<Object> straightFlush(ArrayList<Card> hand) {
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
                    if (straight.size() >= 5) {
                        val = highest.getSuit();
                        fin = new ArrayList<>();
                        fin.add(true);
                        fin.add(CardRankings.STRAIGHT_FLUSH);
                        fin.add(straight.get(straight.size() - 1).getRank());
                        fin.add(null);
                        return fin;
                    }
                    if(straight.size()>=4  && hand.get(hand.size()-1).getRank() == Rank.ACE && straight.get(0).getRank() == Rank.TWO){
                        fin.add(true);
                        fin.add(CardRankings.STRAIGHT_FLUSH);
                        fin.add(straight.get(straight.size() - 1).getRank());
                        fin.add(null);
                        return fin;
                    }

                    straight.clear();
                }
            }
            highest = card;
        }
        if (straight.size() >= 4 && hand.get(0).getRank() == Rank.TWO && hand.get(hand.size() - 1).getRank() == Rank.ACE) {
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
            if (counter == 5) {
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
                    if(straight.size()>=4  && hand.get(x-1).getRank() == Rank.ACE && straight.get(0).getRank() == Rank.TWO){
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
