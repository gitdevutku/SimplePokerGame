import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimplePokerGameGUI extends JFrame {

    public SimplePokerGameGUI() {
        setTitle("Simple Poker Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel buttonPanel = createButtonPanel();
        JLabel statusLabel = new JLabel("Welcome to Simple Poker Game");
        add(buttonPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
        add(createCardPanel(), BorderLayout.NORTH);
    }
    private JPanel createCardPanel() {
        JPanel cardPanel = new JPanel(new GridLayout(1, 5));

        // Load card images (adjust the file paths accordingly)
        ImageIcon backImage = new ImageIcon("images/back.png");
        ImageIcon[] cardImages = new ImageIcon[52];

        for (int i = 0; i < 52; i++) {
            cardImages[i] = new ImageIcon("images/card_" + i + ".png");
        }

        Deck deck = new Deck(false);
        deck.shuffle();

        JLabel[] cardLabels = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            Card card = deck.dealCard();
            if (card != null) {
                int cardIndex = card.getValue() + card.getSuit() * 13; // Assuming a standard deck
                cardLabels[i] = new JLabel(cardImages[cardIndex]);
                cardPanel.add(cardLabels[i]);
            } else {
                // Handle the case when the deck is empty
                // You might want to reshuffle or end the game
                System.out.println("Deck is empty!");
            }
        }
        return cardPanel;
    }


    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));

        JButton dealButton = new JButton("Deal");
        JButton betButton = new JButton("Bet");
        JButton foldButton = new JButton("Fold");
        JButton resetButton = new JButton("Reset");

        dealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement your logic for dealing cards
                // Update statusLabel accordingly
                JOptionPane.showMessageDialog(SimplePokerGameGUI.this, "Dealing cards...");
            }
        });

        betButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement your logic for betting
                // Update statusLabel accordingly
                JOptionPane.showMessageDialog(SimplePokerGameGUI.this, "Placing bet...");
            }
        });

        foldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement your logic for folding
                // Update statusLabel accordingly
                JOptionPane.showMessageDialog(SimplePokerGameGUI.this, "Folding...");
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement your logic for resetting the game
                // Update statusLabel accordingly
                JOptionPane.showMessageDialog(SimplePokerGameGUI.this, "Resetting game...");
            }
        });

        // Add buttons to the panel
        buttonPanel.add(dealButton);
        buttonPanel.add(betButton);
        buttonPanel.add(foldButton);
        buttonPanel.add(resetButton);

        return buttonPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimplePokerGameGUI().setVisible(true);
            }
        });
    }
}

