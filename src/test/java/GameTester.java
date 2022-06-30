import com.model.team.Card;
import com.model.team.Deck;
import com.model.team.Hands;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GameTester {

    public static ArrayList<Card> user = new ArrayList<>();
    public static ArrayList<Card> table = new ArrayList<>();
    public static ArrayList<Card> dealer = new ArrayList<>();

    Deck deck = new Deck();
    Card card1 = deck.draw();
    Card card2 = deck.draw();
    Card card3 = deck.draw();
    Card card4 = deck.draw();
    Card card5 = deck.draw();
    Card card6 = deck.draw();
    Card card7 = deck.draw();
    Card card8 = deck.draw();
    Card card9 = deck.draw();
    Card card10 = deck.draw();
    Card card11 = deck.draw();
    Card card12 = deck.draw();
    Card card13 = deck.draw();
    Card card14 = deck.draw();
    Card card15 = deck.draw();
    Card card16 = deck.draw();
    Card card17 = deck.draw();
    Card card18 = deck.draw();
    Card card19 = deck.draw();
    Card card20 = deck.draw();
    Card card21 = deck.draw();
    Card card22 = deck.draw();
    Card card23 = deck.draw();
    Card card24 = deck.draw();
    Card card25 = deck.draw();
    Card card26 = deck.draw();
    Card card27 = deck.draw();
    Card card28 = deck.draw();
    Card card29 = deck.draw();
    Card card30 = deck.draw();
    Card card31 = deck.draw();
    Card card32 = deck.draw();
    Card card33 = deck.draw();
    Card card34 = deck.draw();
    Card card35 = deck.draw();
    Card card36 = deck.draw();
    Card card37 = deck.draw();
    Card card38 = deck.draw();
    Card card39 = deck.draw();
    Card card40 = deck.draw();
    Card card41 = deck.draw();
    Card card42 = deck.draw();
    Card card43 = deck.draw();
    Card card44 = deck.draw();
    Card card45 = deck.draw();
    Card card46 = deck.draw();
    Card card47 = deck.draw();
    Card card48 = deck.draw();
    Card card49 = deck.draw();
    Card card50 = deck.draw();
    Card card51 = deck.draw();
    Card card52 = deck.draw();

    @Test
    public void RankTest(){
        dealer.add(card13);
        dealer.add(card9);
        table.add(card3);
        table.add(card4);
        table.add(card1);
        table.add(card5);
        table.add(card6);
        user.add(card44);
        user.add(card32);
        System.out.println((dealer + " " + table + " " + user));
        //user has a flush while dealer has a straight flush.
        assertEquals("winner: Dealer", Hands.compares(dealer, user, table));
        //assertNotEquals("push",Hands.compares(dealer,user,table));
        //assertNotEquals("winner: Dealer", Hands.compares(dealer,user,table));


    }

}
