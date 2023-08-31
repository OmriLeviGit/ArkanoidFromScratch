// 206573289 Omri Levi


package game.levels;

import game.levels.gameFunction.environment.Sprite;
import game.levels.gameFunction.GameLevel;
import game.levels.backgrounds.PrismBackground;
import game.shapes.circles.Point;
import game.shapes.circles.Velocity;
import game.shapes.squares.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Holds all the information of the Prism level.
 */
public class Prism implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 100;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();

        Random rand = new Random();
        int angleRange = 60;                        // the angle will be randomized between -angleRange and angleRange

        for (int i = 0; i < numberOfBalls(); i++) {
            int angle = rand.nextInt(angleRange * 2) - angleRange;          // get an angle between -30 and 30
            velocities.add(Velocity.fromAngleAndSpeed(angle, 5));
        }

        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 90;
    }

    @Override
    public String levelName() {
        return "Level: Prism";
    }

    @Override
    public Sprite getBackground() {
        return new PrismBackground();
    }

    @Override
    public List<Block> blocks() {
        Color[] color = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
        List<Block> blockList = new ArrayList<>();

        // initialize blocks
        final int maxBlocksInRow = 15;                                          // more than that will overfill rows
        final double legalWidth = GameLevel.WIDTH - GameLevel.BORDER_THICKNESS * 2;       // game width without borders
        final double legalHeight = GameLevel.WIDTH - GameLevel.BORDER_THICKNESS * 2;      // game height without borders

        int numOfBlocks = 12;                                   // amount of blocks in the longest row
        int numOfRows = color.length;                           // amount of rows required for the assignment
        int heightFromCeiling = 3;                              // used to increase distance from the top border

        // width and height are calculated with these ratios in mind
        int blockWidth = (int) (legalWidth / maxBlocksInRow);
        int blockHeight = (int) ((legalHeight * (legalWidth / maxBlocksInRow)) / (2 * legalWidth));

        // create blocks and initialize
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfBlocks - i; j++) {

                // set current x value starting from the right of the screen
                double currentPointX = GameLevel.WIDTH - GameLevel.BORDER_THICKNESS - blockWidth * (j + 1);
                double currentPointY = GameLevel.BORDER_THICKNESS + blockHeight * (i + heightFromCeiling);
                Point upperLeft = new Point(currentPointX, currentPointY);
                Block block = new Block(upperLeft, blockWidth, blockHeight);

                // if there are more rows than colors in the color array, repeat color
                block.getCollisionRectangle().setColor(color[i % color.length]);
                blockList.add(block);
            }
        }

        return blockList;
    }
}

