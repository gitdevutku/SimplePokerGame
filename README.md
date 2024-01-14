# Simple Poker Game GUI

This project is a simple poker game implemented in Java with a graphical user interface (GUI) using the Swing library. The game allows players to play poker with a computer opponent, and it includes features such as dealing new hands, resetting the deck, and exiting the game.

## Project Structure

- **SimplePokerGameGUI class:** The main class that extends `JFrame` to create the GUI for the poker game. It includes components such as card panels, buttons, and labels.
- **Deck class:** Represents a deck of playing cards and includes methods for shuffling and dealing cards.
- **CalculateHands class:** Handles the calculation of the poker hands, determining the winner between two hands.
- **CompareHands class:** Compares two poker hands and determines the winner.

## GUI Components

### Poker Table and Card Panels

The GUI features a poker table with two card panels for the player and the computer opponent. The cards are displayed as images, and the panels are updated when a new game is dealt or the deck is reset.

### Progress Bar

During the initialization of card images, a progress bar is displayed to show the loading progress. The progress bar indicates the loading stages, such as loading images, cards, buttons, and finishing.

### Button Panel

The GUI includes buttons for the following actions:
- **New Game:** Shuffles the deck, deals new hands, and evaluates the winner.
- **Reset:** Creates a new shuffled deck and resets the card panels.
- **Exit:** Closes the application.

## Functionality

- The game uses a standard 52-card deck, and cards are represented as images.
- Players can deal new hands, and the winner is determined by comparing the poker hands using the `CalculateHands` and `CompareHands` classes.
- The deck is reset when there are not enough cards to deal a hand.

## How to Run

1. Run the `SimplePokerGameGUI` class.
2. The main window of the poker game GUI will appear.
3. Click the "NEW GAME" button to deal new hands.
4. Use the "RESET" button to start a new game with a shuffled deck.
5. Click the "EXIT" button to close the application.

Note: Ensure that the "images" folder with card images is in the same directory as the Java source files.

## Dependencies

- The project uses the Swing library for GUI components.
- Requires Java SDK installed on the system.

## Contributors

- Utku Berkí Baysal

## License

This project is open-source and is provided under the MIT License. Feel free to contribute and use the code for educational or personal purposes.
