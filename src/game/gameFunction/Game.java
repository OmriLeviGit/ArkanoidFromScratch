// 206573289 Omri Levi


package game.gameFunction;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.animation.Animation;
import game.animation.AnimationRunner;
import game.animation.PauseScreen;
import game.environment.Collidable;
import game.environment.GameEnvironment;
import game.environment.Sprite;
import game.environment.SpriteCollection;
import game.listener.BallRemover;
import game.listener.BlockRemover;
import game.listener.ScoreTrackingListener;
import game.shapes.squares.Paddle;
import game.shapes.squares.Rectangle;

import java.awt.Color;


/**
 * The Game class represents a game that includes a collection of sprites, a game environment and the GUI.
 * <p>
 * The game includes constants for its width, height, border thickness, and offset.
 * It also includes methods for adding a collidable or a sprite, initializing the game, and running the game loop.
 * </p>
 */
public class Game implements Animation {
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

    private final SpriteCollection sprites = new SpriteCollection();
    private final GameEnvironment environment = new GameEnvironment();
    private final GUI gui = new GUI("Game", WIDTH, HEIGHT);
    private final Counter score = new Counter();
    private final Counter ballsRemained = new Counter();
    private final Counter blocksRemained = new Counter();
    private final KeyboardSensor keyboard = gui.getKeyboardSensor();
    private Paddle paddle;

    private final AnimationRunner runner = new AnimationRunner(gui, 60);
    private boolean running;

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
     * Initializes the game by creating and adding the game's blocks, ball(s), and paddle to the game.
     */
    public void initialize() {
        Initializer initialize = new Initializer();         // create an initializer
        initialize.setGame(this);                           // add a reference to the game

        int numOfBalls = 3;     // number of balls in the game
        int numOfBlocks;        // number of blocks in the game

        BlockRemover blockRemover = new BlockRemover(this, this.blocksRemained);        // the blocker remover
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(this.score);     // the score tracker

        initialize.background(Color.BLACK);
        initialize.borders(new Rectangle(Game.WIDTH, Game.HEIGHT), Game.BORDER_THICKNESS);
        initialize.paddle(90, 20, keyboard);
        initialize.randomBalls(numOfBalls, this.environment, this.paddle.getCollisionRectangle().getY());
        initialize.deathRegion(new BallRemover(this, this.ballsRemained));
        initialize.scoreIndicator(this.score);

        numOfBlocks = initialize.gameBlocks(blockRemover, scoreTracker);

        ballsRemained.increase(numOfBalls);
        blocksRemained.increase(numOfBlocks);
    }

    /**
     * Start running the game.
     */
    public void run() {
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
}
