import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck(boolean initializeDeck) {
        cards = new ArrayList<>();
        if (initializeDeck) {
            initializeDeck();
        }
    }

    private void initializeDeck() {
        for (int suit = 0; suit < 4; suit++) {
            for (int value = 2; value <= 14; value++) {
                cards.add(new Card(value, suit));
            }
        }
    }


    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        } else {
            System.out.println("No more cards in the deck");
            return null;
        }
    }

    public int remainingCards() {
        return cards.size();
    }
}
