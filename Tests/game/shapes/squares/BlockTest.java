package game.shapes.squares;

import biuoop.DrawSurface;
import biuoop.GUI;
import game.gameFunction.environment.GameEnvironment;
import game.gameFunction.GameLevel;
import game.gameFunction.Initializer;
import game.miscellaneous.DoubleCompare;
import game.shapes.circles.Ball;
import game.shapes.circles.Point;
import game.shapes.circles.Velocity;
import org.junit.jupiter.api.Test;
import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BlockTest {
    GameLevel game = new GameLevel();
    int numOfReps = 5;

    @Test
    public void changeDxWhenTouchesWall() {
        initialize(this.game);
        Ball ball = new Ball(10 + GameLevel.BORDER_THICKNESS, 10 + GameLevel.BORDER_THICKNESS, 5, Color.WHITE);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        double originalDx = -20;
        double originalDy = 0;
        ball.setVelocity(originalDx, originalDy);

        run(numOfReps);

        Velocity expectedV = new Velocity(-originalDx, originalDy);
        Velocity actualV = ball.getVelocity();

        assertTrue(VelocitiesEqual(expectedV, actualV));
    }

    @Test
    public void changeDyWhenTouchesWall() {
        initialize(this.game);
        Ball ball = new Ball(10 + GameLevel.BORDER_THICKNESS, 10 + GameLevel.BORDER_THICKNESS, 5, Color.WHITE);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        double originalDx = 0;
        double originalDy = -20;
        ball.setVelocity(originalDx, originalDy);

        run(numOfReps);

        Velocity expectedV = new Velocity(originalDx, -originalDy);
        Velocity actualV = ball.getVelocity();

        assertTrue(VelocitiesEqual(expectedV, actualV));
    }

    @Test
    public void testTouchesCornerTopLeft() {
        initialize(this.game);
        Ball ball = new Ball(7 + GameLevel.BORDER_THICKNESS, 7 + GameLevel.BORDER_THICKNESS, 5, Color.WHITE);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        double originalDx = -10;
        double originalDy = -10;
        ball.setVelocity(originalDx, originalDy);

        run(numOfReps);

        Velocity expectedV = new Velocity(-originalDx, -originalDy);
        Velocity actualV = ball.getVelocity();

        assertTrue(VelocitiesEqual(expectedV, actualV));
    }

    @Test
    public void testAlmostTouchesCornerTopLeftAndDoubleBounce() {
        initialize(this.game);
        Ball ball = new Ball(13 + GameLevel.BORDER_THICKNESS, 10 + GameLevel.BORDER_THICKNESS, 5, Color.WHITE);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        double originalDx = -10;
        double originalDy = -10;
        ball.setVelocity(originalDx, originalDy);

        run(numOfReps);

        Velocity expectedV = new Velocity(-originalDx, -originalDy);
        Velocity actualV = ball.getVelocity();

        assertTrue(VelocitiesEqual(expectedV, actualV));
    }

    @Test
    public void testTouchesCornerBottomRight() {
        initialize(this.game);
        Ball ball = new Ball(GameLevel.WIDTH - GameLevel.BORDER_THICKNESS - 7,
                GameLevel.HEIGHT - GameLevel.BORDER_THICKNESS - 7, 5, Color.WHITE);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        double originalDx = 10;
        double originalDy = 10;
        ball.setVelocity(originalDx, originalDy);

        run(numOfReps);

        Velocity expectedV = new Velocity(-originalDx, -originalDy);
        Velocity actualV = ball.getVelocity();

        assertTrue(VelocitiesEqual(expectedV, actualV));
    }

    @Test
    public void test1() {
        GUI gui = new GUI("Test Part 1 - ass3", 300, 300);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        GameEnvironment gameEnvironment = new GameEnvironment();

        Ball ball = new Ball(50, 150, 5, Color.blue);
        ball.setVelocity(10, -10);
        ball.setGameEnvironment(gameEnvironment);

        //for check with borders and one block
        Block[] blocks = new Block[6];

        //left border
        blocks[0] = new Block(new Rectangle(new Point(0, 0), 10, 200));
        blocks[0].getCollisionRectangle().setColor(Color.YELLOW);
        //top border
        blocks[1]= new Block(new Rectangle(new Point(0, 0), 200, 10));
        blocks[1].getCollisionRectangle().setColor(Color.RED);
        //right border
        blocks[2] = new Block(new Rectangle(new Point(200, 0), 10, 200));
        blocks[2].getCollisionRectangle().setColor(Color.BLUE);
        //down border
        blocks[3] = new Block(new Rectangle(new Point(0, 200), 200, 10));
        blocks[3].getCollisionRectangle().setColor(Color.GREEN);
        //seen on screen
        blocks[4] = new Block(new Rectangle(new Point(50, 50), 50, 50));
        blocks[4].getCollisionRectangle().setColor(Color.GRAY);
        blocks[5] = new Block(new Rectangle(new Point(100, 50), 50, 50));
        blocks[5].getCollisionRectangle().setColor(Color.BLACK);

        for (Block block : blocks) {
            gameEnvironment.addCollidable(block);
        }

        long startTime = System.currentTimeMillis(); // timing
        //draw animation
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            //draw blocks
            for (Block block : blocks) {
                block.drawOn(d);
            }

            ball.moveOneStep();
            ball.drawOn(d);


            gui.show(d);
            sleeper.sleepFor(5);  // wait for 50 milliseconds.
            long usedTime = System.currentTimeMillis() - startTime;

            if (usedTime > 400) {
                break;
            }
        }
    }

    public void run(int reps) {
        game.run(reps);
    }

    public void initialize(GameLevel game) {
        Initializer initialize = new Initializer();             // create an initializer
        initialize.setGame(this.game);                          // add a reference to the game

        initialize.background(Color.BLACK);
        //initialize.borders();
    }

    public boolean VelocitiesEqual(Velocity expected, Velocity actual) {
        double dx = expected.getDx();
        double dy = expected.getDy();

        double aDx = actual.getDx();
        double aDy = actual.getDy();

        boolean dxEquals = DoubleCompare.equals(dx, aDx);
        boolean dyEquals = DoubleCompare.equals(dy, aDy);

        return dxEquals && dyEquals;
    }
}