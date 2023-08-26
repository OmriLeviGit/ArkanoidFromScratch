// 206573289 Omri Levi


package game.levels.gameFunction;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.animationAndScreens.Animation;
import game.animationAndScreens.AnimationRunner;
import game.animationAndScreens.CountdownAnimation;
import game.animationAndScreens.KeyPressStoppableAnimation;
import game.animationAndScreens.PauseScreen;
import game.levels.gameFunction.environment.Collidable;
import game.levels.gameFunction.environment.GameEnvironment;
import game.levels.gameFunction.environment.Sprite;
import game.levels.gameFunction.environment.SpriteCollection;
import game.levels.gameFunction.indicatorsAndCounters.Counter;
import game.levels.gameFunction.indicatorsAndCounters.NameIndicator;
import game.levels.gameFunction.indicatorsAndCounters.PauseText;
import game.levels.gameFunction.indicatorsAndCounters.ScoreIndicator;
import game.levels.LevelInformation;
import game.levels.gameFunction.listeners.BallRemover;
import game.levels.gameFunction.listeners.BlockRemover;
import game.levels.gameFunction.listeners.ScoreTrackingListener;
import game.shapes.circles.Ball;
import game.shapes.circles.Point;
import game.shapes.squares.Block;
import game.shapes.squares.Paddle;
import game.shapes.squares.Rectangle;

import java.awt.Color;


/**
 * The GameLevel class represents each level. includes a collection of sprites, a game environment and the GUI.
 * <p>
 * In addition, the class includes constants for its width, height, border thickness, and offset.
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

    private final SpriteCollection sprites = new SpriteCollection();
    private final GameEnvironment environment = new GameEnvironment();
    private final Counter score;
    private final Counter ballsRemained;
    private final Counter blocksRemained;
    private final LevelInformation levelInfo;
    private final KeyboardSensor keyboard;
    private final AnimationRunner runner;
    private Paddle paddle;
    private boolean running;


    /**
     * Instantiates a new Game level.
     *
     * @param levelInfo       the level info
     * @param keyboardSensor  the keyboard sensor
     * @param runner the animation runner
     * @param score           the score
     * @param ballsRemained   the balls remained
     * @param blocksRemained  the blocks remained
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboardSensor, AnimationRunner runner,
                     Counter score, Counter ballsRemained, Counter blocksRemained) {
        this.levelInfo = levelInfo;
        this.keyboard = keyboardSensor;
        this.runner = runner;
        this.score = score;
        this.ballsRemained = ballsRemained;
        this.blocksRemained = blocksRemained;
    }

    /**
     * Initializes each level by creating and adding the game blocks, balls, a paddle, etc.
     */
    public void initialize() {

        // add background
        levelInfo.getBackground().addToGame(this);

        // add paddle
        this.paddle = new Paddle(levelInfo.paddleWidth(), levelInfo.paddleSpeed(), this.keyboard);
        this.paddle.addToGame(this);

        // add balls to the game
        Point ballPoint = new Point((double) GameLevel.WIDTH / 2,
                paddle.getCollisionRectangle().getY() - GameLevel.OFFSET * 2);
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

        // add indicators
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);

        NameIndicator nameIndicator = new NameIndicator(this.levelInfo.levelName());
        nameIndicator.addToGame(this);

        PauseText pauseText = new PauseText();
        pauseText.addToGame(this);
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
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
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
            this.runner.run(new KeyPressStoppableAnimation(keyboard, keyboard.SPACE_KEY, new PauseScreen()));
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
