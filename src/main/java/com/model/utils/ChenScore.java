package com.model.utils;

import com.model.team.Card;
import com.model.team.Rank;
import com.model.team.Suit;

import java.util.ArrayList;

public class ChenScore {

    public double calculateScore (ArrayList<Card> cards) {
        double score;

        Rank rankOfFirst = cards.get(0).getRank();
        Rank rankOfSecond = cards.get(1).getRank();
        Suit suitOfFirst = cards.get(0).getSuit();
        Suit suitOfSecond = cards.get(1).getSuit();

        int firstCardValue = getCardValue(cards.get(0));
        int secondCardValue = getCardValue(cards.get(1));

        Rank highCard = calculateHighCard(cards);

        //Scoring highest card
        score = getScore(highCard);

        //Multiply pair by 2. Minimum score is 5.
        if(rankOfFirst == rankOfSecond){
                score *= 2;
                if(score < 5){
                    score = 5;
                }
        }

        //Add 2 score for same suit.
        if(suitOfFirst == suitOfSecond){
            score += 2;
        }

        if(rankOfFirst != rankOfSecond){
            //Subtract points for gaps.
            int cardGap = Math.abs(firstCardValue - secondCardValue);
            switch (cardGap) {
                case 1:
                    break;
                case 2:
                    score -= 1;
                    break;
                case 3:
                    score -= 2;
                    break;
                case 4:
                    score -= 4;
                    break;
                default:
                    score -= 5;
            }

            //Add 1 score of 0 or 1 gap if card is below Q
            if(firstCardValue < 12 && secondCardValue < 12 && cardGap < 3) {
                score += 1;
            }
        }

        //Round score to next up integer
        score = Math.ceil(score);

        return score;
    }

    private double getScore(Rank highCard) {
        double score;
        if(highCard ==  Rank.ACE){
            score = 10;
        } else if (highCard ==  Rank.KING){
            score = 8;
        } else if (highCard ==  Rank.QUEEN){
            score = 7;
        } else if (highCard ==  Rank.JACK){
            score = 6;
        } else {
            score = Double.parseDouble(highCard.getAbbreviation()) / 2;
        }
        return score;
    }

    private Rank calculateHighCard(ArrayList<Card> card){
        int cardValue = card.get(0).getRank().compareTo(card.get(1).getRank());
        return (cardValue > 0) ? card.get(0).getRank() : card.get(1).getRank();
    }

    private int getCardValue(Card card){
        int cardValue;
        switch(card.getRank().getAbbreviation()){
            case "A":
                cardValue = 14;
                break;
            case "K":
                cardValue = 13;
                break;
            case "Q":
                cardValue = 12;
                break;
            case "J":
                cardValue = 11;
                break;
            default:
                cardValue =  Integer.parseInt(card.getRank().getAbbreviation());

        }
        return cardValue;
    }

}
