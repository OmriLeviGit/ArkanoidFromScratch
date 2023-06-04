// 206573289 Omri Levi


package game.gameFunction;

import biuoop.KeyboardSensor;
import game.environment.GameEnvironment;
import game.listener.BallRemover;
import game.listener.HitListener;
import game.listener.ScoreTrackingListener;
import game.shapes.circles.Ball;
import game.shapes.circles.Point;
import game.shapes.circles.Velocity;
import game.shapes.squares.Block;
import game.shapes.squares.Paddle;
import game.shapes.squares.Rectangle;

import java.awt.Color;
import java.util.Random;

/**
 * The Initializer class is a helper class used for initializing various game objects.
 */
public class Initializer {
    private Game game;


    /**
     * Sets the game the initializer will initialize.
     *
     * @param game the game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Creates a background rectangle object with the given color, and add it to the game.
     *
     * @param color the color of the background
     */
    public void background(Color color) {
        Rectangle background = new Rectangle(Game.WIDTH, Game.HEIGHT);
        background.setColor(color);
        background.addToGame(this.game);
    }

    /**
     * Creates an array of blocks representing the game borders, and add it to the game.
     *
     * @param rectangle the rectangle
     * @param thickness the thickness
     */
    public void borders(Rectangle rectangle, double thickness) {
        double x = rectangle.getX();
        double y = rectangle.getY();

        Point leftBorderPoint = new Point(x, y + thickness);
        Point rightBorderPoint = new Point(x + rectangle.getWidth() - thickness, y + thickness);

        Block[] borderArray = {
                new Block(rectangle.getUpperLeft(), rectangle.getWidth(), thickness * 2),   // top border
                new Block(rightBorderPoint, thickness, rectangle.getHeight() - thickness),  // left border
                new Block(leftBorderPoint, thickness, rectangle.getHeight() - thickness),   // right border
        };

        for (Block border : borderArray) {
            border.getCollisionRectangle().setColor(Color.GRAY);
            border.addToGame(this.game);
        }

        Block scorePlaceholder = new Block(rectangle.getUpperLeft(), rectangle.getWidth(), thickness);
        scorePlaceholder.getCollisionRectangle().setColor(Color.ORANGE);
        scorePlaceholder.addToGame(this.game);
    }

    /**
     * Creates a paddle object with the given dimensions and keyboard input, and add it to the game.
     *
     * @param paddleWidth  the width of the paddle
     * @param paddleHeight the paddleHeight of the paddle
     * @param keyboard     the keyboard input object
     */
    public void paddle(int paddleWidth, int paddleHeight, KeyboardSensor keyboard) {

        // center the paddle and adjust paddleHeight to match the border
        double xPaddle = (double) (Game.WIDTH - paddleWidth) / 2;
        double yPaddle = Game.HEIGHT - (paddleHeight + Game.BORDER_THICKNESS);

        // create paddle
        Rectangle paddleShape = new Rectangle(xPaddle, yPaddle, paddleWidth, paddleHeight);
        paddleShape.setColor(Color.yellow);
        Paddle paddle = new Paddle(paddleShape, keyboard);

        // add to the game
        paddle.addToGame(this.game);
        this.game.setPaddle(paddle);
    }

    /**
     * Creates an array of ball objects with the given parameters, and add it to the game.
     *
     * @param numOfBalls   the number of balls to create
     * @param environment  the game environment object
     * @param paddleHeight the height of the paddle
     */
    public void randomBalls(int numOfBalls, GameEnvironment environment, double paddleHeight) {
        Random rand = new Random();

        int radius = 5;                             // radius of the ball
        int angleRange = 30;                        // the angle will be randomized between -angleRange and angleRange

        double ballX = (double) Game.WIDTH / 2;                                 // spawn at the center
        double ballY = paddleHeight - radius * 2;      // spawn above the paddle
        Point point = new Point(ballX, ballY);

        // initialize array of balls
        for (int i = 0; i < numOfBalls; i++) {
            Ball ball = new Ball(point, radius, Color.WHITE);               // create ball
            int angle = rand.nextInt(angleRange * 2) - angleRange;          // get an angle between -30 and 30

            ball.setVelocity(Velocity.fromAngleAndSpeed(angle, 5));         // assign velocity
            ball.setGameEnvironment(environment);                           // assign game environment
            ball.addToGame(this.game);                                      // add ball to the game
        }
    }

    /**
     * Creates an array of block objects representing the game blocks, and add it to the game.
     *
     * @param blockRemover the block remover
     * @param scoreTracker the score tracker
     * @return the number of generated blocks
     */
    public int gameBlocks(HitListener blockRemover, ScoreTrackingListener scoreTracker) {
        Color[] color = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
        int count = 0;

        // initialize blocks
        final int maxBlocksInRow = 15;                                          // more than that will overfill rows
        final double legalWidth = Game.WIDTH - Game.BORDER_THICKNESS * 2;       // game width without borders
        final double legalHeight = Game.WIDTH - Game.BORDER_THICKNESS * 2;      // game height without borders

        int numOfBlocks = 12;                                   // amount of blocks in the longest row
        int numOfRows = 5;                                      // amount of rows required for the assignment
        int heightFromCeiling = 3;                              // used to increase distance from the top border

        // width and height are calculated with these ratios in mind
        int blockWidth = (int) (legalWidth / maxBlocksInRow);
        int blockHeight = (int) ((legalHeight * (legalWidth / maxBlocksInRow)) / (2 * legalWidth));

        // create blocks and initialize
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfBlocks - i; j++) {

                // set current x value starting from the right of the screen
                double currentPointX = Game.WIDTH - Game.BORDER_THICKNESS - blockWidth * (j + 1);
                double currentPointY = Game.BORDER_THICKNESS + blockHeight * (i + heightFromCeiling);
                Point upperLeft = new Point(currentPointX, currentPointY);
                Block block = new Block(upperLeft, blockWidth, blockHeight);

                // if there are more rows than colors in the color array, repeat color
                block.getCollisionRectangle().setColor(color[i % color.length]);

                block.addToGame(this.game);
                block.addHitListener(blockRemover);     // add a hit listener
                block.addHitListener(scoreTracker);     // add a hit listener
                count++;                                // increase the number of remained blocks in the game
            }
        }

        return count;
    }

    /**
     * Creates a death region that removes balls from the game.
     *
     * @param ballRemover the ball remover
     */
    public void deathRegion(BallRemover ballRemover) {
        Block deathRegion = new Block(new Point(0.0, Game.HEIGHT), Game.WIDTH, Game.OFFSET);
        deathRegion.getCollisionRectangle().setColor(Color.RED);
        deathRegion.addHitListener(ballRemover);
        deathRegion.addToGame(this.game);
    }

    /**
     * adds a score indicator to the game.
     *
     * @param score the score counter
     */
    public void scoreIndicator(Counter score) {
        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        scoreIndicator.addToGame(this.game);
    }
}
