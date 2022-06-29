package com.model;

import com.model.team.Card;
import com.model.team.Deck;

import java.util.ArrayList;
import java.util.Comparator;

import static com.model.team.Hands.compares;

public class Game {
    public static ArrayList<Card> user = new ArrayList<>();
    public static ArrayList<Card> table = new ArrayList<>();
    public static ArrayList<Card> dealer = new ArrayList<>();
    private final Comparator<Card> displayComparator = Comparator
            .comparing(Card::getRank);


    public Game() {
    }

    public static void main(String[] args) {
        Game game = new Game();
        Deck deck = new Deck();
        game.deal(deck);
//        Card card1 = deck.draw();
//        Card card2 = deck.draw();
//        Card card3 = deck.draw();
//        Card card4 = deck.draw();
//        Card card5 = deck.draw();
//        Card card6 = deck.draw();
//        Card card7 = deck.draw();
//        Card card8 = deck.draw();
//        Card card9 = deck.draw();
//        Card card10 = deck.draw();
//        Card card11 = deck.draw();
//        Card card12 = deck.draw();
//        Card card13 = deck.draw();
//
//        Card card14 = deck.draw();
//        Card card15 = deck.draw();
//        Card card16 = deck.draw();
//        Card card17 = deck.draw();
//
//        dealer.add(card5);
//        dealer.add(card13);
//        user.add(card3);
//        user.add(card4);
//
//        table.add(card5);
//        table.add(card6);
//        table.add(card7);
//        table.add(card8);
//        table.add(card9);
//        table.add(card10);
//        table.add(card11);

        System.out.println("Dealer" + dealer);
        System.out.println("User" + user);
        System.out.println("Table" + table);
        game.sorter(dealer);
        game.sorter(user);
        //Hands.getHand(dealer);
        String x = compares(dealer, user, table);
        //System.out.println(x +"  this");
        if (x.equals("winner: Dealer")){
            System.out.println("Dealers won");
        }else{
            System.out.println("User won");
        }
    }


    public void deal(Deck deck) {
        user.sort(displayComparator);
        table.sort(displayComparator);
        dealer.sort(displayComparator);

    }

    public void sorter(ArrayList<Card> deck) {
        deck.sort(displayComparator);
    }
}


