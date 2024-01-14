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
    private JLabel RESULT = new JLabel();


    public SimplePokerGameGUI() {
        super("Simple Poker Game");
        initializeCardImages();
        initializeUI();
    }

    private void initializeCardImages() {
        JProgressBar progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setString("Loading Images");
        progressBar.setForeground(Color.GREEN);
        progressBar.setBounds(40, 40, 160, 30);
        progressBar.setMinimum(0);
        progressBar.setMaximum(52);
        JFrame progressFrame = new JFrame("POKER GAME BY UTKU BERKİ BAYSAL");
        progressFrame.add(progressBar);
        progressFrame.setSize(400, 75);
        progressFrame.setLocationRelativeTo(null);
        progressFrame.setVisible(true);
        for (int i = 0; i < 52; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (i < 20 ) {
                progressBar.setString("Loading Images.");
            } else if (i < 35 ) {
                progressBar.setString("Loading Cards..");
            } else if (i < 45 ) {
                progressBar.setString("Loading Buttons...");
            } else {
                progressBar.setString("Finishing...");
            }
            progressBar.setValue(i);
            String filename = "images/card_" + i + ".png";
            System.out.println("Loading image: " + filename);
            cardImages[i] = new ImageIcon(filename);
            progressBar.setValue(i);
            if (cardImages[i]==null) {
                System.out.println("Error loading image: " + filename);
                System.out.println("Please check that the images folder is in the same directory as the images folder.");
            }
        }
        progressFrame.setVisible(false);
    }


    private void initializeUI() {
        setSize(500, 300);
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
        Card[] hand = new Card[5];

        if (deck.remainingCards() < 5) {
            JOptionPane.showMessageDialog(this, "Not enough cards in the deck to deal a hand. To continue please reset deck.", "Deck Empty", JOptionPane.ERROR_MESSAGE);
            Deck newDeckk = new Deck(true);
            newDeckk.shuffle();
            deck = newDeckk;
        }

        for (int i = 0; i < 5; i++) {
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
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        JButton dealButton = new JButton("NEW GAME");
        JButton resetButton = new JButton("RESET");
        JButton exitButton = new JButton("EXIT");

        dealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deck.shuffle();
                handValuePanel.removeAll();
                Card[] hand1 = updateCardPanel(cardPanel1,deck);
                Card[] hand2 = updateCardPanel(cardPanel2,deck);
                updateHandValuePanel(handValuePanel, hand1);
                updateHandValuePanel(handValuePanel, hand2);
                JOptionPane.showMessageDialog(null, CompareHands.compareHands(new CalculateHands(), hand1, hand2));

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
                handValuePanel.removeAll();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(dealButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(exitButton);
        return buttonPanel;
    }

    public static void main(String[] args) {
       new SimplePokerGameGUI();
    }
}
