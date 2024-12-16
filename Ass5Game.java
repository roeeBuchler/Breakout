//212067466 Roee Buchler

import General.Game;

/**
 * The main class for the game.
 * This class creates a new game object, initializes it, and runs it.
 */
public class Ass5Game {
    /**
     * The main method for the game.
     * This method creates a new game object, initializes it, and runs it.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create a new game object, and run it.
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
