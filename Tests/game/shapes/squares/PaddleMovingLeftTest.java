// 206573289 Omri Levi


package game.shapes.squares;

import biuoop.KeyboardSensor;
import game.levels.gameFunction.GameLevel;
import game.gameFunction.Initializer;
import game.miscellaneous.DoubleCompare;
import game.shapes.circles.Ball;
import game.shapes.circles.Velocity;
import org.junit.jupiter.api.Test;
import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaddleMovingLeftTest {
    GameLevel game = new GameLevel();
    int numOfReps = 3;

    /* the paddle is moving left */
    @Test
    public void paddleMovingTowardsStraightToBall() {
        initialize(this.game);
        int paddleX = GameLevel.WIDTH / 2 - 45;
        int paddleY = GameLevel.HEIGHT / 2;
        initializePaddle(paddleX, paddleY, game.getGui().getKeyboardSensor());

        double originalDx = 20;
        double originalDy = 0;

        Ball ball = new Ball(300, paddleY + 10, 5, Color.WHITE);
        ball.setVelocity(originalDx, originalDy);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        run(numOfReps);

        Velocity expectedV = new Velocity(- originalDx, 0);
        Velocity actualV = ball.getVelocity();

        assertTrue(VelocitiesEqual(expectedV, actualV));
    }

    @Test
    public void paddleMovingBallHitsBottom() {
        initialize(this.game);
        int paddleX = GameLevel.WIDTH / 2 - 45;
        int paddleY = GameLevel.HEIGHT / 2;
        initializePaddle(paddleX, paddleY, game.getGui().getKeyboardSensor());

        double originalDx = - 15;
        double originalDy = - 15;

        Ball ball = new Ball((double) GameLevel.WIDTH / 2, paddleY + 40, 5, Color.WHITE);
        ball.setVelocity(originalDx, originalDy);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        run(numOfReps);

        Velocity expectedV = new Velocity(originalDx, - originalDy);
        Velocity actualV = ball.getVelocity();

        assertTrue(VelocitiesEqual(expectedV, actualV));
    }

    @Test
    public void paddleMovingTowardsStraightBallAtAngle() {
        initialize(this.game);
        int paddleX = GameLevel.WIDTH / 2 - 45;
        int paddleY = GameLevel.HEIGHT / 2;
        initializePaddle(paddleX, paddleY, game.getGui().getKeyboardSensor());

        double originalDx = 10;
        double originalDy = 10;

        Ball ball = new Ball(325, paddleY - 5, 5, Color.WHITE);
        ball.setVelocity(originalDx, originalDy);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        run(numOfReps);

        Velocity expectedV = new Velocity(- originalDx, originalDy);
        Velocity actualV = ball.getVelocity();

        assertTrue(VelocitiesEqual(expectedV, actualV));
    }

    @Test
    public void testMovingToCatchClipping() {
        initialize(this.game);
        double xPaddle = GameLevel.WIDTH - (GameLevel.BORDER_THICKNESS + 320);
        double yPaddle = GameLevel.HEIGHT - (20 + GameLevel.BORDER_THICKNESS + GameLevel.OFFSET);
        initializePaddle(xPaddle, yPaddle, game.getGui().getKeyboardSensor());

        double originalDx = 0;
        double originalDy = 10;

        // hit at 400 560
        // so original ball coordinates are 400-45, 560-30
        // each paddle step is 7 so that 400 - 21

        Ball ball = new Ball((double) GameLevel.WIDTH / 2, 500, 5, Color.WHITE);
        ball.setVelocity(originalDx, originalDy);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        run(10);

        Velocity expectedV = new Velocity(originalDx, - originalDy);
        Velocity actualV = ball.getVelocity();

        assertTrue(VelocitiesEqual(expectedV, actualV));
    }


    public void initialize(GameLevel game) {
        Initializer initialize = new Initializer();             // create an initializer
        initialize.setGame(game);                          // add a reference to the game

        initialize.background(Color.BLACK);
        //initialize.borders();
    }

    public void initializePaddle(double xPaddle, double yPaddle, KeyboardSensor keyboard) {
        int paddleWidth = 90;
        int paddleHeight = 20;

        // create paddle
        Rectangle paddleShape = new Rectangle(xPaddle, yPaddle, paddleWidth, paddleHeight);
        paddleShape.setColor(Color.yellow);
        Paddle paddle = new Paddle(paddleShape, keyboard);

        // add to the game
        paddle.addToGame(this.game);
        this.game.setPaddle(paddle);
    }

    public void run(int reps) {
        //game.run(reps);
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