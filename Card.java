public class Card {
    private int value;
    private int suit;

    public Card(int value, int suit) {
        if (isValidValue(value) && isValidSuit(suit)) {
            this.value = value;
            this.suit = suit;
        } else {
            throw new IllegalArgumentException("Illegal playing card value or suit");
        }
    }

    private boolean isValidValue(int value) {
        return value >= 2 && value <= 14;
    }

    private boolean isValidSuit(int suit) {
        return suit >= 0 && suit <= 3;
    }

    public int getValue() {
        return value;
    }

    public int getSuit() {
        return suit;
    }
}
