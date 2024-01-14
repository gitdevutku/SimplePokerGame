import java.util.Arrays;

public class CompareHands {
    private static int[] getSortedValues(Card[] hand) {
        int[] values = new int[hand.length];
        for (int i = 0; i < hand.length; i++) {
            values[i] = hand[i].getValue();
        }
        Arrays.sort(values);
        return values;
    }
    public static String compareHands(CalculateHands calculator, Card[] UpperPlayer, Card[] LowerPlayer) {
        String resultHand1 = calculator.evaluateHand(UpperPlayer);
        String resultHand2 = calculator.evaluateHand(LowerPlayer);

        int resultComparison = compareResults(resultHand1, resultHand2);

        if (resultComparison > 0) {
            return "Upper player wins with " + resultHand1;
        } else if (resultComparison < 0) {
            return "Lower player wins with " + resultHand2;
        } else {
            int highCardComparison = compareHighCards(UpperPlayer, LowerPlayer);
            if (highCardComparison > 0) {
                return "Upper Player wins with a higher high card";
            } else if (highCardComparison < 0) {
                return "Lower Player wins with a higher high card";
            } else {
                return "It's a tie!";
            }
        }
    }

    private static int compareResults(String result1, String result2) {
        // Poker hand rankings
        String[] pokerHands = {
                "Royal Flush", "Straight Flush", "Four of a Kind", "Full House",
                "Flush", "Straight", "Three of a Kind", "Two Pair", "One Pair"
        };

        // Get indices of the results in the pokerHands array
        int index1 = Arrays.asList(pokerHands).indexOf(result1);
        int index2 = Arrays.asList(pokerHands).indexOf(result2);

        // Compare the indices
        return Integer.compare(index1, index2);
    }

    private static int compareHighCards(Card[] hand1, Card[] hand2) {
        int[] values1 = getSortedValues(hand1);
        int[] values2 = getSortedValues(hand2);

        for (int i = values1.length - 1; i >= 0; i--) {
            if (values1[i] > values2[i]) {
                return 1;
            } else if (values1[i] < values2[i]) {
                return -1;
            }
        }
        return 0;
    }
}
