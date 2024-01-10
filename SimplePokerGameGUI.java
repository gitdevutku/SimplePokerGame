import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimplePokerGameGUI extends JFrame {
    private final Color pokerTable = new Color(0, 128, 0);
    private final ImageIcon backImage = new ImageIcon("images/card_back.png");
    private final ImageIcon[] cardImages = new ImageIcon[52];
    private final JPanel cardPanel1 = new JPanel(new GridLayout(1, 5));
    private final JPanel cardPanel2 = new JPanel(new GridLayout(1, 5));
    private Deck deck = new Deck(true);
    private final CalculateHands Calculate = new CalculateHands();
    private JPanel handValuePanel = new JPanel(new GridLayout(2, 1));


    public SimplePokerGameGUI() {
        super("Simple Poker Game");
        initializeCardImages();
        initializeUI();
    }

    private void initializeCardImages() {
        for (int i = 0; i < 52; i++) {
            String filename = "images/card_" + i + ".png";
            System.out.println("Loading image: " + filename);
            cardImages[i] = new ImageIcon(filename);
        }
    }


    private void initializeUI() {
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        cardPanel1.setBackground(pokerTable);
        cardPanel2.setBackground(pokerTable);
        JPanel paint = new JPanel();
        paint.setBackground(pokerTable);
        add(paint, BorderLayout.CENTER);
        add(cardPanel1, BorderLayout.NORTH);
        add(createButtonPanel(), BorderLayout.WEST);
        add(cardPanel2, BorderLayout.SOUTH);
        add(handValuePanel, BorderLayout.EAST);
        setVisible(true);
    }

    private Card[] updateCardPanel(JPanel cardPanel, Deck deck) {
        cardPanel.removeAll();
        Card card;
        Card[] hand = new Card[4];

        if (deck.remainingCards() < 4) {
            JOptionPane.showMessageDialog(this, "Not enough cards in the deck to deal a hand.", "Deck Empty", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        for (int i = 0; i < 4; i++) {
            card = deck.dealCard();
            if (card != null) {
                int cardIndex = card.getValue() - 2 + card.getSuit() * 13;
                System.out.println("Card Index: " + cardIndex); // Debugging
                hand[i] = card;
                cardPanel.add(new JLabel(cardImages[cardIndex]));
            } else {
                System.out.println("No more cards in the deck");
            }
        }

        cardPanel.revalidate();
        cardPanel.repaint();

        return hand;
    }
    private void updateHandValuePanel(JPanel handValuePanel, Card[] hand) {
        handValuePanel.add(new JLabel(Calculate.evaluateHand(hand)));
    }
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        JButton dealButton = new JButton("Deal");
        JButton resetButton = new JButton("Reset");

        dealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deck.shuffle();
                handValuePanel.removeAll();
                updateHandValuePanel(handValuePanel, updateCardPanel(cardPanel1,deck));
                updateHandValuePanel(handValuePanel, updateCardPanel(cardPanel2,deck));
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Deck newDeck = new Deck(true);
                newDeck.shuffle();
                deck = newDeck; // Set the new deck to the existing deck
                cardPanel1.removeAll();
                cardPanel2.removeAll();
                cardPanel1.revalidate();
                cardPanel1.repaint();
                cardPanel2.revalidate();
                cardPanel2.repaint();
            }
        });
        buttonPanel.add(dealButton);
        buttonPanel.add(resetButton);
        return buttonPanel;
    }

    public static void main(String[] args) {
       new SimplePokerGameGUI();
    }
}
