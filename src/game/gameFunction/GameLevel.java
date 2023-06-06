// 206573289 Omri Levi


package game.gameFunction;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.animation.Animation;
import game.animation.AnimationRunner;
import game.animation.CountdownAnimation;
import game.animation.PauseScreen;
import game.environment.Collidable;
import game.environment.GameEnvironment;
import game.environment.Sprite;
import game.environment.SpriteCollection;
import game.indicators.LivesIndicator;
import game.indicators.NameIndicator;
import game.indicators.ScoreIndicator;
import game.levels.LevelInformation;
import game.listener.BallRemover;
import game.listener.BlockRemover;
import game.listener.ScoreTrackingListener;
import game.shapes.circles.Ball;
import game.shapes.circles.Point;
import game.shapes.squares.Block;
import game.shapes.squares.Paddle;
import game.shapes.squares.Rectangle;

import java.awt.*;


/**
 * The Game class represents a game that includes a collection of sprites, a game environment and the GUI.
 * <p>
 * The game includes constants for its width, height, border thickness, and offset.
 * It also includes methods for adding a collidable or a sprite, initializing the game, and running the game loop.
 * </p>
 */
public class GameLevel implements Animation {
    /**
     * The constant WIDTH represents the width of the game.
     */
    public static final int WIDTH = 800;
    /**
     * The constant HEIGHT represents the height of the game.
     */
    public static final int HEIGHT = 600;
    /**
     * The constant BORDER_THICKNESS represents the thickness of the borders of the game.
     */
    public static final int BORDER_THICKNESS = 20;
    /**
     * The constant OFFSET represents small gaps that are used to separate various objects in the game.
     */
    public static final int OFFSET = 3;

    private final SpriteCollection sprites = new SpriteCollection();//
    private final GameEnvironment environment = new GameEnvironment();//
    private final Counter ballsRemained = new Counter();//
    private final Counter blocksRemained = new Counter();//
    private final GUI gui = new GUI("Game", WIDTH, HEIGHT);
    private final Counter score = new Counter();
    private final KeyboardSensor keyboard = gui.getKeyboardSensor();
    private Paddle paddle;//
    private final LevelInformation levelInfo;

    private final AnimationRunner runner = new AnimationRunner(gui, 60);
    private boolean running;

    public GameLevel(LevelInformation levelInfo) {
        this.levelInfo = levelInfo;
    }

    /**
     * Initializes the game by creating and adding the game blocks, balls, and a paddle.
     */
    public void initialize() {
        //levelInfo.levelName();

        // add background
        levelInfo.getBackground().addToGame(this);

        // add paddle
        this.paddle = new Paddle(levelInfo.paddleWidth(), levelInfo.paddleSpeed(), this.keyboard);
        this.paddle.addToGame(this);

        // add balls to the game
        Point ballPoint = new Point((double) WIDTH / 2, paddle.getCollisionRectangle().getY() - GameLevel.OFFSET * 2);
        ballsRemained.increase(levelInfo.numberOfBalls());

        for (int i = 0; i < levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(ballPoint, 5, Color.WHITE);                // create ball
            ball.setVelocity(levelInfo.initialBallVelocities().get(i));     // set velocity
            ball.setGameEnvironment(environment);                           // assign game environment
            ball.addToGame(this);                                           // add to game
        }

        // add borders
        createBorders(new Rectangle(GameLevel.WIDTH, GameLevel.HEIGHT), GameLevel.BORDER_THICKNESS);

        // add blocks
        BlockRemover blockRemover = new BlockRemover(this, this.blocksRemained);        // the blocker remover
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(this.score);     // the score tracker
        blocksRemained.increase(levelInfo.numberOfBlocksToRemove());

        for (Block block : levelInfo.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracker);
        }

        // add death region
        Block deathRegion = new Block(new Point(0.0, GameLevel.HEIGHT), GameLevel.WIDTH, GameLevel.OFFSET);
        deathRegion.getCollisionRectangle().setColor(Color.RED);
        deathRegion.addHitListener(new BallRemover(this, this.ballsRemained));
        deathRegion.addToGame(this);

        // add score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);

        LivesIndicator livesIndicator = new LivesIndicator(1);
        livesIndicator.addToGame(this);

        NameIndicator nameIndicator = new NameIndicator(this.levelInfo.levelName());
        nameIndicator.addToGame(this);


    }


    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to be added
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the sprite collection.
     *
     * @param s the sprite object to be added
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }


    /**
     * Start running the game.
     */
    public void run() {
        //this.runner.run(new CountdownAnimation(2, 3, this.sprites));    // countdown before turn starts. //TODO
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Gets the gui of the game.
     *
     * @return the gui
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Gets the environment of the game.
     *
     * @return the environment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Sets the paddle as a game field.
     *
     * @param paddle the paddle
     */
    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }


    /**
     * Remove the collidable from the game.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Remove the sprite from the game.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        sprites.removeS(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        sprites.drawAllOn(d);
        sprites.notifyAllTimePassed();

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
            this.runner.run(new CountdownAnimation(2, 3, this.sprites));    // countdown before turn starts.
        }

        if (this.ballsRemained.getValue() == 0) {
            this.running = false;
        }

        if (this.blocksRemained.getValue() == 0) {
            score.increase(100);                    // Clearing an entire level is worth another 100 points
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Creates an array of blocks representing the game borders, and add it to the game.
     *
     * @param rectangle the rectangle
     * @param thickness the thickness
     */
    public void createBorders(Rectangle rectangle, double thickness) {
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
            border.addToGame(this);
        }

        Block scorePlaceholder = new Block(rectangle.getUpperLeft(), rectangle.getWidth(), thickness);
        scorePlaceholder.getCollisionRectangle().setColor(Color.ORANGE);
        scorePlaceholder.addToGame(this);
    }

}
