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

class PaddleStaticTest {
    int numOfReps = 2;
    GameLevel game = new GameLevel();

    @Test
    public void ballHitsRegion1NoDx() {
        initialize(this.game);
        int paddleX = GameLevel.WIDTH / 2 - 45;
        int paddleY = GameLevel.HEIGHT / 2;
        initializePaddle(paddleX, paddleY, game.getGui().getKeyboardSensor());

        double ballOriginalDx = 0;
        double ballOriginalDy = 20;

        Ball ball = new Ball(paddleX + 9, (double) GameLevel.HEIGHT / 2 - 10, 5, Color.WHITE);
        ball.setVelocity(ballOriginalDx, ballOriginalDy);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        run(numOfReps);

        double dx = ball.getVelocity().getDx();
        double dy = ball.getVelocity().getDy();
        double speed = Math.sqrt((dx * dx) + (dy * dy));

        Velocity expectedV = Velocity.fromAngleAndSpeed(- 60, speed);
        Velocity actualV = ball.getVelocity();

        assertTrue(VelocitiesEqual(expectedV, actualV));
    }

    @Test
    public void ballHitsRegion2NoDx() {
        initialize(this.game);
        int paddleX = GameLevel.WIDTH / 2 - 45;
        int paddleY = GameLevel.HEIGHT / 2;
        initializePaddle(paddleX, paddleY, game.getGui().getKeyboardSensor());

        double ballOriginalDx = 0;
        double ballOriginalDy = 20;

        Ball ball = new Ball(paddleX + 10 + 9, (double) GameLevel.HEIGHT / 2 - 10, 5, Color.WHITE);
        ball.setVelocity(ballOriginalDx, ballOriginalDy);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        run(numOfReps);

        double dx = ball.getVelocity().getDx();
        double dy = ball.getVelocity().getDy();
        double speed = Math.sqrt((dx * dx) + (dy * dy));

        Velocity expectedV = Velocity.fromAngleAndSpeed(- 30, speed);
        Velocity actualV = ball.getVelocity();

        assertTrue(VelocitiesEqual(expectedV, actualV));
    }

    @Test
    public void ballHitsRegion3NoDx() {
        initialize(this.game);
        int paddleX = GameLevel.WIDTH / 2 - 45;
        int paddleY = GameLevel.HEIGHT / 2;
        initializePaddle(paddleX, paddleY, game.getGui().getKeyboardSensor());

        double ballOriginalDx = 0;
        double ballOriginalDy = 20;

        Ball ball = new Ball(paddleX + 9 + 18 * 2, (double) GameLevel.HEIGHT / 2 - 10, 5, Color.WHITE);
        ball.setVelocity(ballOriginalDx, ballOriginalDy);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        run(numOfReps);

        Velocity expectedV = new Velocity(ballOriginalDx, - ballOriginalDy);
        Velocity actualV = ball.getVelocity();

        assertTrue(VelocitiesEqual(expectedV, actualV));
    }


    @Test
    public void ballHitsRegion4NoDx() {
        initialize(this.game);
        int paddleX = GameLevel.WIDTH / 2 - 45;
        int paddleY = GameLevel.HEIGHT / 2;
        initializePaddle(paddleX, paddleY, game.getGui().getKeyboardSensor());

        double ballOriginalDx = 0;
        double ballOriginalDy = 20;

        Ball ball = new Ball(paddleX + 9 + 18 * 3, (double) GameLevel.HEIGHT / 2 - 10, 5, Color.WHITE);
        ball.setVelocity(ballOriginalDx, ballOriginalDy);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        run(numOfReps);

        double dx = ball.getVelocity().getDx();
        double dy = ball.getVelocity().getDy();
        double speed = Math.sqrt((dx * dx) + (dy * dy));

        Velocity expectedV = Velocity.fromAngleAndSpeed(30, speed);
        Velocity actualV = ball.getVelocity();

        assertTrue(VelocitiesEqual(expectedV, actualV));
    }


    @Test
    public void ballHitsRegion5NoDx() {
        initialize(this.game);
        int paddleX = GameLevel.WIDTH / 2 - 45;
        int paddleY = GameLevel.HEIGHT / 2;
        initializePaddle(paddleX, paddleY, game.getGui().getKeyboardSensor());

        double ballOriginalDx = 0;
        double ballOriginalDy = 20;

        Ball ball = new Ball(paddleX + 9 + 18 * 4, (double) GameLevel.HEIGHT / 2 - 10, 5, Color.WHITE);
        ball.setVelocity(ballOriginalDx, ballOriginalDy);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        run(numOfReps);

        double dx = ball.getVelocity().getDx();
        double dy = ball.getVelocity().getDy();
        double speed = Math.sqrt((dx * dx) + (dy * dy));

        Velocity expectedV = Velocity.fromAngleAndSpeed(60, speed);
        Velocity actualV = ball.getVelocity();

        assertTrue(VelocitiesEqual(expectedV, actualV));
    }

    @Test
    public void ballHitsRegionWithDx() {
        initialize(this.game);
        int paddleX = GameLevel.WIDTH / 2 - 45;
        int paddleY = GameLevel.HEIGHT / 2;
        initializePaddle(paddleX, paddleY, game.getGui().getKeyboardSensor());

        double originalDx = 15;
        double originalDy = 15;

        for (int i = 0; i < 5; i++) {
            Ball ball = new Ball(paddleX - 10 + 20 * i, (double) GameLevel.HEIGHT / 2 - 10, 5, Color.WHITE);
            ball.setVelocity(originalDx, originalDy);
            ball.setGameEnvironment(game.getEnvironment());
            ball.addToGame(game);

            run(numOfReps);

            double dx = ball.getVelocity().getDx();
            double dy = ball.getVelocity().getDy();
            double speed = Math.sqrt((dx * dx) + (dy * dy));

            Velocity expectedV = Velocity.fromAngleAndSpeed(- 60 + i * 30, speed);
            Velocity actualV = ball.getVelocity();

            if (i == 2) {
                expectedV = new Velocity(originalDx, - originalDy);
            }

            assertTrue(VelocitiesEqual(expectedV, actualV));
        }
    }

    @Test
    public void straightBallHitsFromLeftPassingThrough() {
        initialize(this.game);
        int paddleX = GameLevel.WIDTH / 2 - 45;
        int paddleY = GameLevel.HEIGHT / 2;
        initializePaddle(paddleX, paddleY, game.getGui().getKeyboardSensor());

        double originalDx = 7;
        double originalDy = 0;

        Ball ball = new Ball(paddleX - 10, paddleY + 10, 5, Color.WHITE);
        ball.setVelocity(originalDx, originalDy);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        run(numOfReps);

        Velocity expectedV = new Velocity(- originalDx, 0);
        Velocity actualV = ball.getVelocity();

        assertTrue(VelocitiesEqual(expectedV, actualV));
    }

    @Test
    public void straightBallHitsFromLeftHittingBorderExactly() {
        initialize(this.game);
        int paddleX = GameLevel.WIDTH / 2 - 45;
        int paddleY = GameLevel.HEIGHT / 2;
        initializePaddle(paddleX, paddleY, game.getGui().getKeyboardSensor());

        double originalDx = 10;
        double originalDy = 0;

        Ball ball = new Ball(paddleX - 10, paddleY + 10, 5, Color.WHITE);
        ball.setVelocity(originalDx, originalDy);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        run(numOfReps);

        Velocity expectedV = new Velocity(- originalDx, 0);
        Velocity actualV = ball.getVelocity();

        assertTrue(VelocitiesEqual(expectedV, actualV));
    }

    @Test
    public void straightBallHitsFromRightPassingThrough() {
        initialize(this.game);
        int paddleX = GameLevel.WIDTH / 2 - 45;
        int paddleY = GameLevel.HEIGHT / 2;
        initializePaddle(paddleX, paddleY, game.getGui().getKeyboardSensor());

        double originalDx = - 7;
        double originalDy = 0;

        Ball ball = new Ball(paddleX + 90 + 10, paddleY + 10, 5, Color.WHITE);
        ball.setVelocity(originalDx, originalDy);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        run(numOfReps);

        Velocity expectedV = new Velocity(- originalDx, 0);
        Velocity actualV = ball.getVelocity();

        assertTrue(VelocitiesEqual(expectedV, actualV));
    }

    @Test
    public void ballHitsFromRightAngle() {
        initialize(this.game);
        int paddleX = GameLevel.WIDTH / 2 - 45;
        int paddleY = GameLevel.HEIGHT / 2;
        initializePaddle(paddleX, paddleY, game.getGui().getKeyboardSensor());

        double originalDx = - 15;
        double originalDy = 15;

        Ball ball = new Ball(paddleX + 90 + 20, paddleY - 10, 5, Color.WHITE);
        ball.setVelocity(originalDx, originalDy);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        run(numOfReps);

        Velocity expectedV = new Velocity(- originalDx, originalDy);
        Velocity actualV = ball.getVelocity();

        assertTrue(VelocitiesEqual(expectedV, actualV));
    }

    @Test
    public void ballHitsFromTheBottomStraight() {
        initialize(this.game);
        int paddleX = GameLevel.WIDTH / 2 - 45;
        int paddleY = GameLevel.HEIGHT / 2;
        initializePaddle(paddleX, paddleY, game.getGui().getKeyboardSensor());

        double originalDx = 0;
        double originalDy = - 15;

        Ball ball = new Ball((double) GameLevel.WIDTH / 2, paddleY + 40, 5, Color.WHITE);
        ball.setVelocity(originalDx, originalDy);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        run(numOfReps);

        Velocity expectedV = new Velocity(0, - originalDy);
        Velocity actualV = ball.getVelocity();

        assertTrue(VelocitiesEqual(expectedV, actualV));
    }

    @Test
    public void ballHitsFromBottomAngle() {
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
    public void ballHitsFromTheTopLeftCorner() {
        initialize(this.game);
        int paddleX = GameLevel.WIDTH / 2 - 45;
        int paddleY = GameLevel.HEIGHT / 2;
        initializePaddle(paddleX, paddleY, game.getGui().getKeyboardSensor());

        double originalDx = 15;
        double originalDy = 15;

        Ball ball = new Ball(paddleX - 15, paddleY - 15, 5, Color.WHITE);
        ball.setVelocity(originalDx, originalDy);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        run(numOfReps);

        double dx = ball.getVelocity().getDx();
        double dy = ball.getVelocity().getDy();
        double speed = Math.sqrt((dx * dx) + (dy * dy));

        Velocity expectedV = Velocity.fromAngleAndSpeed(- 60, speed);
        Velocity actualV = ball.getVelocity();

        assertTrue(VelocitiesEqual(expectedV, actualV));
    }


    @Test
    public void ballHitsFromTheBottomRightCorner() {
        initialize(this.game);
        int paddleX = GameLevel.WIDTH / 2 - 45;
        int paddleY = GameLevel.HEIGHT / 2;
        initializePaddle(paddleX, paddleY, game.getGui().getKeyboardSensor());

        double originalDx = - 15;
        double originalDy = - 15;

        Ball ball = new Ball(paddleX + 90 + 15, paddleY + 20 + 15, 5, Color.WHITE);
        ball.setVelocity(originalDx, originalDy);
        ball.setGameEnvironment(game.getEnvironment());
        ball.addToGame(game);

        run(numOfReps);

        Velocity expectedV = new Velocity(- originalDx, - originalDy);
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