package game.shapes.circles;

import biuoop.DrawSurface;
import biuoop.GUI;
import game.gameFunction.environment.GameEnvironment;
import game.shapes.squares.Block;
import game.shapes.squares.Rectangle;
import org.junit.jupiter.api.Test;

import java.awt.Color;

class BallTest {
    GUI gui = new GUI("Test Part 1 - ass3", 300, 300);
    biuoop.Sleeper sleeper = new biuoop.Sleeper();
    GameEnvironment gameEnvironment = new GameEnvironment();


    @Test
    public void test1() {

        // create borders
        Block[] blocks =  {
                new Block(new Rectangle(new Point(0, 0), 10, 200)),   //left border
                new Block(new Rectangle(new Point(0, 0), 200, 10)),      //top border
                new Block(new Rectangle(new Point(200, 0), 10, 200)),    //right border
                new Block(new Rectangle(new Point(0, 200), 200, 10)),   //down border
                new Block(new Rectangle(new Point(50, 50), 50, 50))};      //seen on screen

        blocks[0].getCollisionRectangle().setColor(Color.YELLOW);
        blocks[1].getCollisionRectangle().setColor(Color.RED);
        blocks[2].getCollisionRectangle().setColor(Color.BLUE);
        blocks[3].getCollisionRectangle().setColor(Color.GREEN);
        blocks[4].getCollisionRectangle().setColor(Color.GRAY);


        for (Block block : blocks) {
            gameEnvironment.addCollidable(block);
        }

        // create balls
        Ball ball1 = new Ball(180, 60, 5, java.awt.Color.BLACK);    //check movement on X
        ball1.setVelocity(-5, 0);

        Ball ball2 = new Ball(60, 180, 5, Color.ORANGE);    //check movement on Y
        ball2.setVelocity(0, 5);

        Ball ball3 = new Ball(150, 150, 5, Color.MAGENTA);  //check movement diagonal
        ball3.setVelocity(5, 5);

        Ball ball4 = new Ball(40, 160, 5, Color.CYAN);      //check free movement
        ball4.setVelocity(7,5);

        Ball[] balls = {ball1, ball2, ball3, ball4};
        for (Ball ball : balls) {
            ball.setGameEnvironment(gameEnvironment);
        }


        long startTime = System.currentTimeMillis(); // timing
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            //draw blocks
            for (Block block : blocks) {
                block.drawOn(d);
            }

            //draw balls
            for (Ball ball : balls) {
                ball.moveOneStep();
                ball.drawOn(d);
            }


            gui.show(d);
            sleeper.sleepFor(5);  // wait for 50 milliseconds.
            long usedTime = System.currentTimeMillis() - startTime;

            if (usedTime > 400) {
                break;
            }
        }
    }
}