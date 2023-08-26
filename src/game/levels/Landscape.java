// 206573289 Omri Levi


package game.levels;

import game.levels.gameFunction.environment.Sprite;
import game.levels.gameFunction.GameLevel;
import game.levels.backgrounds.LandscapeBackground;
import game.shapes.circles.Point;
import game.shapes.circles.Velocity;
import game.shapes.squares.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Holds all the information of the Landscape level.
 */
public class Landscape implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 5;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();

        Random rand = new Random();
        int angleRange = 30;                        // the angle will be randomized between -angleRange and angleRange

        for (int i = 0; i < numberOfBalls(); i++) {
            int angle = rand.nextInt(angleRange * 2) - angleRange;          // get an angle between -30 and 30
            velocities.add(Velocity.fromAngleAndSpeed(angle, 5));
        }

        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Level: Landscape";
    }

    @Override
    public Sprite getBackground() {
        return new LandscapeBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();

        // initialize blocks
        final int maxBlocksInRow = 8;                                              // more blocks will overfill rows
        final double legalWidth = GameLevel.WIDTH - GameLevel.BORDER_THICKNESS * 2;     // game width without borders
        final double legalHeight = GameLevel.WIDTH - GameLevel.BORDER_THICKNESS * 2;    // game height without borders

        int blockWidth = (int) (legalWidth / maxBlocksInRow);
        int blockHeight = (int) ((legalHeight * (legalWidth / maxBlocksInRow)) / (4 * legalWidth));

        // create blocks and initialize
        for (int i = 0; i < maxBlocksInRow; i++) {
            double currentPointX = GameLevel.BORDER_THICKNESS + blockWidth * i;
            double currentPointY = GameLevel.BORDER_THICKNESS + blockHeight + 150;
            Point upperLeft = new Point(currentPointX, currentPointY);
            Block block = new Block(upperLeft, blockWidth, blockHeight);

            // if there are more rows than colors in the color array, repeat color
            block.getCollisionRectangle().setColor(new Color(200, 200, 200));
            blockList.add(block);
        }

        return blockList;
    }
}
