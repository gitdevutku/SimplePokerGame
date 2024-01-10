import java.util.Arrays;
import java.util.HashMap;

public class CalculateHands {
    public String evaluateHand(Card[] hand) {
        if (isRoyalFlush(hand)) {
            return "Royal Flush";
        } else if (isStraightFlush(hand)) {
            return "Straight Flush";
        } else if (isFourOfAKind(hand)) {
            return "Four of a Kind";
        } else if (isFullHouse(hand)) {
            return "Full House";
        } else if (isFlush(hand)) {
            return "Flush";
        } else if (isStraight(hand)) {
            return "Straight";
        } else if (isThreeOfAKind(hand)) {
            return "Three of a Kind";
        } else if (isTwoPair(hand)) {
            return "Two Pair";
        } else if (isOnePair(hand)) {
            return "One Pair";
        } else {
            return "High Card: " + getHighCard(hand);
        }
    }

    private boolean isRoyalFlush(Card[] hand) {
        return isStraightFlush(hand) && hand[0].getValue() == 10;
    }

    private boolean isStraightFlush(Card[] hand) {
        return isFlush(hand) && isStraight(hand);
    }

    private boolean isFourOfAKind(Card[] hand) {
        HashMap<Integer, Integer> valueCounts = getValueCounts(hand);
        return valueCounts.containsValue(4);
    }

    private boolean isFullHouse(Card[] hand) {
        HashMap<Integer, Integer> valueCounts = getValueCounts(hand);
        return valueCounts.containsValue(3) && valueCounts.containsValue(2);
    }

    private boolean isFlush(Card[] hand) {
        int suit = hand[0].getSuit();
        for (Card card : hand) {
            if (card.getSuit() != suit) {
                return false;
            }
        }
        return true;
    }

    private boolean isStraight(Card[] hand) {
        int[] values = getSortedValues(hand);

        // Special case for Ace (14) being at the start of a straight
        if (values.length == 5 && values[0] == 2 && values[1] == 3 && values[2] == 4 && values[3] == 5 && values[4] == 14) {
            return true;
        }

        for (int i = 0; i < values.length - 1; i++) {
            if (values[i + 1] - values[i] != 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isThreeOfAKind(Card[] hand) {
        HashMap<Integer, Integer> valueCounts = getValueCounts(hand);
        return valueCounts.containsValue(3);
    }

    private boolean isTwoPair(Card[] hand) {
        HashMap<Integer, Integer> valueCounts = getValueCounts(hand);
        int pairCount = 0;
        for (int count : valueCounts.values()) {
            if (count == 2) {
                pairCount++;
            }
        }
        return pairCount == 2;
    }

    private boolean isOnePair(Card[] hand) {
        HashMap<Integer, Integer> valueCounts = getValueCounts(hand);
        return valueCounts.containsValue(2);
    }

    private int[] getSortedValues(Card[] hand) {
        int[] values = new int[hand.length];
        for (int i = 0; i < hand.length; i++) {
            values[i] = hand[i].getValue();
        }
        Arrays.sort(values);
        return values;
    }

    private HashMap<Integer, Integer> getValueCounts(Card[] hand) {
        HashMap<Integer, Integer> valueCounts = new HashMap<>();
        for (Card card : hand) {
            int value = card.getValue();
            valueCounts.put(value, valueCounts.getOrDefault(value, 0) + 1);
        }
        return valueCounts;
    }

    private int getHighCard(Card[] hand) {
        int[] values = getSortedValues(hand);
        return values[values.length - 1];
    }
}
