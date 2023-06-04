// 206573289 Omri Levi


import game.gameFunction.GameLevel;

/**
 * This class is the main entry point for the game
 * <p>
 * It creates an instance of the Game class, initializes it and starts its execution.
 * </p>
 */
public class Ass6Game {
    /**
     * The main method creates an instance of the game, initializes it and starts its execution.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        GameLevel game = new GameLevel();
        game.initialize();
        game.run();
    }
}
